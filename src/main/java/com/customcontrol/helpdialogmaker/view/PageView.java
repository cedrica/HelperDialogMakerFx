package com.customcontrol.helpdialogmaker.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import com.customcontrol.helpdialogmaker.consts.Screens;
import com.customcontrol.helpdialogmaker.helper.Helper;
import com.customcontrol.helpdialogmaker.viewmodel.PageViewModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PageView implements Initializable{

	@FXML Label lblName;
	@FXML Button btnInfo;
	@FXML Label lblHome;
	private PopOver	popOver;
	@FXML TextField tfName;
	@FXML Button btnOk;
	@FXML PageViewModel pageViewModel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		popOver = new PopOver();
		popOver.setArrowLocation(ArrowLocation.LEFT_TOP);
		FXMLLoader fxmlLoader = Helper.createView(Screens.PAGE_INFO);
		popOver.setContentNode(fxmlLoader.getRoot());
		popOver.setDetachable(false);
		btnInfo.setOnAction(this::onInfo);
		btnOk.setOnAction(this::onOk);
	}

	public void onOk(ActionEvent evt){
		setNameEditable(false);
		setEitableInfo(true);
		pageViewModel.nameProperty().bind(tfName.textProperty());
	}
	
	public void onInfo(ActionEvent evt){
		popOver.show(btnInfo);
	}

	public void setHomeIconVisible(boolean b) {
		lblHome.setVisible(b);
	}

	public void setNameEntrancePossible(boolean b) {
		if(b){
			setNameEditable(true);
			setEitableInfo(false);
		}else{
			setNameEditable(false);
			setEitableInfo(true);
		}
	}

	public void setEitableInfo(boolean b) {
		lblName.setVisible(b);
		btnInfo.setVisible(b);
	}

	public void setNameEditable(boolean b) {
		tfName.setVisible(b);
		btnOk.setVisible(b);
	}
}
