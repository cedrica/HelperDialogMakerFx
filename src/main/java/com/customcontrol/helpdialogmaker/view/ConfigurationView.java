package com.customcontrol.helpdialogmaker.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.helper.Helper;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class ConfigurationView implements Initializable{

	@FXML TextArea taContent;
	@FXML Button btnLoadImage;
	@FXML Label lblTitle;
	@FXML Label lblInfo;
	@FXML ComboBox <Muster>  cbMuster;
	@FXML VBox vbInfo;
	@FXML VBox vbMustContainer;
	@FXML HBox hbRow;
	@FXML VBox vbMusterContainer;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Helper.roundVBox(vbInfo, 30);
	}

}
