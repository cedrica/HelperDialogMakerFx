package com.customcontrol.helpdialogmaker.container.events;

import javafx.event.Event;
import javafx.event.EventType;

public class ContainerViewEvent extends Event{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static EventType<ContainerViewEvent> FILE_SUCESSFULLY_EXPORTED = new EventType<>("FILE_SUCESSFULLY_EXPORTED");
	public static EventType<ContainerViewEvent> SAVE_AS_TEMPLATE = new EventType<>("SAVE_AS_TEMPLATE");
	
	public ContainerViewEvent(EventType<ContainerViewEvent> eventType) {
		super(eventType);
	}

}
