package com.customcontrol.helpdialogmaker.event;

import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.preag.core.ui.event.ApplicationEvent;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventType;

public class PageEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    public static final EventType<PageEvent> TRANSFER_CONFIG_TO_PAGE = new EventType<>(Event.ANY, "TRANSFER_CONFIG_TO_PAGE");

    public static final EventType<PageEvent> TRANSFER_OLD_CONFIG = new EventType<>(Event.ANY, "TRANSFER_OLD_CONFIG");

    public static final EventType<PageEvent> ADD_MENU_POINT = new EventType<>(Event.ANY, "ADD_MENU_POINT");

    public static final EventType<PageEvent> SHOW_POPOVERMENU = new EventType<>(Event.ANY, "CREATE_POPUPMENU");

    public static final EventType<PageEvent> UPDATE_PREVIEW = new EventType<>(Event.ANY, "UPDATE_PREVIEW");

    public static final EventType<PageEvent> PAGE_CONFIGURATION = new EventType<>(Event.ANY, "PAGE_CONFIGURATION");;

    private boolean disabled;


    private String pageHTML;

    private ObservableList<ConfigurationData> configurationDatas;

    private int pageIndex;

    public PageEvent(EventType<PageEvent> eventType) {
        super(eventType);
    }

    public PageEvent(EventType<PageEvent> eventType, boolean disabled) {
        super(eventType);
        this.disabled = disabled;
    }

    public PageEvent(EventType<PageEvent> eventType, int pageIndex, String html,ObservableList<ConfigurationData> configurationDatas) {
        super(eventType);
        this.pageHTML = html;
        this.pageIndex = pageIndex;
        this.configurationDatas = configurationDatas;
    }

    public PageEvent(EventType<PageEvent> eventType, ObservableList<ConfigurationData> configurationDatas) {
        super(eventType);
        this.configurationDatas = configurationDatas;
    }
    
    public PageEvent(EventType<PageEvent> eventType, int pageIndex, ObservableList<ConfigurationData> configurationDatas) {
        super(eventType);
        this.configurationDatas = configurationDatas;
        this.pageIndex = pageIndex;
    }

    public String getPageHTML() {
        return pageHTML;
    }


    public boolean isDisabled() {
        return disabled;
    }



    public int getPageIndex() {
        return pageIndex;
    }

    public ObservableList<ConfigurationData> getConfigurationDatas() {
        return configurationDatas;
    }

}
