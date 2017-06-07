package com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.text;

import java.net.URL;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.enums.Muster;
import com.customcontrol.helpdialogmaker.event.PageEvent;
import com.customcontrol.helpdialogmaker.model.OldConfigurationData;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class TextMusterController  implements Initializable{

	@FXML Button btnRemoveRow;
	@FXML Button btnSave;
	@FXML Button btnEdit;
	@FXML HBox hbViewer;
	@FXML WebView webView;
	@FXML HBox hbEditor;
	@FXML HTMLEditor htmlEditor;

	private WebEngine					webEngine;
	private Stage stage;
    @FXML TextMusterView textMusterView;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnSave.setOnAction(this::onSave);
		btnEdit.setOnAction(this::onEdit);
		btnRemoveRow.setOnAction(this::onRemoveRow);
		webEngine = webView.getEngine();
		btnEdit.setVisible(false);
		
		textMusterView.htmlEditorTextProperty().addListener((obs,oldVal,newVal)->{
		    setHtmlEditorText(newVal);
		});
	}


	
	public void setHtmlEditorText(String text){
		htmlEditor.setHtmlText(text);
	}

	public void onRemoveRow(ActionEvent evt){
		stage.fireEvent(new PageEvent(PageEvent.REMOVE_CONFIGURATION,textMusterView.getPosInVbMusterContainer(),false));
	}
	
	public void onSave(ActionEvent evt) {
		textMusterView.setHtmlText(htmlEditor.getHtmlText());
		String totalContent = textMusterView.save(btnSave.getScene().getWindow());
		OldConfigurationData oldConfigurationData = new OldConfigurationData();
		oldConfigurationData.setHtmlText(textMusterView.getHtmlText());
		oldConfigurationData.setMuster(Muster.TEXT);
		textMusterView.setOldConfigurationData(oldConfigurationData);
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



	
	public TextMusterView getTextMusterViewModel() {
		return textMusterView;
	}



	
	public void setTextMusterViewModel(TextMusterView textMusterView) {
		this.textMusterView = textMusterView;
	}

	

}