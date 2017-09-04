package com.customcontrol.helpdialogmaker.container.events;

import javafx.event.Event;
import javafx.event.EventType;

public class ContainerViewEvent extends Event{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static EventType<ContainerViewEvent> FILE_SUCESSFULLY_EXPORTED = new EventType<>(Event.ANY);
	
	public ContainerViewEvent(EventType<ContainerViewEvent> eventType) {
		super(eventType);
	}

}
