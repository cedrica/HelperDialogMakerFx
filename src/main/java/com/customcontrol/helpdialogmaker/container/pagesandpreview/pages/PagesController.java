package com.customcontrol.helpdialogmaker.container.pagesandpreview.pages;

import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.customcontrol.helpdialogmaker.container.ContainerService;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page.PageView;
import com.customcontrol.helpdialogmaker.data.ConfigurationData;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.WindowEvent;
import javafx.util.Pair;

public class PagesController implements Initializable {

	private final static Logger LOG = LoggerFactory.getLogger(PagesController.class);

	@FXML
	TreeView<String> tvBaum;

	private TreeItem<String> parentTree;

	@FXML
	PagesView rootNode;

	private static int PAGE_INDEX = 1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		parentTree = new TreeItem<String>();
		parentTree.setExpanded(true);
		tvBaum.setRoot(parentTree);
		tvBaum.setShowRoot(false);
		rootNode.setParentTree(parentTree);
		rootNode.newPageProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal != null)
				addNewPage(newVal,parentTree);
		});
		rootNode.subPageIndexProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal != -1)
				addSubPage(parentTree.getChildren(), newVal);
			rootNode.addSubPageIndex(-1);
		});
		rootNode.removePageProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal != -1) // I have to reset the index after editing to
								// allow the property change to be trigger again
				removePage(parentTree.getChildren(), newVal);
			rootNode.setRenamePage(-1);
		});
		rootNode.renamePageProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal != -1)
				rename(parentTree.getChildren(), newVal);
			rootNode.setRenamePage(-1);
		});

		rootNode.pageConfigutaionProperty().addListener((obs, oldVal, newVal) -> {
			saveConfigurationCorrespondingPage(newVal, parentTree.getChildren());
		});

		rootNode.enablePopUpMenuBtnProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal.getKey() != -1)
				enablePopUpMenuBtn(newVal, parentTree.getChildren());
			rootNode.setEnablePopUpMenuBtn(new Pair<Integer, Boolean>(-1, null));
		});
	}

	private void enablePopUpMenuBtn(Pair<Integer, Boolean> pair, ObservableList<TreeItem<String>> items) {
		for (TreeItem<String> page : items) {
			PageView pageView = (PageView) page.getGraphic();
			if (pageView.getIndex() == pair.getKey()) {
				pageView.setDisablePopUpMenuBtn(pair.getValue());
				return;
			} else {
				enablePopUpMenuBtn(pair, page.getChildren());
			}
		}
	}

	private void saveConfigurationCorrespondingPage(
			Pair<Integer, Pair<String, ObservableList<ConfigurationData>>> newVal,
			ObservableList<TreeItem<String>> items) {
		for (TreeItem<String> page : items) {
			PageView pageView = (PageView) page.getGraphic();
			if (pageView.getIndex() == newVal.getKey()) {
				pageView.setHtml(newVal.getValue().getKey());
				pageView.setConfiguration(newVal.getValue().getValue());
				pageView.setDisablePopUpMenuBtn(true);
				return;
			} else if (page.getChildren() != null && page.getChildren().size() > 0) {
				saveConfigurationCorrespondingPage(newVal, page.getChildren());
			}
		}
	}

	private void rename(ObservableList<TreeItem<String>> items, Integer indexOfPageToBeRemove) {
		for (TreeItem<String> page : items) {
			PageView pageView = (PageView) page.getGraphic();
			if (pageView.getIndex() == indexOfPageToBeRemove) {
				pageView.setNameVisible(true);
				return;
			} else if (page.getChildren() != null && !page.getChildren().isEmpty()) {
				rename(page.getChildren(), indexOfPageToBeRemove);
			}
		}
	}

	public void removePage(ObservableList<TreeItem<String>> items, Integer indexOfPageToBeRemove) {
		boolean isRootNode = false;
		int indexRemovedPage = 0;
		for (TreeItem<String> page : items) {
			PageView pageView = (PageView) page.getGraphic();
			if (pageView.getIndex() == indexOfPageToBeRemove) {
				boolean remove = page.getParent().getChildren().remove(page);
				isRootNode = pageView.isRootNode();
				indexRemovedPage = pageView.getIndex();
				if (isRootNode) {
					--PAGE_INDEX;
				}
				if (remove)
					LOG.info("Die untere Seite mit dem Name: " + pageView.getName() + " wurde entfernt.");
				break;
			} else if (page.getChildren() != null && !page.getChildren().isEmpty()) {
				removePage(page.getChildren(), indexOfPageToBeRemove);
			}
		}
		if (isRootNode)
			decrementPageIndex(items, indexRemovedPage, 1);
		return;
	}

	public void decrementPageIndex(ObservableList<TreeItem<String>> items, int indexRemovedPage, int tiefe) {
		for (TreeItem<String> page : items) {
			PageView pageView = (PageView) page.getGraphic();
			if (pageView.isRootNode() && pageView.getIndex() < indexRemovedPage)
				continue;
			else if (pageView.isRootNode()) {
				pageView.setIndex(pageView.getIndex() - 1);
				if (page.getChildren() != null && !page.getChildren().isEmpty()) {
					decrementPageIndex(page.getChildren(), indexRemovedPage, tiefe);
				}
			} else {
				pageView.setIndex(pageView.getIndex() - 10 * tiefe);
				if (page.getChildren() != null && !page.getChildren().isEmpty()) {
					decrementPageIndex(page.getChildren(), indexRemovedPage, tiefe * 10);
				}
			}

		}
	}

	public void addSubPage(ObservableList<TreeItem<String>> items, int parentIndex) {
		for (TreeItem<String> page : items) {
			PageView parentPage = (PageView) page.getGraphic();
			if (parentPage.getIndex() == parentIndex) {
				PageView subPage = new PageView();
				subPage.setHome(false);
				subPage.setRootNode(false);
				int index = ContainerService.calculIndex(page);
				subPage.setIndex(index);
				TreeItem<String> childItem = new TreeItem<String>("", subPage);
				page.setExpanded(true);
				page.getChildren().add(childItem);
				return;
			} else if (page.getChildren() != null && !page.getChildren().isEmpty()) {
				addSubPage(page.getChildren(), parentIndex);
			}
		}
	}

	public void onClose(ActionEvent evt) {
		tvBaum.fireEvent(new WindowEvent(null, WindowEvent.WINDOW_CLOSE_REQUEST));
		rootNode.setBtnPagesDisabled(false);
	}

	public void onAddPage(ActionEvent evt) {
		addPage();
	}

	public void addPage() {
		PageView pageView = new PageView();
		if (parentTree.getChildren() != null && parentTree.getChildren().isEmpty()) {
			pageView.setHome(true);
		} else {
			pageView.setHome(false);
		}
		pageView.setIndex(PAGE_INDEX++);
		TreeItem<String> page = new TreeItem<String>("", pageView);
		parentTree.getChildren().add(page);
	}

	public void addNewPage(PageView pageView, TreeItem<String> parent) {
		TreeItem<String> page = new TreeItem<String>("", pageView);
		parent.getChildren().add(page);
		if(pageView.getSubPages() != null && !pageView.getSubPages().isEmpty()){
			 ObservableList<PageView> subPages = pageView.getSubPages();
			for (PageView subPage : subPages) {
				addNewPage(subPage,page);
			}
		}
	}

}
