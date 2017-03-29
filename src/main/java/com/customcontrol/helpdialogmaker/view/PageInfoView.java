package com.customcontrol.helpdialogmaker.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.event.PageEvent;
import com.customcontrol.helpdialogmaker.event.PopOverEvent;
import com.customcontrol.helpdialogmaker.session.Session;
import com.customcontrol.helpdialogmaker.viewmodel.PageInfoViewModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PageInfoView implements Initializable {

	@FXML
	HBox						hbRename;
	@FXML
	HBox						hbUnderPage;
	@FXML
	HBox						hbRemove;
	private PageInfoViewModel	pageInfoViewModel;
	private Stage stage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pageInfoViewModel = new PageInfoViewModel();
		hbRemove.setOnMouseClicked(evt -> {
			if (evt.getButton() == MouseButton.PRIMARY) {
				List<FXMLLoader>pages = Session.createInstance().getPages();
				int i = 0;
				for (FXMLLoader fxmlLoader : pages) {
					PageView pageView = fxmlLoader.getController();
					if(pageInfoViewModel.getPage().getIndex() == pageView.getPageViewModel().getPage().getIndex()){
						break;
					}
					i++;
				}
				Session.createInstance().getPages().remove(i);
				stage.fireEvent(new PageEvent(PageEvent.REMOVE,pageInfoViewModel.getPage()));
				hbRemove.fireEvent(new PopOverEvent(PopOverEvent.CLOSE));
			}
		});
		
		hbUnderPage.setOnMouseClicked(evt -> {
			if (evt.getButton() == MouseButton.PRIMARY) {
			}
		});
		
		hbRename.setOnMouseClicked(evt -> {
			if (evt.getButton() == MouseButton.PRIMARY) {
			}
		});
	}

	
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public PageInfoViewModel getPageInfoViewModel() {
		return this.pageInfoViewModel;
	}

}
