package com.customcontrol.helpdialogmaker.view;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.consts.Screens;
import com.customcontrol.helpdialogmaker.enums.Muster;
import com.customcontrol.helpdialogmaker.event.PageEvent;
import com.customcontrol.helpdialogmaker.helper.Helper;
import com.customcontrol.helpdialogmaker.model.ConfigurationData;
import com.customcontrol.helpdialogmaker.viewmodel.ConfigurationViewModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
	private ImageTextMusterView		imageTextMusterView;
	private TextImageMusterView		textImageTextMusterView;
	private ImageMusterView			imageMusterView;
	private TextMusterView			textMusterView;
	private BooleanProperty			musterSelected	= new SimpleBooleanProperty(false);
	private Stage stage;
	private static int ROW_INDEX = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configurationViewModel = new ConfigurationViewModel();
		Helper.roundVBox(vbInfo, 30);
		btnAddNewRow.setOnAction(this::onAddNewRow);
		cbMuster.setItems(FXCollections.observableList(Arrays.asList(Muster.values())));
		configurationViewModel.selectedMusterProperty().bind(cbMuster.getSelectionModel().selectedItemProperty());
		btnAddNewRow.disableProperty().bind(musterSelected.or(cbMuster.getSelectionModel().selectedItemProperty().isNull()));
		lblTitle.textProperty().bind(configurationViewModel.titleProperty());
		stage.addEventFilter(PageEvent.REMOVE_ROW, evt ->{
			ConfigurationData configurationData = evt.getConfigurationData();
			vbMustContainer.getChildren().remove(configurationData.getRowIndex());
		});
	}

	public void onAddNewRow(ActionEvent evt) {
		FXMLLoader fxmlLoader = null;
		Muster muster = configurationViewModel.getSelectedMuster();
		if (muster == Muster.IMAGE) {
			fxmlLoader = Helper.createView(Screens.IMAGE_MUSTER);
			vbMustContainer.getChildren().add(ROW_INDEX++,fxmlLoader.getRoot());
			imageMusterView = fxmlLoader.getController();
			
		} else if (muster == Muster.TEXT) {
			fxmlLoader = Helper.createView(Screens.TEXT_MUSTER);
			vbMustContainer.getChildren().add(ROW_INDEX++,fxmlLoader.getRoot());
			textMusterView = fxmlLoader.getController();
		} else if (muster == Muster.TEXT_IMAGE) {
			fxmlLoader = Helper.createView(Screens.TEXT_IMAGE_MUSTER);
			vbMustContainer.getChildren().add(ROW_INDEX++,fxmlLoader.getRoot());
			textImageTextMusterView = fxmlLoader.getController();
		} else if (muster == Muster.IMAGE_TEXT) {
			fxmlLoader = Helper.createView(Screens.IMAGE_TEXT_MUSTER);
			vbMustContainer.getChildren().add(ROW_INDEX++,fxmlLoader.getRoot());
			imageTextMusterView = fxmlLoader.getController();
			imageTextMusterView.getImageTextMusterViewModel().setPosInVbMusterContainer(ROW_INDEX);
		}
	}

	
	public ConfigurationViewModel getConfigurationViewModel() {
		return configurationViewModel;
	}

	
	public void setConfigurationViewModel(ConfigurationViewModel configurationViewModel) {
		this.configurationViewModel = configurationViewModel;
	}

	public void setStage(Stage stage2) {
		// TODO Auto-generated method stub
		
	}
	
	

}
