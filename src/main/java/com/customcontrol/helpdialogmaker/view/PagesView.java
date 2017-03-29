package com.customcontrol.helpdialogmaker.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.consts.Screens;
import com.customcontrol.helpdialogmaker.event.PageEvent;
import com.customcontrol.helpdialogmaker.helper.Helper;
import com.customcontrol.helpdialogmaker.model.Page;
import com.customcontrol.helpdialogmaker.session.Session;

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

public class PagesView implements Initializable {

	@FXML
	Button						btnHelp;
	@FXML
	Button						btnClose;
	@FXML
	Button						btnAddPage;
	@FXML
	TreeView<String>			tvBaum;
	private TreeItem<String>	rootNode;
	private int PAGE_INDEX_COUNTER = 1;
	private Stage stage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootNode = new TreeItem<String>("");
		rootNode.setExpanded(true);
		// Creating HOME
		List<FXMLLoader> pages = Session.createInstance().pages;
		for (FXMLLoader fxmlLoader : pages) {
			Node content = fxmlLoader.getRoot();
			PageView pageView = fxmlLoader.getController();
			if (pageView.isHome()) {
				pageView.setHomeIconVisible(true);
				pageView.setNameEntrancePossible(false);
			}
			TreeItem<String> pageTreeItem = new TreeItem<String>("", content);
			rootNode.getChildren().add(pageTreeItem);
			
		}
		tvBaum.setRoot(rootNode);
		tvBaum.setShowRoot(false);
		btnAddPage.setOnAction(this::onAddPage);
		btnClose.setOnAction(this::onClose);
	}

	private void handleEvent() {
		stage.addEventHandler(PageEvent.REMOVE , event ->{
			rootNode.getChildren().clear();
			List<FXMLLoader> pages = Session.createInstance().pages;
			for (FXMLLoader fxmlLoader : pages) {
				Node content = fxmlLoader.getRoot();
				PageView pageView = fxmlLoader.getController();
				if (pageView.isHome()) {
					pageView.setHomeIconVisible(true);
					pageView.setNameEntrancePossible(false);
				}
				TreeItem<String> pageTreeItem = new TreeItem<String>("", content);
				rootNode.getChildren().add(pageTreeItem);
				
			}
		});
	}

	public void onClose(ActionEvent evt) {
		tvBaum.fireEvent(new WindowEvent(null, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	public void onAddPage(ActionEvent evt) {
		FXMLLoader fxmlLoader = Helper.createView(Screens.PAGE);
		Node rootIcon = fxmlLoader.getRoot();
		PageView pageView = fxmlLoader.getController();
		pageView.setStage(stage);
		List<FXMLLoader> pages = Session.createInstance().pages;
		if(pages != null && pages.isEmpty()){
			pageView.setHome(true);
			pageView.setHomeIconVisible(true);
			pageView.setNameEntrancePossible(true);
		}else{
			pageView.setHomeIconVisible(false);
			pageView.setNameEntrancePossible(true);
		}
		Page newPage = new Page(PAGE_INDEX_COUNTER ++, pageView.getPageViewModel().getName());
		pageView.getPageViewModel().setPage(newPage);
		Session.createInstance().getPages().add(fxmlLoader);
		TreeItem<String> page = new TreeItem<String>("", rootIcon);
		rootNode.getChildren().add(page);
		
	}

	
	public Stage getStage() {
		return stage;
	}

	
	public void setStage(Stage stage) {
		this.stage = stage;
		handleEvent();
	}
	
	
}
