package com.customcontrol.helpdialogmaker.event;

import java.util.List;

import com.customcontrol.helpdialogmaker.model.OldConfigurationData;
import com.preag.core.ui.event.ApplicationEvent;

import javafx.event.Event;
import javafx.event.EventType;

public class PageEvent extends ApplicationEvent {

    private static final long                serialVersionUID        = 1L;

    public static final EventType<PageEvent> REMOVE_PAGE             = new EventType<>(Event.ANY, "REMOVE_PAGE");

    public static final EventType<PageEvent> ADD_SUB_PAGE            = new EventType<>(Event.ANY, "ADD_SUB_PAGE");

    public static final EventType<PageEvent> CONFIGURATION           = new EventType<>(Event.ANY, "CONFIGURATION");

    public static final EventType<PageEvent> REMOVE_CONFIGURATION    = new EventType<>(Event.ANY, "REMOVE_ROW");

    public static final EventType<PageEvent> TRANSFER_CONFIG_TO_PAGE = new EventType<>(Event.ANY, "TRANSFER_CONFIG_TO_PAGE");

    public static final EventType<PageEvent> TRANSFER_OLD_CONFIG     = new EventType<>(Event.ANY, "TRANSFER_OLD_CONFIG");

    public static final EventType<PageEvent> HANDE_BUTTON_ENABLING   = new EventType<>(Event.ANY, "HANDE_BUTTON_ENABLING");

    public static final EventType<PageEvent> ADD_MENU_POINT          = new EventType<>(Event.ANY, "ADD_MENU_POINT");

    public static final EventType<PageEvent> EDIT_NAME               = new EventType<>(Event.ANY, "EDIT_NAME");

    public static final EventType<PageEvent> SHOW_POPOVERMENU        = new EventType<>(Event.ANY, "CREATE_POPUPMENU");

    public static final EventType<PageEvent> UPDATE_PREVIEW          = new EventType<>(Event.ANY, "UPDATE_PREVIEW");

    private boolean                    disabled;

    private int                        musterIndex;

    private List<OldConfigurationData> oldConfigurationDatas;

    private int pageIndex;

    public PageEvent(EventType<PageEvent> eventType) {
        super(eventType);
    }

    public PageEvent(EventType<PageEvent> eventType, boolean disabled) {
        super(eventType);
        this.disabled = disabled;
    }

    public PageEvent(EventType<PageEvent> eventType, int musterIndex, boolean isPage) {
        super(eventType);
        if (!isPage)
            this.musterIndex = musterIndex;
        else{
            this.pageIndex = musterIndex;
        }
            
    }

    public PageEvent(EventType<PageEvent> eventType, List<OldConfigurationData> oldConfigurationDatas) {
        super(eventType);
        this.oldConfigurationDatas = oldConfigurationDatas;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public int getMusterIndex() {
        return musterIndex;
    }

    
    
    public int getPageIndex() {
        return pageIndex;
    }

    public List<OldConfigurationData> getOldConfigurationData() {
        return oldConfigurationDatas;
    }

}
