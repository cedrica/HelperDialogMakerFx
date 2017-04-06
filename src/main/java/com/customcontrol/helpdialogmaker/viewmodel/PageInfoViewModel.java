package com.customcontrol.helpdialogmaker.viewmodel;

import java.util.ArrayList;
import java.util.List;

import com.customcontrol.helpdialogmaker.model.ConfigurationData;
import com.customcontrol.helpdialogmaker.model.OldConfigurationData;
import com.customcontrol.helpdialogmaker.model.PageData;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class PageInfoViewModel {


	private PageData		pageData;
	private boolean			child;
	private int				parentIndex;
	private BooleanProperty	editable	= new SimpleBooleanProperty(true);
	private ConfigurationData configurationData;
	private List<OldConfigurationData> oldConfigurationDatas;

	public PageInfoViewModel() {
		oldConfigurationDatas = new ArrayList<>();
	}

	
	public List<OldConfigurationData> getOldConfigurationDatas() {
		return oldConfigurationDatas;
	}

	
	public void setOldConfigurationData(List<OldConfigurationData> oldConfigurationDatas) {
		this.oldConfigurationDatas = oldConfigurationDatas;
	}

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

	
	
	public ConfigurationData getConfigurationData() {
		return configurationData;
	}

	public void setConfigurationData(ConfigurationData configurationData) {
		this.configurationData = configurationData;
	}


}
