package com.customcontrol.helpdialogmaker.model;

import com.customcontrol.helpdialogmaker.enums.Muster;

public class OldConfigurationData {
	private byte[] image;
	private String htmlText;
	private Muster muster;
	
	public Muster getMuster() {
		return muster;
	}

	public void setMuster(Muster muster) {
		this.muster = muster;
	}

	public String getHtmlText() {
		return htmlText;
	}
	
	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}

}
