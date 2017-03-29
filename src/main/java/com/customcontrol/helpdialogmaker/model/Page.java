package com.customcontrol.helpdialogmaker.model;

import java.util.List;

public class Page {
	private int index;
	private String name;
	private String text;
	private List<Page> subPages;
	private List<byte[]> images;
	
	public Page(int index, String name){
		this.index = index;
		this.name = name;
	}

	
	public int getIndex() {
		return index;
	}

	
	public void setIndex(int index) {
		this.index = index;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getText() {
		return text;
	}

	
	public void setText(String text) {
		this.text = text;
	}

	
	public List<Page> getSubPages() {
		return subPages;
	}

	
	public void setSubPages(List<Page> subPages) {
		this.subPages = subPages;
	}


	
	public List<byte[]> getImages() {
		return images;
	}


	
	public void setImages(List<byte[]> images) {
		this.images = images;
	}
	
	

}
