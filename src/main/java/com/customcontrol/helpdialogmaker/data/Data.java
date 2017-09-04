package com.customcontrol.helpdialogmaker.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Data {
	private List<Page> pages = new ArrayList<>();

	public List<Page> getPages() {
		return pages;
	}

	@XmlElement(name="page", type=Page.class)
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

}
