package com.customcontrol.helpdialogmaker.container;

import javax.inject.Singleton;

import com.customcontrol.helpdialogmaker.container.pagesandpreview.PagesAndPreview;
import com.preag.core.ui.service.FXMLService;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.VBox;
@Singleton
public class ContainerView extends VBox{
    private BooleanProperty clearPlaceHolder = new SimpleBooleanProperty();
    private ObjectProperty<PagesAndPreview> pagesAndPreview = new SimpleObjectProperty<>();
    
    public  ContainerView(){
        FXMLService.INSTANCE.loadView(this);
    }

    
    public final BooleanProperty clearPlaceHolderProperty() {
        return this.clearPlaceHolder;
    }
    

    
    public final boolean isClearPlaceHolder() {
        return this.clearPlaceHolderProperty().get();
    }
    

    
    public final void setClearPlaceHolder(final boolean clearPlaceHolder) {
        this.clearPlaceHolderProperty().set(clearPlaceHolder);
    }


    
    public final ObjectProperty<PagesAndPreview> pagesAndPreviewProperty() {
        return this.pagesAndPreview;
    }
    


    
    public final PagesAndPreview getPagesAndPreview() {
        return this.pagesAndPreviewProperty().get();
    }
    

    
    public final void setPagesAndPreview(final PagesAndPreview pagesAndPreview) {
        this.pagesAndPreviewProperty().set(pagesAndPreview);
    }
    

}
