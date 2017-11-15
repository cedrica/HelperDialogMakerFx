package com.customcontrol.helpdialogmaker.container.pagesandpreview;

import javax.inject.Singleton;

import org.controlsfx.control.MasterDetailPane;

import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.PagesView;
import com.customcontrol.helpdialogmaker.localization.Localization;
import com.preag.core.ui.service.FXMLService;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
@Singleton
public class PagesAndPreview extends MasterDetailPane{
	private BooleanProperty btnPagesDisabled = new SimpleBooleanProperty(true);
	private StringProperty htmlContent = new SimpleStringProperty();
	private ObjectProperty<Node> placeHolder = new SimpleObjectProperty<>();
	private ObjectProperty<PagesView> pagesView = new SimpleObjectProperty<>();
	
	public  PagesAndPreview(){
	    FXMLService.INSTANCE.loadView(this,Localization.getDefault());
	}
	
	public final BooleanProperty btnPagesDisabledProperty() {
		return this.btnPagesDisabled;
	}
	

	
	public final boolean isBtnPagesDisabled() {
		return this.btnPagesDisabledProperty().get();
	}
	

	
	public final void setBtnPagesDisabled(final boolean btnPagesDisabled) {
		this.btnPagesDisabledProperty().set(btnPagesDisabled);
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

    
    public final ObjectProperty<Node> placeHolderProperty() {
        return this.placeHolder;
    }
    

    
    public final Node getPlaceHolder() {
        return this.placeHolderProperty().get();
    }
    

    
    public final void setPlaceHolder(final Node placeHolder) {
        this.placeHolderProperty().set(placeHolder);
    }

    
    public final ObjectProperty<PagesView> pagesViewProperty() {
        return this.pagesView;
    }
    

    
    public final PagesView getPagesView() {
        return this.pagesViewProperty().get();
    }
    

    
    public final void setPagesView(final PagesView pagesView) {
        this.pagesViewProperty().set(pagesView);
    }
    


	
}
