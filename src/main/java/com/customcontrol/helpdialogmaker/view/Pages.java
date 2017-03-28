package com.customcontrol.helpdialogmaker.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.consts.Screens;
import com.customcontrol.helpdialogmaker.helper.Helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.WindowEvent;

public class Pages implements Initializable {

	@FXML
	Button				btnHelp;
	@FXML
	Button				btnClose;
	@FXML
	Button				btnAddPage;
	@FXML
	TreeView<String>	tvBaum;
private TreeItem<String> rootNode;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootNode = new TreeItem<String>("");
		rootNode.setExpanded(true);
		//Creating HOME
		FXMLLoader fxmlLoader = Helper.createView(Screens.PAGE);
		Node rootIcon = fxmlLoader.getRoot();
		PageView pageView = fxmlLoader.getController();
		pageView.setHomeIconVisible(true);
		pageView.setNameEntrancePossible(true);
		TreeItem<String> page = new TreeItem<String>("",rootIcon);
		rootNode.getChildren().add(page);
		tvBaum.setRoot(rootNode);
		btnAddPage.setOnAction(this::onAddPage);
		btnClose.setOnAction(this::onClose);
	}

	public void onClose(ActionEvent evt){
		tvBaum.fireEvent(new WindowEvent(null, WindowEvent.WINDOW_CLOSE_REQUEST));
	}
	
	public void onAddPage(ActionEvent evt){
		FXMLLoader fxmlLoader = Helper.createView(Screens.PAGE);
		Node rootIcon = fxmlLoader.getRoot();
		PageView pageView = fxmlLoader.getController();
		pageView.setHomeIconVisible(false);
		pageView.setNameEntrancePossible(true);
		TreeItem<String> page = new TreeItem<String>("",rootIcon);
		rootNode.getChildren().add(page);
		tvBaum.setShowRoot(false);
	}
}
