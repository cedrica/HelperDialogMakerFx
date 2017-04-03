package com.customcontrol.helpdialogmaker.view;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.consts.Screens;
import com.customcontrol.helpdialogmaker.enums.Muster;
import com.customcontrol.helpdialogmaker.helper.Helper;
import com.customcontrol.helpdialogmaker.viewmodel.ConfigurationViewModel;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

public class ConfiguratorView implements Initializable {

	@FXML
	TextArea						taContent;
	@FXML
	Button							btnLoadImage;
	@FXML
	Label							lblTitle;
	@FXML
	Label							lblInfo;
	@FXML
	ComboBox<Muster>				cbMuster;
	@FXML
	VBox							vbInfo;
	@FXML
	VBox							vbMustContainer;
	@FXML
	HBox							hbRow;
	@FXML
	VBox							vbMusterContainer;
	@FXML
	Button							btnAddNewRow;
	private ConfigurationViewModel	configurationViewModel;
	private ImageTextMuster			imageTextMuster;
	private TextImageMuster			textImageTextMuster;
	private ImageMuster				imageMuster;
	private TextMuster				textMuster;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configurationViewModel = new ConfigurationViewModel();
		Helper.roundVBox(vbInfo, 30);
		btnAddNewRow.setOnAction(this::onAddNewRow);
		cbMuster.setItems(FXCollections.observableList(Arrays.asList(Muster.values())));
		configurationViewModel.selectedMusterProperty().bind(cbMuster.getSelectionModel().selectedItemProperty());
	}

	public void setStage(Stage stage) {
	}

	public void onAddNewRow(ActionEvent evt) {
		FXMLLoader fxmlLoader = null;
		Muster muster = configurationViewModel.getSelectedMuster();
		if (muster == Muster.IMAGE) {
			fxmlLoader = Helper.createView(Screens.IMAGE_MUSTER);
			vbMustContainer.getChildren().add(fxmlLoader.getRoot());
			imageMuster = fxmlLoader.getController();
		} else if (muster == Muster.TEXT) {
			fxmlLoader = Helper.createView(Screens.TEXT_MUSTER);
			vbMustContainer.getChildren().add(fxmlLoader.getRoot());
			textMuster = fxmlLoader.getController();
		} else if (muster == Muster.TEXT_IMAGE) {
			fxmlLoader = Helper.createView(Screens.TEXT_IMAGE_MUSTER);
			vbMustContainer.getChildren().add(fxmlLoader.getRoot());
			textImageTextMuster = fxmlLoader.getController();
		} else if (muster == Muster.IMAGE_TEXT) {
			fxmlLoader = Helper.createView(Screens.IMAGE_TEXT_MUSTER);
			vbMustContainer.getChildren().add(fxmlLoader.getRoot());
			imageTextMuster = fxmlLoader.getController();
		}
	}

}
