package com.customcontrol.helpdialogmaker.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import javafx.stage.WindowEvent;

public class ConfiguratorView implements Initializable {

	private static boolean			A_ROW_WAS_REMOVED	= false;
	@FXML
	TextArea						taContent;
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
	private List<Object> rows;
	private BooleanProperty			musterSelected		= new SimpleBooleanProperty(false);
	private Stage					stage;
	private static int				ROW_INDEX			= 0;
	@FXML Button btnSavePage;
	@FXML Button btnClose;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configurationViewModel = new ConfigurationViewModel();
		rows = new ArrayList<>();
		Helper.roundVBox(vbInfo, 30);
		btnAddNewRow.setOnAction(this::onAddNewRow);
		btnSavePage.setOnAction(this::onSavePage);
		btnClose.setOnAction(this::onClose);
		cbMuster.setItems(FXCollections.observableList(Arrays.asList(Muster.values())));
		configurationViewModel.selectedMusterProperty().bind(cbMuster.getSelectionModel().selectedItemProperty());
		btnAddNewRow.disableProperty().bind(musterSelected.or(cbMuster.getSelectionModel().selectedItemProperty().isNull()));
		lblTitle.textProperty().bind(configurationViewModel.titleProperty());

	}
	
	public void onClose(ActionEvent evt) {
		ROW_INDEX = 0;
		A_ROW_WAS_REMOVED = false;
		this.stage.fireEvent(new WindowEvent(null,WindowEvent.WINDOW_CLOSE_REQUEST));
	}
	public void onSavePage(ActionEvent evt) {
		StringBuilder htmlContent = new StringBuilder();
		for (Object row : rows) {
			if(row instanceof ImageTextMusterView){
				htmlContent.append(((ImageTextMusterView)row).getImageTextMusterViewModel().getWholeHtmlContent());
			}
//			else if(row instanceof ImageMusterView){
//				configurationData = ((ImageMusterView)row).getImageMusterViewModel().getConfigurationData();
//				htmlContent.append(configurationData.getHtmlContent());
//			}else if(row instanceof TextImageMusterView){
//				configurationData = ((TextImageMusterView)row).getTextImageMusterViewModel().getConfigurationData();
//				htmlContent.append(configurationData.getHtmlContent());
//			}else if(row instanceof TextMusterView){
//				configurationData = ((TextMusterView)row).getTextMusterViewModel().getConfigurationData();
//				htmlContent.append(configurationData.getHtmlContent());
//			}
		}
		transferConfigurationToPage(htmlContent);
		onClose(evt);
	}
	
	private void transferConfigurationToPage(StringBuilder htmlContent) {
		ConfigurationData configurationData = new ConfigurationData();
		configurationData.setHtmlContent(htmlContent.toString());
		stage.fireEvent(new PageEvent(PageEvent.TRANSFER_CONFIG_TO_PAGE, configurationData));
	}
	public void onAddNewRow(ActionEvent evt) {
		FXMLLoader fxmlLoader = null;
		Muster muster = configurationViewModel.getSelectedMuster();
		if (muster == Muster.IMAGE) {
			fxmlLoader = Helper.createView(Screens.IMAGE_MUSTER);
			vbMustContainer.getChildren().add(ROW_INDEX, fxmlLoader.getRoot());
			ROW_INDEX++;
			imageMusterView = fxmlLoader.getController();
			rows.add(imageMusterView);
		} else if (muster == Muster.TEXT) {
			fxmlLoader = Helper.createView(Screens.TEXT_MUSTER);
			vbMustContainer.getChildren().add(ROW_INDEX, fxmlLoader.getRoot());
			ROW_INDEX++;
			textMusterView = fxmlLoader.getController();
			rows.add(textMusterView);
		} else if (muster == Muster.TEXT_IMAGE) {
			fxmlLoader = Helper.createView(Screens.TEXT_IMAGE_MUSTER);
			vbMustContainer.getChildren().add(ROW_INDEX, fxmlLoader.getRoot());
			ROW_INDEX++;
			textImageTextMusterView = fxmlLoader.getController();
			rows.add(textImageTextMusterView);
		} else if (muster == Muster.IMAGE_TEXT) {
			fxmlLoader = Helper.createView(Screens.IMAGE_TEXT_MUSTER);
			vbMustContainer.getChildren().add(ROW_INDEX, fxmlLoader.getRoot());
			imageTextMusterView = fxmlLoader.getController();
			imageTextMusterView.getImageTextMusterViewModel().setPosInVbMusterContainer(ROW_INDEX);
			ROW_INDEX++;
			imageTextMusterView.setStage(stage);
			rows.add(imageTextMusterView);
		}
	}


	public ConfigurationViewModel getConfigurationViewModel() {
		return configurationViewModel;
	}


	public void setConfigurationViewModel(ConfigurationViewModel configurationViewModel) {
		this.configurationViewModel = configurationViewModel;
	}


	public Stage getStage() {
		return stage;
	}


	public void setStage(Stage stage) {
		this.stage = stage;
		stage.addEventFilter(PageEvent.REMOVE_ROW, evt -> {
			ConfigurationData configurationData = evt.getConfigurationData();
			int toRemoved = 0;
			for (Object row : rows) {
				int  pos;
				if(row instanceof ImageTextMusterView){
					pos = ((ImageTextMusterView)row).getImageTextMusterViewModel().getPosInVbMusterContainer();
					if(pos == configurationData.getRowIndex()){
						break;
					}
				}
//				else if(row instanceof ImageMusterView){
//					myconfig = ((ImageMusterView)row).getImageMusterViewModel().getConfigurationData();
//					if(myconfig.getRowIndex() == configurationData.getRowIndex()){
//						break;
//					}
//				}else if(row instanceof TextImageMusterView){
//					myconfig = ((TextImageMusterView)row).getTextImageMusterViewModel().getConfigurationData();
//					if(myconfig.getRowIndex() == configurationData.getRowIndex()){
//						break;
//					}
//				}else if(row instanceof TextMusterView){
//					myconfig = ((TextMusterView)row).getTextMusterViewModel().getConfigurationData();
//					if(myconfig.getRowIndex() == configurationData.getRowIndex()){
//						break;
//					}
//				}
				toRemoved++;
			}
			rows.remove(toRemoved);
			if (vbMustContainer.getChildren().size() == 1) {
				vbMustContainer.getChildren().remove(0);
				ROW_INDEX--;
				return;
			}
			if (A_ROW_WAS_REMOVED) {
				int index = configurationData.getRowIndex() - 1;
				if (index >= 0 && index <= vbMustContainer.getChildren().size() - 1) {
					vbMustContainer.getChildren().remove(index);
				} else if (index == vbMustContainer.getChildren().size()) {
					vbMustContainer.getChildren().remove(index-1);
				} else if (vbMustContainer.getChildren().size() > 0) {
					vbMustContainer.getChildren().remove(0);
				}
			} else {
				vbMustContainer.getChildren().remove(configurationData.getRowIndex());
				A_ROW_WAS_REMOVED = true;
			}
			ROW_INDEX--;
		});
	}


}
