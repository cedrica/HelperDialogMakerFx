package com.customcontrol.helpdialogmaker.model;


public class ConfigurationData {
	
	private int rowIndex;
	private StringBuilder htmlContent;
	
	public ConfigurationData(){
		htmlContent = new StringBuilder();
	}
	
	
	public String getHtmlContent() {
		return htmlContent.toString();
	}


	
	public void setHtmlContent(String htmlContent) {
		this.htmlContent.append(htmlContent);
	}


	public ConfigurationData(int pos) {
		this.rowIndex = pos;
	}

	
	public int getRowIndex() {
		return rowIndex;
	}

	
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	
}
