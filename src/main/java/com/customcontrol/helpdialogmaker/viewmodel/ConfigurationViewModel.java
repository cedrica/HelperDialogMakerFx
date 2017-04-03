package com.customcontrol.helpdialogmaker.viewmodel;

import com.customcontrol.helpdialogmaker.enums.Muster;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ConfigurationViewModel {
	private ObjectProperty<Muster> selectedMuster = new SimpleObjectProperty<>();

	
	public final ObjectProperty<Muster> selectedMusterProperty() {
		return this.selectedMuster;
	}
	

	
	public final Muster getSelectedMuster() {
		return this.selectedMusterProperty().get();
	}
	

	
	public final void setSelectedMuster(final Muster selectedMuster) {
		this.selectedMusterProperty().set(selectedMuster);
	}
	
	

	
	
}
