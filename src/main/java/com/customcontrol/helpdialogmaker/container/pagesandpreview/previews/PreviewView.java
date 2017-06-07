package com.customcontrol.helpdialogmaker.container.pagesandpreview.previews;

import javax.inject.Singleton;

import com.preag.core.ui.service.FXMLService;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.HBox;
@Singleton
public class PreviewView extends HBox {
    private StringProperty htmlContent = new SimpleStringProperty();
    public PreviewView(){
        FXMLService.INSTANCE.loadView(this);
    }
    
    public final StringProperty htmlContentProperty() {
        return this.htmlContent;
    }
    

    
    public final String getHtmlContent() {
        return this.htmlContentProperty().get();
    }
    

    
    public final void setHtmlContent(final String htmlContent) {
        this.htmlContentProperty().set(htmlContent);
    }

}
