package com.customcontrol.helpdialogmaker.container.pagesandpreview.previews;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class PreviewController implements Initializable{

    @FXML WebView webView;
    @FXML PreviewView previewView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        previewView.htmlContentProperty().addListener((obs,oldVal,newVal)->{
            setHtmlContent(newVal);
        });
    }
    public void setHtmlContent(String content) {
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(content);

    }
    



}
