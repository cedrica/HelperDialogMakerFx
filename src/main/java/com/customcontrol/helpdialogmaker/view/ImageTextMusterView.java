package com.customcontrol.helpdialogmaker.view;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;

import com.customcontrol.helpdialogmaker.event.PageEvent;
import com.customcontrol.helpdialogmaker.model.ConfigurationData;
import com.customcontrol.helpdialogmaker.viewmodel.ImageTextMusterViewModel;

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

public class ImageTextMusterView implements Initializable {

	@FXML
	Button		btnChooseImage;
	@FXML
	ImageView	ivLoadedImage;
	@FXML
	Button		btnChangeImage;
	@FXML
	VBox		vbLoadedImage;
	@FXML
	Button		btnSave;
	@FXML
	Button		btnEdit;
	@FXML
	HTMLEditor	htmlEditor;
	@FXML
	WebView		webView;

	private ImageTextMusterViewModel	imageTextMusterViewModel;
	private byte[]						imageBytes;
	private WebEngine					webEngine;
	@FXML
	HBox								hbViewer;
	@FXML
	HBox								hbEditor;
	@FXML Button btnRemoveRow;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imageTextMusterViewModel = new ImageTextMusterViewModel();
		btnChooseImage.setOnAction(this::onFileChooserAction);
		btnSave.setOnAction(this::onSave);
		btnEdit.setOnAction(this::onEdit);
		btnRemoveRow.setOnAction(this::onRemoveRow);
		webEngine = webView.getEngine();
		btnSave.setDisable(true);
		btnEdit.setVisible(false);
	}

	public void onFileChooserAction(ActionEvent evt) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Bild auswählen");
		File file = chooser.showOpenDialog(btnChooseImage.getScene().getWindow());
		if (file != null) {
			try {
				imageBytes = FileUtils.readFileToByteArray(file);
				imageTextMusterViewModel.setImageBytes(imageBytes);
				imageTextMusterViewModel.setImageName(file.getName());
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

	public void onRemoveRow(ActionEvent evt){
		btnRemoveRow.fireEvent(new PageEvent(PageEvent.REMOVE_ROW, new ConfigurationData(imageTextMusterViewModel.getPosInVbMusterContainer())));
	}
	
	public void onSave(ActionEvent evt) {
		imageTextMusterViewModel.setHtmlText(htmlEditor.getHtmlText());
		String totalContent = imageTextMusterViewModel.save(btnSave.getScene().getWindow());
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

	
	public ImageTextMusterViewModel getImageTextMusterViewModel() {
		return imageTextMusterViewModel;
	}

	
	public void setImageTextMusterViewModel(ImageTextMusterViewModel imageTextMusterViewModel) {
		this.imageTextMusterViewModel = imageTextMusterViewModel;
	}
	
	
}
