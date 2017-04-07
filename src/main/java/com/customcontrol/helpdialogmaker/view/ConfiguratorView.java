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
import com.customcontrol.helpdialogmaker.model.OldConfigurationData;
import com.customcontrol.helpdialogmaker.viewmodel.ConfigurationViewModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
	JFXComboBox<Muster>				cbMuster;
	@FXML
	VBox							vbInfo;
	@FXML
	VBox							vbMustContainer;
	@FXML
	HBox							hbRow;
	@FXML
	VBox							vbMusterContainer;
	@FXML
	JFXButton							btnAddNewRow;
	private ConfigurationViewModel	configurationViewModel;
	private ImageTextMusterView		imageTextMusterView;
	private TextImageMusterView		textImageTextMusterView;
	private ImageMusterView			imageMusterView;
	private TextMusterView			textMusterView;
	private List<Object>			rows;
	private BooleanProperty			musterSelected		= new SimpleBooleanProperty(false);
	private Stage					stage;
	private static int				ROW_INDEX			= 0;
	@FXML
	Button							btnSavePage;
	@FXML
	Button							btnClose;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configurationViewModel = new ConfigurationViewModel();
		rows = new ArrayList<>();
		Helper.roundVBox(vbInfo, 30);
		btnAddNewRow.setOnAction(this::onAddNewRow);
		btnSavePage.setOnAction(this::onSavePage);
		btnClose.setOnAction(this::onClose);
		cbMuster.setPromptText("Muster wählen");
		cbMuster.setItems(FXCollections.observableList(Arrays.asList(Muster.values())));
		configurationViewModel.selectedMusterProperty().bind(cbMuster.getSelectionModel().selectedItemProperty());
		btnAddNewRow.disableProperty().bind(musterSelected.or(cbMuster.getSelectionModel().selectedItemProperty().isNull()));
		btnAddNewRow.getStyleClass().add("button-raised");
		lblTitle.textProperty().bind(configurationViewModel.titleProperty());
	}

	public void initVbMusterContainer(List<OldConfigurationData> oldConfigurationDatas) {
		FXMLLoader fxmlLoader = null;
		for (OldConfigurationData oldConfigurationData : oldConfigurationDatas) {
			Muster muster = oldConfigurationData.getMuster();
			if (muster == Muster.IMAGE) {
				fxmlLoader = Helper.createView(Screens.IMAGE_MUSTER);
				vbMustContainer.getChildren().add(ROW_INDEX, fxmlLoader.getRoot());
				ROW_INDEX++;
				imageMusterView = fxmlLoader.getController();
				imageMusterView.setImageInImageView(oldConfigurationData.getImage());
				rows.add(imageMusterView);
				imageMusterView.setStage(stage);
			} else if (muster == Muster.TEXT) {
				fxmlLoader = Helper.createView(Screens.TEXT_MUSTER);
				vbMustContainer.getChildren().add(ROW_INDEX, fxmlLoader.getRoot());
				ROW_INDEX++;
				textMusterView = fxmlLoader.getController();
				textMusterView.setHtmlEditorText(oldConfigurationData.getHtmlText());
				rows.add(textMusterView);
				textMusterView.setStage(stage);
			} else if (muster == Muster.TEXT_IMAGE) {
				fxmlLoader = Helper.createView(Screens.TEXT_IMAGE_MUSTER);
				vbMustContainer.getChildren().add(ROW_INDEX, fxmlLoader.getRoot());
				ROW_INDEX++;
				textImageTextMusterView = fxmlLoader.getController();
				textImageTextMusterView.setImageInImageView(oldConfigurationData.getImage());
				textImageTextMusterView.setHtmlEditorText(oldConfigurationData.getHtmlText());
				rows.add(textImageTextMusterView);
				textImageTextMusterView.setStage(stage);
			} else if (muster == Muster.IMAGE_TEXT) {
				fxmlLoader = Helper.createView(Screens.IMAGE_TEXT_MUSTER);
				vbMustContainer.getChildren().add(ROW_INDEX, fxmlLoader.getRoot());
				imageTextMusterView = fxmlLoader.getController();
				imageTextMusterView.getImageTextMusterViewModel().setPosInVbMusterContainer(ROW_INDEX);
				imageTextMusterView.setImageInImageView(oldConfigurationData.getImage());
				imageTextMusterView.setHtmlEditorText(oldConfigurationData.getHtmlText());
				ROW_INDEX++;
				imageTextMusterView.setStage(stage);
				rows.add(imageTextMusterView);
			}
		}
	}

	public void createViews(Muster muster) {
		FXMLLoader fxmlLoader;
		if (muster == Muster.IMAGE) {
			fxmlLoader = Helper.createView(Screens.IMAGE_MUSTER);
			vbMustContainer.getChildren().add(ROW_INDEX, fxmlLoader.getRoot());
			ROW_INDEX++;
			imageMusterView = fxmlLoader.getController();
			rows.add(imageMusterView);
			imageMusterView.setStage(stage);
		} else if (muster == Muster.TEXT) {
			fxmlLoader = Helper.createView(Screens.TEXT_MUSTER);
			vbMustContainer.getChildren().add(ROW_INDEX, fxmlLoader.getRoot());
			ROW_INDEX++;
			textMusterView = fxmlLoader.getController();
			rows.add(textMusterView);
			textMusterView.setStage(stage);
		} else if (muster == Muster.TEXT_IMAGE) {
			fxmlLoader = Helper.createView(Screens.TEXT_IMAGE_MUSTER);
			vbMustContainer.getChildren().add(ROW_INDEX, fxmlLoader.getRoot());
			ROW_INDEX++;
			textImageTextMusterView = fxmlLoader.getController();
			rows.add(textImageTextMusterView);
			textImageTextMusterView.setStage(stage);
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

	public void onClose(ActionEvent evt) {
		ROW_INDEX = 0;
		A_ROW_WAS_REMOVED = false;
		this.stage.fireEvent(new WindowEvent(null, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	public void onSavePage(ActionEvent evt) {
		StringBuilder htmlContent = new StringBuilder();
		for (Object row : rows) {
			if (row instanceof ImageTextMusterView) {
				ImageTextMusterView imageTextMusterView = (ImageTextMusterView) row;
				htmlContent.append(imageTextMusterView.getImageTextMusterViewModel().getWholeHtmlContent());
				configurationViewModel.getOldConfigurationDatas().add(imageTextMusterView.getImageTextMusterViewModel().getOldConfigurationData());
			} else if (row instanceof ImageMusterView) {
				ImageMusterView imageMusterView = (ImageMusterView) row;
				htmlContent.append(imageMusterView.getImageMusterViewModel().getWholeHtmlContent());
				configurationViewModel.getOldConfigurationDatas().add(imageMusterView.getImageMusterViewModel().getOldConfigurationData());
			} else if (row instanceof TextImageMusterView) {
				TextImageMusterView textImageMusterView = (TextImageMusterView) row;
				htmlContent.append(textImageMusterView.getTextImageMusterViewModel().getWholeHtmlContent());
				configurationViewModel.getOldConfigurationDatas().add(textImageMusterView.getTextImageMusterViewModel().getOldConfigurationData());
			} else if (row instanceof TextMusterView) {
				TextMusterView textMusterView = (TextMusterView) row;
				htmlContent.append(textMusterView.getTextMusterViewModel().getWholeHtmlContent());
				configurationViewModel.getOldConfigurationDatas().add(textMusterView.getTextMusterViewModel().getOldConfigurationData());
			}
		}
		transferConfigurationToPage(htmlContent);
		onClose(evt);
	}

	private void transferConfigurationToPage(StringBuilder htmlContent) {
		ConfigurationData configurationData = new ConfigurationData();
		configurationData.setHtmlContent(htmlContent.toString());
		stage.fireEvent(new PageEvent(PageEvent.TRANSFER_CONFIG_TO_PAGE, configurationData));
		List<OldConfigurationData> oldConfigurationDatas = configurationViewModel.getOldConfigurationDatas();
		stage.fireEvent(new PageEvent(PageEvent.TRANSFER_OLD_CONFIG, oldConfigurationDatas));
	}

	public void onAddNewRow(ActionEvent evt) {
		Muster muster = configurationViewModel.getSelectedMuster();
		createViews(muster);
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
				int pos;
				if (row instanceof ImageTextMusterView) {
					pos = ((ImageTextMusterView) row).getImageTextMusterViewModel().getPosInVbMusterContainer();
					if (pos == configurationData.getRowIndex()) {
						break;
					}
				} else if (row instanceof ImageMusterView) {
					pos = ((ImageMusterView) row).getImageMusterViewModel().getPosInVbMusterContainer();
					if (pos == configurationData.getRowIndex()) {
						break;
					}
				} else if (row instanceof TextImageMusterView) {
					pos = ((TextImageMusterView) row).getTextImageMusterViewModel().getPosInVbMusterContainer();
					if (pos == configurationData.getRowIndex()) {
						break;
					}
				} else if (row instanceof TextMusterView) {
					pos = ((TextMusterView) row).getTextMusterViewModel().getPosInVbMusterContainer();
					if (pos == configurationData.getRowIndex()) {
						break;
					}
				}
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
					vbMustContainer.getChildren().remove(index - 1);
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
