package com.customcontrol.helpdialogmaker.model;


public class ConfigurationData {
	public ConfigurationData(int pos) {
		this.rowIndex = pos;
	}
	private int rowIndex;

	
	public int getRowIndex() {
		return rowIndex;
	}

	
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	
}
