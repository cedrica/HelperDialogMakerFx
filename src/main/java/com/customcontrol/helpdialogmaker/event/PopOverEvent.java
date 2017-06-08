package com.customcontrol.helpdialogmaker.event;

import com.customcontrol.helpdialogmaker.data.ConfigurationData;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventType;

public class PopOverEvent extends Event {

    private static final long serialVersionUID = 1L;

    public static final EventType<PopOverEvent> REMOVE_CONFIGURATION = new EventType<>(Event.ANY, "REMOVE_ROW");

    public static final EventType<PopOverEvent> REMOVE_PAGE = new EventType<>(Event.ANY, "REMOVE_PAGE");

    public static final EventType<PopOverEvent> ADD_SUB_PAGE = new EventType<>(Event.ANY, "ADD_SUB_PAGE");

    public static final EventType<PopOverEvent> EDIT_NAME = new EventType<>(Event.ANY, "EDIT_NAME");

    public static final EventType<PopOverEvent> CLOSE = new EventType<>(Event.ANY, "CLOSE");

    public static final EventType<PopOverEvent> SHOW_CONFIGURATION = new EventType<>(Event.ANY, "SHOW_CONFIGURATION");

    private ObservableList<ConfigurationData> configuration;

    private int musterIndex;

    private int pageIndex;

    public PopOverEvent(EventType<PopOverEvent> eventType) {
        super(eventType);
    }

    public PopOverEvent(EventType<PopOverEvent> eventType, int musterIndex, boolean isPage) {
        super(eventType);
        if (!isPage)
            this.musterIndex = musterIndex;
        else {
            this.pageIndex = musterIndex;
        }

    }

    public PopOverEvent(EventType<PopOverEvent> eventType, int pageIndex,
                        ObservableList<ConfigurationData> configuration) {
        super(eventType);
        this.pageIndex = pageIndex;
        this.configuration = configuration;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getMusterIndex() {
        return musterIndex;
    }

    public ObservableList<ConfigurationData> getConfiguration() {
        return configuration;
    }

}
