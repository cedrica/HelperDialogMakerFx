package com.customcontrol.helpdialogmaker.container.pagesandpreview.pages;

import javax.inject.Singleton;

import com.preag.core.ui.service.FXMLService;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.HBox;

@Singleton
public class PagesView extends HBox {

    private BooleanProperty btnPagesDisabled = new SimpleBooleanProperty(true);

    private ObjectProperty<Integer> subPage = new SimpleObjectProperty<>();

    private ObjectProperty<Integer> renamePage = new SimpleObjectProperty<>();

    private ObjectProperty<Integer> removePage = new SimpleObjectProperty<>();

    private ObjectProperty<TreeItem<String>> rootNode = new SimpleObjectProperty<>();

    public PagesView() {
        FXMLService.INSTANCE.loadView(this);
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

    public final ObjectProperty<Integer> subPageProperty() {
        return this.subPage;
    }

    public final Integer getSubPage() {
        return this.subPageProperty().get();
    }

    public final void addSubPage(final Integer parentIndex) {
        this.subPageProperty().set(parentIndex);
    }

    public final ObjectProperty<Integer> renamePageProperty() {
        return this.renamePage;
    }

    public final Integer getRenamePage() {
        return this.renamePageProperty().get();
    }

    public final void setRenamePage(final Integer renamePage) {
        this.renamePageProperty().set(renamePage);
    }

    public final ObjectProperty<Integer> removePageProperty() {
        return this.removePage;
    }

    public final Integer getRemovePage() {
        return this.removePageProperty().get();
    }

    public final void setRemovePage(final Integer removePage) {
        this.removePageProperty().set(removePage);
    }

    public final ObjectProperty<TreeItem<String>> rootNodeProperty() {
        return this.rootNode;
    }

    public final TreeItem<String> getRootNode() {
        return this.rootNodeProperty().get();
    }

    public final void setRootNode(final TreeItem<String> rootNode) {
        this.rootNodeProperty().set(rootNode);
    }

}
