package com.customcontrol.helpdialogmaker;

import com.customcontrol.helpdialogmaker.consts.Screens;
import com.customcontrol.helpdialogmaker.helper.Helper;
import com.customcontrol.helpdialogmaker.view.ContainerView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = Helper.createView(Screens.CONTAINER);
		ContainerView containerView = fxmlLoader.getController();
		containerView.setStage(primaryStage);
		Scene scene = new  Scene(fxmlLoader.getRoot());
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setMaximized(true);
	}
	
	public static void main(String[] args){
		launch(args);
	}

}
