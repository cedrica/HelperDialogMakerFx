package com.customcontrol.helpdialogmaker.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class PagesViewModel {
	private BooleanProperty btnPagesDisabled = new SimpleBooleanProperty(true);

	
	public final BooleanProperty btnPagesDisabledProperty() {
		return this.btnPagesDisabled;
	}
	

	
	public final boolean isBtnPagesDisabled() {
		return this.btnPagesDisabledProperty().get();
	}
	

	
	public final void setBtnPagesDisabled(final boolean btnPagesDisabled) {
		this.btnPagesDisabledProperty().set(btnPagesDisabled);
	}
	

	
	
}
