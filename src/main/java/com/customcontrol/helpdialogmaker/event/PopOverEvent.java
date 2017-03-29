package com.customcontrol.helpdialogmaker.event;

import javafx.event.Event;
import javafx.event.EventType;

public class PopOverEvent extends Event {

	private static final long	serialVersionUID	= 1L;
	public static final EventType<PopOverEvent>	CLOSE	= new EventType<>(Event.ANY, "CLOSE");
	public PopOverEvent(EventType<PopOverEvent> eventType) {
		super(eventType);
	}
	
}