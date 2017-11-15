package com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.popovermenu;

import javax.inject.Singleton;

import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.customcontrol.helpdialogmaker.localization.Localization;
import com.preag.core.ui.service.FXMLService;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

@Singleton
public class PopOverMenuView extends VBox {

    private IntegerProperty                 pageIndex     = new SimpleIntegerProperty();

    private StringProperty                  pageName      = new SimpleStringProperty();

    private ListProperty<ConfigurationData> configuration = new SimpleListProperty<>();

    public PopOverMenuView() {
        FXMLService.INSTANCE.loadView(this,Localization.getDefault());
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

    public final StringProperty pageNameProperty() {
        return this.pageName;
    }

    public final String getPageName() {
        return this.pageNameProperty().get();
    }

    public final void setPageName(final String pageName) {
        this.pageNameProperty().set(pageName);
    }

}
