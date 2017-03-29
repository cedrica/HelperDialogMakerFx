package com.customcontrol.helpdialogmaker.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import com.customcontrol.helpdialogmaker.consts.Screens;
import com.customcontrol.helpdialogmaker.event.PopOverEvent;
import com.customcontrol.helpdialogmaker.helper.Helper;
import com.customcontrol.helpdialogmaker.helper.PopOverHelper;
import com.customcontrol.helpdialogmaker.viewmodel.PageViewModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PageView implements Initializable {

	@FXML
	Label					lblName;
	@FXML
	Button					btnInfo;
	@FXML
	Label					lblHome;
	@FXML
	TextField				tfName;
	@FXML
	Button					btnOk;
	@FXML
	PageViewModel			pageViewModel;
	private boolean			home;
	private PageInfoView	pageInfoView;
	private Stage stage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		binding();
		btnInfo.setOnAction(this::onInfo);
		btnOk.setOnAction(this::onOk);

	}

	public void binding() {
		pageViewModel.nameProperty().bind(tfName.textProperty());
		tfName.visibleProperty().bind(pageViewModel.tfNameVisibleProperty());
		lblName.visibleProperty().bind(tfName.visibleProperty().not());
	}

	public void onOk(ActionEvent evt) {
		btnInfo.setVisible(true);
		btnOk.setVisible(false);
		pageViewModel.setTfNameVisible(false);

	}

	public void onInfo(ActionEvent evt) {
//		PopOver popOver = new PopOver();
//		popOver.setArrowLocation(ArrowLocation.LEFT_TOP);
//
//		popOver.setContentNode(fxmlLoader.getRoot());
//		popOver.setDetachable(false);
//		popOver.show(btnInfo);
//		popOver.setAutoHide(true);
		
		FXMLLoader fxmlLoader = Helper.createView(Screens.PAGE_INFO);
		pageInfoView = fxmlLoader.getController();
		pageInfoView.setStage(stage);
		PopOverHelper popOverHelper = PopOverHelper.getInstance();
		popOverHelper.setArrowLocation(ArrowLocation.LEFT_TOP);
		popOverHelper.setParent(btnInfo);
		popOverHelper.setContentNode(fxmlLoader.getRoot());
		popOverHelper.setDetachable(false);
		popOverHelper.show();
		pageInfoView.getPageInfoViewModel().setPage(pageViewModel.getPage());
		popOverHelper.getPopOver().addEventFilter(PopOverEvent.CLOSE, e ->{
			popOverHelper.hide();
		});
	}

	public void setHomeIconVisible(boolean b) {
		lblHome.setVisible(b);
	}

	public void setNameEntrancePossible(boolean b) {
		if (b) {
			btnInfo.setVisible(!b);
			btnOk.setVisible(b);
		} else {
			btnInfo.setVisible(!b);
			btnOk.setVisible(b);
		}
		pageViewModel.setTfNameVisible(b);
	}

	public void setHome(boolean home) {
		this.home = home;
	}

	public boolean isHome() {
		return this.home;
	}

	
	public PageViewModel getPageViewModel() {
		return pageViewModel;
	}

	
	public void setPageViewModel(PageViewModel pageViewModel) {
		this.pageViewModel = pageViewModel;
	}

	
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	
}
