package com.customcontrol.helpdialogmaker.model;

import java.util.ArrayList;
import java.util.List;

public class PageData {

	private int				index;
	private String			name;
	private String			text;
	private boolean			child;
	private List<byte[]>	images;
	private List<PageData>	subPages;

	public PageData(int index, String name) {
		this.index = index;
		this.name = name;
		subPages = new ArrayList<>();
	}


	public boolean isChild() {
		return this.child;
	}

	public void setChild(boolean child) {
		this.child = child;
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


	public List<PageData> getSubPages() {
		return subPages;
	}


	public void setSubPages(List<PageData> subPages) {
		this.subPages = subPages;
	}


	public List<byte[]> getImages() {
		return images;
	}


	public void setImages(List<byte[]> images) {
		this.images = images;
	}


}
