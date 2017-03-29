package com.customcontrol.helpdialogmaker.session;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;

public class Session {
	private static Session				session	= null;
	public  List<FXMLLoader> pages = new ArrayList<>();
	
	private Session() {
	}


	public static Session createInstance() {
		if (session == null) {
			session = new Session();
		}
		return session;
	}

	public List<FXMLLoader> getPages() {
		return pages;
	}
	
	public void setPages(List<FXMLLoader> pages) {
		this.pages = pages;
	}

}
