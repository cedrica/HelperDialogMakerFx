package com.customcontrol.helpdialogmaker.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import com.customcontrol.helpdialogmaker.consts.Screens;
import com.customcontrol.helpdialogmaker.event.PopOverEvent;
import com.customcontrol.helpdialogmaker.helper.Helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ContainerView implements Initializable {

	@FXML
	Button			btnPages;
	@FXML
	Button			btnSS;
	@FXML
	Button			btnView;
	@FXML WebView wvPreview;
	private PopOver	popOver;
	private Stage stage;
	private PagesView pagesView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnPages.setOnAction(this::onPagesClick);
		
	}

	public void onPagesClick(ActionEvent evt) {
		popOver = new PopOver();
		popOver.setArrowLocation(ArrowLocation.TOP_CENTER);
		FXMLLoader fxmlLoader = Helper.createView(Screens.PAGES);
		pagesView = fxmlLoader.getController();
		pagesView.setStage(stage);
		popOver.setContentNode(fxmlLoader.getRoot());
		popOver.setDetachable(true);
		popOver.show(btnPages);
		popOver.addEventFilter(PopOverEvent.CLOSE, e ->{
			popOver.hide();
			btnPages.setDisable(false);
		});
		btnPages.disableProperty().bind(pagesView.getPagesViewModel().btnPagesDisabledProperty());
	}
	
	
	public Stage getStage() {
		return stage;
	}

	
	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
