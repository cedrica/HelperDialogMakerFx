package com.customcontrol.helpdialogmaker.helper;

import java.util.Optional;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class DialogHelper {

	public static Stage dialogStage(Parent content, Modality modality, String title, Window owner) {
		Stage stage = new Stage();
		stage.setScene(new Scene(content));
		stage.setTitle(title);
		stage.initModality(modality);
		stage.initOwner(owner);
		inheritStyle(stage, owner);
		stage.show();
		return stage;
	}

	private static void inheritStyle(final Window dialogWindow, final Window parentWindow) {
		if (parentWindow != null) {
			final Stage parentStage = (Stage) parentWindow; // Set the Current Application Icon
			final Stage dialogStage = (Stage) dialogWindow;
			dialogStage.getIcons().setAll(parentStage.getIcons()); // Inherit the parent style
			dialogWindow.getScene().getStylesheets().setAll(parentWindow.getScene().getStylesheets());
		}
	}


	public static Optional<ButtonType> confirm(String title, String header, String content, ButtonType saveButtonTyp, ButtonType cancelButtonTyp, Window owner) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.initOwner(owner);
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(
		   DialogHelper.class.getResource("/com/preag/pepexcel/css/Alert.css").toExternalForm());
		dialogPane.getStyleClass().add("alert");
		alert.getButtonTypes().setAll(cancelButtonTyp, saveButtonTyp);
		Optional<ButtonType> result = alert.showAndWait();
		return result;
	}

	public static void error(String title, String content, Window owner, ButtonType ok) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(owner);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.getButtonTypes().setAll(ok);
		alert.showAndWait();
	}


	public static void info(String headerText, String content, ButtonType ok, Window owner) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(owner);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setHeaderText(headerText);
		alert.setContentText(content);
		alert.getButtonTypes().setAll(ok);
		alert.showAndWait();
	}
}
