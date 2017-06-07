package com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator;

import com.customcontrol.helpdialogmaker.enums.Muster;
import com.preag.core.ui.event.ApplicationEvent;

import javafx.event.Event;
import javafx.event.EventType;

public class ConfiguratorEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    public static final EventType<ConfiguratorEvent> ADD_NEW_ROW = new EventType<ConfiguratorEvent>(ANY, "ADD_NEW_ROW");

    private Muster                muster;

    public ConfiguratorEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    public ConfiguratorEvent(EventType<ConfiguratorEvent> eventType, Muster muster) {
        super(eventType);
        this.muster = muster;
    }

    public Muster getMuster() {
        return muster;
    }

}
