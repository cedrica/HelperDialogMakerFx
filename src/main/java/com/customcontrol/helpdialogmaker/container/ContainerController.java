package com.customcontrol.helpdialogmaker.container;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.customcontrol.helpdialogmaker.container.events.ContainerViewEvent;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.PagesAndPreview;
import com.customcontrol.helpdialogmaker.helper.Helper;
import com.preag.core.ui.utils.FileUtil;
import com.preag.core.ui.utils.dialog.Dialogs;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
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

	public static void saveToFile(Image image) {
		File outputFile = new File("F:/test/");
		BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
		try {
			ImageIO.write(bImage, "png", outputFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void onExport() {
		if (rootNode.getPagesAndPreview() != null && rootNode.getPagesAndPreview().getPagesView() != null
				&& rootNode.getPagesAndPreview().getPagesView().getPageConfigutaion() != null
				&& rootNode.getPagesAndPreview().getPagesView().getPageConfigutaion().getValue() != null
				&& rootNode.getPagesAndPreview().getPagesView().getPageConfigutaion().getValue().getValue() != null
				&& rootNode.getPagesAndPreview().getPagesView().getPageConfigutaion().getValue().getValue()
						.size() > 0) {
			Helper.saveFileInTempdirAndOpen(
					ContainerService.builtHtmlPage(rootNode.getPagesAndPreview().getPagesView().getRootNode()),"help.html");
			rootNode.fireEvent(new ContainerViewEvent(ContainerViewEvent.FILE_SUCESSFULLY_EXPORTED));
		}else{
			Dialog<ButtonType> info = Dialogs.info("Keine Konfiguration registriert. Erzeugen Sie eine Seite um diesen Vorgang durchführen zu können.", rootNode.getScene().getWindow());
			info.showAndWait();
		}

	}

	public void onSaveAsTemplate() {
		rootNode.fireEvent(new ContainerViewEvent(ContainerViewEvent.SAVE_AS_TEMPLATE));
	}

}
