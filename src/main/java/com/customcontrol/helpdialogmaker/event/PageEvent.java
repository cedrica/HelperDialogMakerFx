package com.customcontrol.helpdialogmaker.event;


import java.util.List;

import com.customcontrol.helpdialogmaker.model.ConfigurationData;
import com.customcontrol.helpdialogmaker.model.OldConfigurationData;
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
	public static final EventType<PageEvent> TRANSFER_OLD_CONFIG = new EventType<>(Event.ANY, "TRANSFER_OLD_CONFIG");
	private PageData pageData;
	private ConfigurationData configurationData;
	private List<OldConfigurationData> oldConfigurationDatas;
	
	public PageEvent(EventType<PageEvent> eventType, PageData pageData) {
		super(eventType);
		this.pageData = pageData;
	}
	
	public PageEvent(EventType<PageEvent> eventType, ConfigurationData configurationData) {
		super(eventType);
		this.configurationData = configurationData;
	}
	
	public PageEvent(EventType<PageEvent> eventType, List<OldConfigurationData> oldConfigurationDatas) {
		super(eventType);
		this.oldConfigurationDatas = oldConfigurationDatas;
	}
	
	public PageData getPage() {
		return pageData;
	}

	
	public ConfigurationData getConfigurationData() {
		return configurationData;
	}

	
	public List<OldConfigurationData> getOldConfigurationData() {
		return oldConfigurationDatas;
	}

	
	
}