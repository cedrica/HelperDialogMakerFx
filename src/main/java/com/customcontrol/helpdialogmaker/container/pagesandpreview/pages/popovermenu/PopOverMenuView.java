package com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.popovermenu;

import javax.inject.Singleton;

import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.preag.core.ui.service.FXMLService;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

@Singleton
public class PopOverMenuView extends VBox{


	private IntegerProperty		pageIndex = new SimpleIntegerProperty();
	private ListProperty<ConfigurationData> configuration = new SimpleListProperty<>();
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
    public final ListProperty<ConfigurationData> configurationProperty() {
        return this.configuration;
    }

    public final ObservableList<ConfigurationData> getConfiguration() {
        return this.configurationProperty().get();
    }

    public final void setConfiguration(final ObservableList<ConfigurationData> configuration) {
        this.configurationProperty().set(configuration);
    }
}
