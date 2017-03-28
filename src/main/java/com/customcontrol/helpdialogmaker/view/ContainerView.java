package com.customcontrol.helpdialogmaker.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import com.customcontrol.helpdialogmaker.consts.Screens;
import com.customcontrol.helpdialogmaker.helper.Helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ContainerView implements Initializable {

	@FXML
	Button			btnPages;
	@FXML
	Button			btnSS;
	@FXML
	Button			btnView;
	private PopOver	popOver;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		
		btnSS.setOnAction(this::onCreateNewPage);
		btnPages.setOnAction(this::onPagesClick);
	}

	public void onPagesClick(ActionEvent evt) {
		popOver = new PopOver();
		popOver.setArrowLocation(ArrowLocation.TOP_CENTER);
		FXMLLoader fxmlLoader = Helper.createView(Screens.PAGES);
		popOver.setContentNode(fxmlLoader.getRoot());
		popOver.setDetachable(false);
		popOver.show(btnPages);
	}
	
	public void onCreateNewPage(ActionEvent evt) {
		popOver = new PopOver();
		popOver.setArrowLocation(ArrowLocation.TOP_CENTER);
		FXMLLoader fxmlLoader = Helper.createView(Screens.PAGE);
		popOver.setContentNode(fxmlLoader.getRoot());
		popOver.setDetachable(false);
		popOver.show(btnPages);
	}
}
