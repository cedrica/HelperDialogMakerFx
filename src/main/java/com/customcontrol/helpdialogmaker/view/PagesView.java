package com.customcontrol.helpdialogmaker.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.consts.HtmlPart;
import com.customcontrol.helpdialogmaker.consts.Screens;
import com.customcontrol.helpdialogmaker.event.PageEvent;
import com.customcontrol.helpdialogmaker.helper.Helper;
import com.customcontrol.helpdialogmaker.model.PageData;
import com.customcontrol.helpdialogmaker.session.Session;
import com.customcontrol.helpdialogmaker.viewmodel.PagesViewModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class PagesView implements Initializable {

	@FXML
	Button				btnHelp;
	@FXML
	Button				btnClose;
	@FXML
	Button				btnAddPage;
	@FXML
	TreeView<String>	tvBaum;
	@FXML
	WebView				webView;

	private TreeItem<String>	rootNode;
	private int					PAGE_INDEX_COUNTER	= 1;
	private Stage				stage;
	private PagesViewModel		pagesViewModel;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pagesViewModel = new PagesViewModel();
		rootNode = new TreeItem<String>("");
		rootNode.setExpanded(true);
		// Creating HOME
		List<PageView> pages = Session.createInstance().pages;
		recursivInitPages(rootNode, pages);
		tvBaum.setRoot(rootNode);
		tvBaum.setShowRoot(false);
		btnAddPage.setOnAction(this::onAddPage);
		// btnClose.setOnAction(this::onClose);
	}

	private void recursivInitPages(TreeItem<String> root, List<PageView> pages) {
		root.setExpanded(true);
		for (PageView pageView : pages) {
			Node content = pageView.getMySquelet().getRoot();
			if (pageView.isHome()) {
				pageView.setHomeIconVisible(true);
			}
			TreeItem<String> pageTreeItem = new TreeItem<String>("", content);
			if (!pageView.getSubPages().isEmpty()) {
				root.getChildren().add(pageTreeItem);
				recursivInitPages(pageTreeItem, pageView.getSubPages());
			} else {
				root.getChildren().add(pageTreeItem);
			}
		}
		return;
	}

	private void handleEvent() {
		stage.addEventHandler(PageEvent.REMOVE, event -> {
			PageData pageData = event.getPage();
			recursivRemovePage(rootNode, pageData);
			rootNode.getChildren().clear();
			List<PageView> pages = Session.createInstance().pages;
			recursivInitPages(rootNode, pages);
		});

		stage.addEventFilter(PageEvent.ADD_SUB_PAGE, evt -> {
			PageData pageData = evt.getPage();
			FXMLLoader fxmlLoader = Helper.createView(Screens.PAGE);
			Node squelet = fxmlLoader.getRoot();
			PageView pageView = fxmlLoader.getController();
			int index = calculIndex(pageData);
			PageData pageData2 = new PageData(index, "");
			pageView.getPageViewModel().setPageData(pageData2);
			pageView.setStage(stage);
			pageView.setMySquelet(fxmlLoader);
			pageView.setHomeIconVisible(false);
			squelet.setUserData(pageView);
			TreeItem<String> childItem = new TreeItem<String>("", squelet);
			recursivAddSubPage(rootNode, childItem, pageData, pageView);
		});

		stage.addEventFilter(PageEvent.ADD_MENU_POINT, evt -> {
			WebEngine webEngine = webView.getEngine();
			String content = HtmlPart.MENU
							+ "<body>"
							+"<div style=\"background-color: #444; width:100%;height:30px;padding-left: 5px; padding-right: 5px; padding-top: 5px;position:fixed;top:0\">"
							+ "<div class=\"col-lg-12\">" + "<div id=\"message\" style=\"color:white\"></div>"
							+ "<i class=\"fa fa-home\" style=\"color: #fff; font-size: 25px;\" ></i>"
							+ "<button id=\"menu-buton\" type=\"button\" class=\"pull-right\" style=\"background-color: transparent; border: none;\">"
							+ "<i class=\"fa fa-bars\" style=\"color: #fff; font-size: 20px;\"></i></button></div></div>"
							+ "<div id=\"menu\" style=\"position:fixed;top:0;width:100%;overflow:hidden;margin-top: 30px; "
							+ "background-color: #000; opacity: 0.8; filter: alpha(opacity = 50);z-index:200;\"><ul>";
			content += repeatProcess(Session.createInstance().pages, "");
			content += "</div></body>";
			 webEngine.loadContent(content);
			
		});

	}


	private String repeatProcess(List<PageView> subPages,String apres) {
		for (PageView pageView : subPages) {
			PageData pageData = pageView.getPageViewModel().getPageData();
			apres += "<li><h4><a href=\"#section" + pageData.getIndex() + "\">" + pageData.getIndex() + " " + pageData.getName() + "</a></h4>";
			if (pageView.getSubPages().size() > 0) {
				apres += "<ul>";
				repeatProcess(pageView.getSubPages(),apres);
			} else {
				apres += "</li>";
			}
		}
		apres += "</ul>";
		return apres;
	}

	private int calculIndex(PageData pageData) {
		int res = 0;
		if (!pageData.getSubPages().isEmpty()) {
			res = pageData.getIndex() * 10 + pageData.getSubPages().size() + 1;
		} else {
			res = pageData.getIndex() * 10 + 1;
		}
		return res;
	}

	private void recursivAddSubPage(TreeItem<String> root, TreeItem<String> childItem, PageData pageData, PageView pageView) {
		for (TreeItem<String> newRoot : root.getChildren()) {
			PageView userData = (PageView) newRoot.getGraphic().getUserData();
			if (userData.getPageViewModel().getPageData().getIndex() == pageData.getIndex()) {
				newRoot.setExpanded(true);
				newRoot.getChildren().add(childItem);
				addSubSubPageRec(Session.createInstance().pages, userData, pageView);
			} else if (!userData.getSubPages().isEmpty()) {
				recursivAddSubPage(newRoot, childItem, pageData, pageView);
			}
		}
		return;
	}

	private void addSubSubPageRec(List<PageView> pages, PageView userData, PageView pageView) {
		for (PageView pv : pages) {
			if (pv.getPageViewModel().getPageData().getIndex() == userData.getPageViewModel().getPageData().getIndex()) {
				pv.getSubPages().add(pageView);
				return;
			} else if (!pv.getSubPages().isEmpty()) {
				addSubSubPageRec(pv.getSubPages(), userData, pageView);
			}
		}
		return;
	}

	private void recursivRemovePage(TreeItem<String> root, PageData pageData) {
		for (TreeItem<String> newRoot : root.getChildren()) {
			PageView userData = (PageView) newRoot.getGraphic().getUserData();
			if (userData.getPageViewModel().getPageData().getIndex() == pageData.getIndex()) {
				removeSubPageRec(Session.createInstance().pages, userData);
				return;
			} else if (!userData.getSubPages().isEmpty()) {
				recursivRemovePage(newRoot, pageData);
			}
		}
		return;
	}

	private void removeSubPageRec(List<PageView> pages, PageView userData) {
		for (PageView pageView : pages) {
			if (pageView.getPageViewModel().getPageData().getIndex() == userData.getPageViewModel().getPageData().getIndex()) {
				pages.remove(pageView);
				return;
			} else if (!pageView.getSubPages().isEmpty()) {
				removeSubPageRec(pageView.getSubPages(), userData);
			}
		}
		return;
	}

	public void onClose(ActionEvent evt) {
		tvBaum.fireEvent(new WindowEvent(null, WindowEvent.WINDOW_CLOSE_REQUEST));
		pagesViewModel.setBtnPagesDisabled(false);
	}

	public void onAddPage(ActionEvent evt) {
		addPage(rootNode);

	}

	public void addPage(TreeItem<String> root) {
		FXMLLoader fxmlLoader = Helper.createView(Screens.PAGE);
		Node squelet = fxmlLoader.getRoot();
		PageView pageView = fxmlLoader.getController();
		pageView.setStage(stage);
		pageView.setMySquelet(fxmlLoader);
		List<PageView> pages = Session.createInstance().pages;
		if (pages != null && pages.isEmpty()) {
			pageView.setHome(true);
			pageView.setHomeIconVisible(true);
		} else {
			pageView.setHomeIconVisible(false);
		}
		PageData pageData = new PageData(PAGE_INDEX_COUNTER++, pageView.getPageViewModel().getName());
		pageView.getPageViewModel().setPageData(pageData);
		pageView.setRootNode(true);
		Session.createInstance().getPages().add(pageView);
		squelet.setUserData(pageView);
		TreeItem<String> page = new TreeItem<String>("", squelet);
		root.getChildren().add(page);
	}


	public Stage getStage() {
		return stage;
	}


	public void setStage(Stage stage) {
		this.stage = stage;
		handleEvent();
	}


	public PagesViewModel getPagesViewModel() {
		return pagesViewModel;
	}


	public void setPagesViewModel(PagesViewModel pagesViewModel) {
		this.pagesViewModel = pagesViewModel;
	}


}
