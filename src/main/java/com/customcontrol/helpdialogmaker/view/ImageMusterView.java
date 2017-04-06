package com.customcontrol.helpdialogmaker.view;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;

import com.customcontrol.helpdialogmaker.enums.Muster;
import com.customcontrol.helpdialogmaker.event.PageEvent;
import com.customcontrol.helpdialogmaker.model.ConfigurationData;
import com.customcontrol.helpdialogmaker.model.OldConfigurationData;
import com.customcontrol.helpdialogmaker.viewmodel.ImageMusterViewModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImageMusterView implements Initializable {

	@FXML Button btnRemoveRow;
	@FXML Button btnSave;
	@FXML Button btnEdit;
	@FXML HBox hbViewer;
	@FXML WebView webView;
	@FXML HBox hbEditor;
	@FXML VBox vbLoadedImage;
	@FXML ImageView ivLoadedImage;
	@FXML Button btnChangeImage;
	@FXML Button btnChooseImage;

	private ImageMusterViewModel	ImageMusterViewModel;
	private byte[]						imageBytes;
	private WebEngine					webEngine;
	private Stage stage;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ImageMusterViewModel = new ImageMusterViewModel();
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
				imageBytes = FileUtils.readFileToByteArray(file);
				ImageMusterViewModel.setImageBytes(imageBytes);
				ImageMusterViewModel.setImageName(file.getName());
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
		ImageMusterViewModel.setImageBytes(bytes);
		InputStream is =  new ByteArrayInputStream(bytes);
		Image image = new Image(is);
		ivLoadedImage.setImage(image);
		vbLoadedImage.setVisible(true);
		btnChooseImage.setVisible(false);
		btnSave.setDisable(false);
	}
	

	public void onRemoveRow(ActionEvent evt){
		stage.fireEvent(new PageEvent(PageEvent.REMOVE_ROW, new ConfigurationData(ImageMusterViewModel.getPosInVbMusterContainer())));
	}
	
	public void onSave(ActionEvent evt) {
		String totalContent = ImageMusterViewModel.save(btnSave.getScene().getWindow());
		OldConfigurationData oldConfigurationData = new OldConfigurationData();
		oldConfigurationData.setHtmlText(ImageMusterViewModel.getHtmlText());
		oldConfigurationData.setImage(ImageMusterViewModel.getImageBytes());
		oldConfigurationData.setMuster(Muster.IMAGE);
		ImageMusterViewModel.setOldConfigurationData(oldConfigurationData);
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

	
	
	public Stage getStage() {
		return stage;
	}

	
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	
	public ImageMusterViewModel getImageMusterViewModel() {
		return ImageMusterViewModel;
	}

	
	public void setImageMusterViewModel(ImageMusterViewModel imageMusterViewModel) {
		ImageMusterViewModel = imageMusterViewModel;
	}


}
