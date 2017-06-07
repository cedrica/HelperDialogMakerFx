package com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.popovermenu;

import javax.inject.Singleton;

import com.preag.core.ui.service.FXMLService;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.VBox;

@Singleton
public class PopOverMenuView extends VBox{


	private IntegerProperty		pageIndex = new SimpleIntegerProperty();

	public PopOverMenuView() {
	    FXMLService.INSTANCE.loadView(this);
	}
    
    public final IntegerProperty pageIndexProperty() {
        return this.pageIndex;
    }

    public final int getPageIndex() {
        return this.pageIndexProperty().get();
    }
    
    public final void setPageIndex(final int pageIndex) {
        this.pageIndexProperty().set(pageIndex);
    }

}
