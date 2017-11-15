package com.customcontrol.helpdialogmaker.container.pagesandpreview.pages;

import javax.inject.Singleton;

import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page.PageView;
import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.customcontrol.helpdialogmaker.localization.Localization;
import com.preag.core.ui.service.FXMLService;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

@Singleton
public class PagesView extends HBox {

    private BooleanProperty btnPagesDisabled = new SimpleBooleanProperty(true);

    private ObjectProperty<Integer> subPageIndex = new SimpleObjectProperty<>();
    
    private ObjectProperty<Boolean> clearParentTree = new SimpleObjectProperty<>(false);
    
    private ObjectProperty<Integer> renamePage = new SimpleObjectProperty<>();

    private ObjectProperty<Integer> removePage = new SimpleObjectProperty<>();

    private ObjectProperty<PageView> newPage = new SimpleObjectProperty<>();
    
    private ObjectProperty<TreeItem<String>> parentTree = new SimpleObjectProperty<>();

    private ObjectProperty<Pair<Integer, Pair<String, ObservableList<ConfigurationData>>>> pageConfigutaion = new SimpleObjectProperty<>();

    private ObjectProperty<Pair<Integer,Boolean>> enablePopUpMenuBtn = new SimpleObjectProperty<>();
    
    public PagesView() {
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

    public final ObjectProperty<Integer> subPageIndexProperty() {
        return this.subPageIndex;
    }

    public final Integer getSubPageIndex() {
        return this.subPageIndexProperty().get();
    }

    public final void addSubPageIndex(final Integer parentIndex) {
        this.subPageIndexProperty().set(parentIndex);
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

    public final ObjectProperty<TreeItem<String>> parentTreeProperty() {
        return this.parentTree;
    }

    public final TreeItem<String> getParentTree() {
        return this.parentTreeProperty().get();
    }

    public final void setParentTree(final TreeItem<String> rootNode) {
        this.parentTreeProperty().set(rootNode);
    }

    public final ObjectProperty<Pair<Integer, Pair<String, ObservableList<ConfigurationData>>>> pageConfigutaionProperty() {
        return this.pageConfigutaion;
    }

    public final Pair<Integer, Pair<String, ObservableList<ConfigurationData>>> getPageConfigutaion() {
        return this.pageConfigutaionProperty().get();
    }

    public final void setPageConfigutaion(final Pair<Integer, Pair<String, ObservableList<ConfigurationData>>> pageConfigutaion) {
        this.pageConfigutaionProperty().set(pageConfigutaion);
    }

    
    public final ObjectProperty<Pair<Integer,Boolean>> enablePopUpMenuBtnProperty() {
        return this.enablePopUpMenuBtn;
    }
    

    
    public final Pair<Integer,Boolean> getEnablePopUpMenuBtn() {
        return this.enablePopUpMenuBtnProperty().get();
    }
    

    
    public final void setEnablePopUpMenuBtn(final Pair<Integer,Boolean> enablePopUpMenuBtn) {
        this.enablePopUpMenuBtnProperty().set(enablePopUpMenuBtn);
    }

	public final ObjectProperty<PageView> newPageProperty() {
		return this.newPage;
	}
	

	public final PageView getNewPage() {
		return this.newPageProperty().get();
	}
	

	public final void addNewPage(final PageView newPage) {
		this.newPageProperty().set(newPage);
	}

	public final ObjectProperty<Boolean> clearParentTreeProperty() {
		return this.clearParentTree;
	}
	

	public final Boolean getClearParentTree() {
		return this.clearParentTreeProperty().get();
	}
	

	public final void clearParentTree(final Boolean clearParentTree) {
		this.clearParentTreeProperty().set(clearParentTree);
	}
	

}
