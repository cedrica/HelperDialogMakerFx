package com.customcontrol.helpdialogmaker.event;


import com.customcontrol.helpdialogmaker.model.Page;

import javafx.event.Event;
import javafx.event.EventType;

public class PageEvent extends Event {

	private static final long	serialVersionUID	= 1L;
	public static final EventType<PageEvent>	REMOVE	= new EventType<>(Event.ANY, "REMOVE");
	private Page page;
	public PageEvent(EventType<PageEvent> eventType, Page page) {
		super(eventType);
		this.page = page;
	}
	
	public Page getPage() {
		return page;
	}

	
}