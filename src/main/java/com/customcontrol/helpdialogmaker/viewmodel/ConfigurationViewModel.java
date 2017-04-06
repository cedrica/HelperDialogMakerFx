package com.customcontrol.helpdialogmaker.viewmodel;

import java.util.ArrayList;
import java.util.List;

import com.customcontrol.helpdialogmaker.enums.Muster;
import com.customcontrol.helpdialogmaker.model.OldConfigurationData;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConfigurationViewModel {

	private ObjectProperty<Muster>		selectedMuster	= new SimpleObjectProperty<>();
	private StringProperty				title			= new SimpleStringProperty();
	private List<OldConfigurationData>	oldConfigurationDatas;


	public ConfigurationViewModel() {
		oldConfigurationDatas = new ArrayList<>();
	}

	public List<OldConfigurationData> getOldConfigurationDatas() {
		return oldConfigurationDatas;
	}


	public void setOldConfigurationDatas(List<OldConfigurationData> oldConfigurationDatas) {
		this.oldConfigurationDatas = oldConfigurationDatas;
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


}
