package com.customcontrol.helpdialogmaker.session;

import java.util.ArrayList;
import java.util.List;

import com.customcontrol.helpdialogmaker.view.PageView;

public class Session {
	private static Session				session	= null;
	public  List<PageView> pages = new ArrayList<>();
	
	private Session() {
	}


	public static Session createInstance() {
		if (session == null) {
			session = new Session();
		}
		return session;
	}

	public List<PageView> getPages() {
		return pages;
	}
	
	public void setPages(List<PageView> pages) {
		this.pages = pages;
	}

}
