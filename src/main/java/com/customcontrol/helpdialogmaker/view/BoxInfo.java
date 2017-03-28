package com.customcontrol.helpdialogmaker.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.model.Page;
import com.customcontrol.helpdialogmaker.viewmodel.PageInfoViewModel;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;

public class BoxInfo implements Initializable {

	@FXML
	HBox						hbRename;
	@FXML
	HBox						hbUnderPage;
	@FXML
	HBox						hbRemove;
	private PageInfoViewModel	pageInfoViewModel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pageInfoViewModel = new PageInfoViewModel();
		hbRemove.setOnMouseClicked(evt -> {
			if (evt.getButton() == MouseButton.PRIMARY) {
				remove(pageInfoViewModel.getCurrentPage());
			}
		});
		
		hbUnderPage.setOnMouseClicked(evt -> {
			if (evt.getButton() == MouseButton.PRIMARY) {
				addPage(pageInfoViewModel.getCurrentPage());
			}
		});
		
		hbRename.setOnMouseClicked(evt -> {
			if (evt.getButton() == MouseButton.PRIMARY) {
				rename(pageInfoViewModel.getCurrentPage());
			}
		});
	}

	private void rename(Page currentPage) {
		// TODO Auto-generated method stub
		
	}

	private void addPage(Page currentPage) {
		// TODO Auto-generated method stub
		
	}

	private void remove(Page currentPage) {
		System.out.println("Page has been removed");
	}

}
