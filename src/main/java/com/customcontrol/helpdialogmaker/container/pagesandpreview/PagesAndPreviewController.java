package com.customcontrol.helpdialogmaker.container.pagesandpreview;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.PagesView;

public class PagesAndPreviewController implements Initializable {

    @FXML
    PagesAndPreview pagesAndPreview;


    @FXML
    HBox hbPlaceHolder;


    @FXML PagesView pagesView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pagesAndPreview.placeHolderProperty().addListener((obs, oldVal, newVal) -> {
            hbPlaceHolder.getChildren().clear();
            hbPlaceHolder.getChildren().add(newVal);
        });
        pagesAndPreview.setPagesView(pagesView);
    }

}
