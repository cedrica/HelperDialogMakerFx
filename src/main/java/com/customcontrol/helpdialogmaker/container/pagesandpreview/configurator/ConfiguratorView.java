package com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator;

import javax.inject.Singleton;

import com.customcontrol.helpdialogmaker.enums.Muster;
import com.preag.core.ui.service.FXMLService;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

@Singleton
public class ConfiguratorView extends VBox {

    private ObjectProperty<Muster> selectedMuster = new SimpleObjectProperty<>();
    private StringProperty title = new SimpleStringProperty();
    private ObjectProperty<Node> musterComponent = new SimpleObjectProperty<>();
    private ObjectProperty<Integer> musterIndex = new SimpleObjectProperty<>();
    private IntegerProperty pageIndex = new SimpleIntegerProperty();
    
    public ConfiguratorView() {
        FXMLService.INSTANCE.loadView(this);
    }

    public final ObjectProperty<Muster> selectedMusterProperty() {
        return this.selectedMuster;
    }

    public final Muster getSelectedMuster() {
        return this.selectedMusterProperty().get();
    }

    public final void setSelectedMuster(final Muster selectedMuster) {
        this.selectedMusterProperty().set(selectedMuster);
    }

    public final StringProperty titleProperty() {
        return this.title;
    }

    public final String getTitle() {
        return this.titleProperty().get();
    }

    public final void setTitle(final String title) {
        this.titleProperty().set(title);
    }

    
    public final ObjectProperty<Node> musterComponentProperty() {
        return this.musterComponent;
    }
    

    
    public final Node getMusterComponent() {
        return this.musterComponentProperty().get();
    }
    

    
    public final void setMusterComponent(final Node musterComponent) {
        this.musterComponentProperty().set(musterComponent);
    }


    public void removeMuster(int musterIndex) {
        setMusterIndex(musterIndex);
    }

    
    public final ObjectProperty<Integer> musterIndexProperty() {
        return this.musterIndex;
    }
    

    
    public final Integer getMusterIndex() {
        return this.musterIndexProperty().get();
    }
    

    
    public final void setMusterIndex(final Integer musterIndex) {
        this.musterIndexProperty().set(musterIndex);
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