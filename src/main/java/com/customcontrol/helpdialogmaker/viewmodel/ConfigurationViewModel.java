package com.customcontrol.helpdialogmaker.viewmodel;

import com.customcontrol.helpdialogmaker.enums.Muster;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConfigurationViewModel {

	private ObjectProperty<Muster>	selectedMuster	= new SimpleObjectProperty<>();
	private StringProperty			title			= new SimpleStringProperty();


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
