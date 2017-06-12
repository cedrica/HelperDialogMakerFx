package com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.popovermenu;

import com.customcontrol.helpdialogmaker.data.ConfigurationData;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventType;

public class PopOverMenuEvent extends Event {

    private static final long serialVersionUID = 1L;

   

    public static final EventType<PopOverMenuEvent> REMOVE_PAGE = new EventType<>(Event.ANY, "REMOVE_PAGE");

    public static final EventType<PopOverMenuEvent> ADD_SUB_PAGE = new EventType<>(Event.ANY, "ADD_SUB_PAGE");

    public static final EventType<PopOverMenuEvent> EDIT_NAME = new EventType<>(Event.ANY, "EDIT_NAME");

    public static final EventType<PopOverMenuEvent> CLOSE = new EventType<>(Event.ANY, "CLOSE");

    public static final EventType<PopOverMenuEvent> SHOW_CONFIGURATION = new EventType<>(Event.ANY, "SHOW_CONFIGURATION");

    private ObservableList<ConfigurationData> configuration;

    private int    musterIndex;

    private String pageName;

    private int    pageIndex;

    public PopOverMenuEvent(EventType<PopOverMenuEvent> eventType) {
        super(eventType);
    }

    public PopOverMenuEvent(EventType<PopOverMenuEvent> eventType, int musterIndex, boolean isPage) {
        super(eventType);
        if (!isPage)
            this.musterIndex = musterIndex;
        else {
            this.pageIndex = musterIndex;
        }

    }

    public PopOverMenuEvent(EventType<PopOverMenuEvent> eventType, int pageIndex, String pageName,
                        ObservableList<ConfigurationData> configuration) {
        super(eventType);
        this.pageIndex = pageIndex;
        this.pageName = pageName;
        this.configuration = configuration;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getMusterIndex() {
        return musterIndex;
    }

    public String getPageName() {
        return pageName;
    }

    public ObservableList<ConfigurationData> getConfiguration() {
        return configuration;
    }

}
