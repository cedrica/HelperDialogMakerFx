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

    private TreeItem<String> rootNode;

    @FXML
    PagesView pagesView;

    private static int PAGE_INDEX = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rootNode = new TreeItem<String>();
        rootNode.setExpanded(true);
        tvBaum.setRoot(rootNode);
        tvBaum.setShowRoot(false);
        pagesView.setRootNode(rootNode);
        pagesView.subPageProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != -1)
                addSubPage(rootNode.getChildren(), newVal);
            pagesView.addSubPage(-1);
        });
        pagesView.removePageProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != -1) // I have to reset the index after editing to allow the property change to be trigger again
                removePage(rootNode.getChildren(), newVal);
            pagesView.setRenamePage(-1);
        });
        pagesView.renamePageProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != -1) 
                rename(rootNode.getChildren(), newVal);
            pagesView.setRenamePage(-1);
        });

        pagesView.pageConfigutaionProperty().addListener((obs, oldVal, newVal) -> {
            saveConfigurationCorrespondingPage(newVal);
        });

        pagesView.enablePopUpMenuBtnProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.getKey() != -1)
                enablePopUpMenuBtn(newVal);
            pagesView.setEnablePopUpMenuBtn(new Pair<Integer, Boolean>(-1, null));
        });
    }

    private void enablePopUpMenuBtn(Pair<Integer,Boolean> pair) {
        for (TreeItem<String> page : rootNode.getChildren()) {
            PageView pageView = (PageView) page.getGraphic();
            if (pageView.getIndex() == pair.getKey()) {
                pageView.setDisablePopUpMenuBtn(pair.getValue());
                break;
            }
        }
    }

    private void saveConfigurationCorrespondingPage(Pair<Integer, Pair<String, ObservableList<ConfigurationData>>> newVal) {
        for (TreeItem<String> page : rootNode.getChildren()) {
            PageView pageView = (PageView) page.getGraphic();
            if (pageView.getIndex() == newVal.getKey()) {
                pageView.setHtml(newVal.getValue().getKey());
                pageView.setConfiguration(newVal.getValue().getValue());
                pageView.setDisablePopUpMenuBtn(true);
                break;
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
        pagesView.setBtnPagesDisabled(false);
    }

    public void onAddPage(ActionEvent evt) {
        addPage();
    }

    public void addPage() {
        PageView pageView = new PageView();
        if (rootNode.getChildren() != null && rootNode.getChildren().isEmpty()) {
            pageView.setHome(true);
        } else {
            pageView.setHome(false);
        }
        pageView.setIndex(PAGE_INDEX++);
        TreeItem<String> page = new TreeItem<String>("", pageView);
        rootNode.getChildren().add(page);
    }

}
