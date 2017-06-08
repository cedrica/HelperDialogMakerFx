package com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.textimage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;

import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.customcontrol.helpdialogmaker.enums.Muster;
import com.customcontrol.helpdialogmaker.event.PopOverEvent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

public class TextImageMusterController  implements Initializable{

	@FXML Button btnRemoveRow;
	@FXML Button btnSave;
	@FXML Button btnEdit;
	@FXML HBox hbViewer;
	@FXML WebView webView;
	@FXML HBox hbEditor;
	@FXML HTMLEditor htmlEditor;
	@FXML VBox vbLoadedImage;
	@FXML ImageView ivLoadedImage;
	@FXML Button btnChangeImage;
	@FXML Button btnChooseImage;
	private WebEngine					webEngine;
    @FXML TextImageMusterView textImageMusterView;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnChooseImage.setOnAction(this::onFileChooserAction);
		btnSave.setOnAction(this::onSave);
		btnEdit.setOnAction(this::onEdit);
		btnChangeImage.setOnAction(this::onChangeImage);
		btnRemoveRow.setOnAction(this::onRemoveRow);
		webEngine = webView.getEngine();
		btnSave.setDisable(true);
		btnEdit.setVisible(false);
	}

	public void onChangeImage(ActionEvent evt) {
		onFileChooserAction(evt);
	}
	
	public void onFileChooserAction(ActionEvent evt) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Bild auswählen");
		File file = chooser.showOpenDialog(btnChooseImage.getScene().getWindow());
		if (file != null) {
			try {
				textImageMusterView.setImageBytes(FileUtils.readFileToByteArray(file));
				textImageMusterView.setImageName(file.getName());
				InputStream is = FileUtils.openInputStream(file);
				Image image = new Image(is);
				ivLoadedImage.setImage(image);
				vbLoadedImage.setVisible(true);
				btnChooseImage.setVisible(false);
				btnSave.setDisable(false);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			btnSave.setDisable(true);
		}
	}

	public void setImageInImageView(byte[] bytes) {
		textImageMusterView.setImageBytes(bytes);
		InputStream is =  new ByteArrayInputStream(bytes);
		Image image = new Image(is);
		ivLoadedImage.setImage(image);
		vbLoadedImage.setVisible(true);
		btnChooseImage.setVisible(false);
		btnSave.setDisable(false);
	}
	
	public void setHtmlEditorText(String text){
		htmlEditor.setHtmlText(text);
	}

	public void onRemoveRow(ActionEvent evt){
	    textImageMusterView.fireEvent(new PopOverEvent(PopOverEvent.REMOVE_CONFIGURATION, textImageMusterView.getPosInVbMusterContainer(),false));
	}
	
	public void onSave(ActionEvent evt) {
		textImageMusterView.setHtmlText(htmlEditor.getHtmlText());
		String totalContent = textImageMusterView.save(btnSave.getScene().getWindow());
		ConfigurationData configurationData = new ConfigurationData();
		configurationData.setHtmlText(textImageMusterView.getHtmlText());
		configurationData.setImage(textImageMusterView.getImageBytes());
		configurationData.setMuster(Muster.TEXT_IMAGE);
		textImageMusterView.setOldConfigurationData(configurationData);
		if (totalContent != null){
			hbEditor.setVisible(false);
			hbViewer.setVisible(true);
			webEngine.loadContent(totalContent);
			btnEdit.setVisible(true);
			btnSave.setVisible(false);
		}
			
	}

	public void onEdit(ActionEvent evt) {
		hbEditor.setVisible(true);
		hbViewer.setVisible(false);
		btnEdit.setVisible(false);
		btnSave.setVisible(true);
	}


}
