package com.customcontrol.helpdialogmaker.viewmodel;

import com.customcontrol.helpdialogmaker.model.PageData;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class PageInfoViewModel {


	private PageData pageData;
	private boolean child;
	private int parentIndex;
	private BooleanProperty editable = new SimpleBooleanProperty(true);
	
	public PageData getPageData() {
		return pageData;
	}

	public void setPageData(PageData pageData) {
		this.pageData = pageData;
	}

	public boolean isChild() {
		return this.child;
	}

	public void setChild(boolean child) {
		this.child = child;
	}

	
	public void setParentIndex(int parentIndex) {
		this.parentIndex = parentIndex;
	}

	public int getParentIndex() {
		return this.parentIndex;
	}

	
	public final BooleanProperty editableProperty() {
		return this.editable;
	}
	

	
	public final boolean isEditable() {
		return this.editableProperty().get();
	}
	

	
	public final void setEditable(final boolean editable) {
		this.editableProperty().set(editable);
	}
	

	

}
