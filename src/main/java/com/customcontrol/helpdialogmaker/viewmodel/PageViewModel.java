package com.customcontrol.helpdialogmaker.viewmodel;

import com.customcontrol.helpdialogmaker.model.PageData;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PageViewModel {

	private StringProperty	name			= new SimpleStringProperty();
	private BooleanProperty	tfNameVisible	= new SimpleBooleanProperty(true);
	private PageData		pageData;

	public PageData getPageData() {
		return pageData;
	}


	public void setPageData(PageData pageData) {
		this.pageData = pageData;
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

	public final BooleanProperty tfNameVisibleProperty() {
		return this.tfNameVisible;
	}


	public final boolean isTfNameVisible() {
		return this.tfNameVisibleProperty().get();
	}


	public final void setTfNameVisible(final boolean nameVisible) {
		this.tfNameVisibleProperty().set(nameVisible);
	}


}
