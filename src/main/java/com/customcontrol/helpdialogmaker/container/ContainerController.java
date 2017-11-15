package com.customcontrol.helpdialogmaker.container;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.container.events.ContainerViewEvent;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.PagesAndPreview;
import com.preag.core.ui.utils.FileUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.DirectoryChooser;

public class ContainerController implements Initializable {

	@FXML
	ContainerView rootNode;
	@FXML
	PagesAndPreview pagesAndPreview;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootNode.setPagesAndPreview(pagesAndPreview);
	}

	public void onSave(ActionEvent evt) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File location = directoryChooser.showDialog(pagesAndPreview.getScene().getWindow());
		if (location == null) {
			return;
		}
		File from = new File(System.getProperty("user.dir") + "/additionalResources/images");
		for (File file : from.listFiles()) {
			FileUtil.saveFileAsTemp(file);
		}
	}


	public void onExport() {
		rootNode.fireEvent(new ContainerViewEvent(ContainerViewEvent.FILE_SUCESSFULLY_EXPORTED));
	}

	public void onSaveAsTemplate() {
		rootNode.fireEvent(new ContainerViewEvent(ContainerViewEvent.SAVE_AS_TEMPLATE));
	}

	public void onImportXmlTemplate() {
		rootNode.fireEvent(new ContainerViewEvent(ContainerViewEvent.IMPORT_XML_TEMPLATE));

	}

}
