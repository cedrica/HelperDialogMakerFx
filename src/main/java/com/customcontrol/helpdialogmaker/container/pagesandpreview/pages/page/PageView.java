package com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page;

import com.preag.core.ui.service.FXMLService;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class PageView extends HBox {

    private StringProperty name = new SimpleStringProperty();

    private IntegerProperty index = new SimpleIntegerProperty();

    private StringProperty html = new SimpleStringProperty("");

    private BooleanProperty nameVisible = new SimpleBooleanProperty(true);

    private ObjectProperty<Button> popOverNode = new SimpleObjectProperty<>();

    private BooleanProperty rootNode = new SimpleBooleanProperty(true);

    private BooleanProperty home = new SimpleBooleanProperty(true);

    private BooleanProperty btnInfoDisable = new SimpleBooleanProperty();

    public PageView() {
        FXMLService.INSTANCE.loadView(this);
    }

    public final StringProperty nameProperty() {
        return this.name;
    }

    public final String getName() {
        return this.nameProperty().get();
    }

    public final void setName(final String name) {
        this.nameProperty().set(name);
    }

    public final BooleanProperty nameVisibleProperty() {
        return this.nameVisible;
    }

    public final boolean isNameVisible() {
        return this.nameVisibleProperty().get();
    }

    public final void setNameVisible(final boolean nameVisible) {
        this.nameVisibleProperty().set(nameVisible);
    }

    public final BooleanProperty homeProperty() {
        return this.home;
    }

    public final boolean isHome() {
        return this.homeProperty().get();
    }

    public final void setHome(final boolean home) {
        this.homeProperty().set(home);
    }

    public final BooleanProperty rootNodeProperty() {
        return this.rootNode;
    }

    public final boolean isRootNode() {
        return this.rootNodeProperty().get();
    }

    public final void setRootNode(final boolean rootNode) {
        this.rootNodeProperty().set(rootNode);
    }

    public final BooleanProperty btnInfoDisableProperty() {
        return this.btnInfoDisable;
    }

    public final boolean isBtnInfoDisable() {
        return this.btnInfoDisableProperty().get();
    }

    public final void setBtnInfoDisable(final boolean btnInfoDisable) {
        this.btnInfoDisableProperty().set(btnInfoDisable);
    }

    public final IntegerProperty indexProperty() {
        return this.index;
    }

    public final int getIndex() {
        return this.indexProperty().get();
    }

    public final void setIndex(final int index) {
        this.indexProperty().set(index);
    }

    public final ObjectProperty<Button> popOverNodeProperty() {
        return this.popOverNode;
    }

    public final Button getPopOverNode() {
        return this.popOverNodeProperty().get();
    }

    public final void setPopOverNode(final Button popOverNode) {
        this.popOverNodeProperty().set(popOverNode);
    }

    public final StringProperty htmlProperty() {
        return this.html;
    }

    public final String getHtml() {
        return this.htmlProperty().get();
    }

    public final void setHtml(final String html) {
        this.htmlProperty().set(html);
    }

}
