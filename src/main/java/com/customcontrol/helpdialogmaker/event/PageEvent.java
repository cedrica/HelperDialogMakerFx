package com.customcontrol.helpdialogmaker.event;


import com.customcontrol.helpdialogmaker.model.ConfigurationData;
import com.customcontrol.helpdialogmaker.model.PageData;

import javafx.event.Event;
import javafx.event.EventType;

public class PageEvent extends Event {

	private static final long	serialVersionUID	= 1L;
	public static final EventType<PageEvent>	REMOVE	= new EventType<>(Event.ANY, "REMOVE");
	public static final EventType<PageEvent> ADD_SUB_PAGE = new EventType<>(Event.ANY, "ADD_SUB_PAGE");
	public static final EventType<PageEvent> CONFIGURE = new EventType<>(Event.ANY, "CONFIGURE");
	public static final EventType<PageEvent> REMOVE_ROW = new EventType<>(Event.ANY, "REMOVE_ROW");
	public static final EventType<PageEvent> TRANSFER_CONFIG_TO_PAGE = new EventType<>(Event.ANY, "TRANSFER_CONFIG_TO_PAGE");
	private PageData pageData;
	private ConfigurationData configurationData;
	
	public PageEvent(EventType<PageEvent> eventType, PageData pageData) {
		super(eventType);
		this.pageData = pageData;
	}
	
	public PageEvent(EventType<PageEvent> eventType, ConfigurationData configurationData) {
		super(eventType);
		this.configurationData = configurationData;
	}
	
	public PageData getPage() {
		return pageData;
	}

	
	public ConfigurationData getConfigurationData() {
		return configurationData;
	}

	
	
}