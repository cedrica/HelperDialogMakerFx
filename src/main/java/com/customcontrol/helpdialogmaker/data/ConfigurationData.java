package com.customcontrol.helpdialogmaker.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.customcontrol.helpdialogmaker.enums.Muster;
@XmlRootElement(name="configurationData")
public class ConfigurationData {
	private byte[] image;
	private String htmlText;
	private Muster muster;
	
	public Muster getMuster() {
		return muster;
	}
	@XmlElement
	public void setMuster(Muster muster) {
		this.muster = muster;
	}

	public String getHtmlText() {
		return htmlText;
	}
	@XmlElement
	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}
	public byte[] getImage() {
		return image;
	}
	@XmlElement
	public void setImage(byte[] image) {
		this.image = image;
	}

}
