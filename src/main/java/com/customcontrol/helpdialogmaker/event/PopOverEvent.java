package com.customcontrol.helpdialogmaker.event;

import java.util.List;

import com.customcontrol.helpdialogmaker.model.OldConfigurationData;

import javafx.event.Event;
import javafx.event.EventType;

public class PopOverEvent extends Event {

    private static final long                   serialVersionUID      = 1L;

    public static final EventType<PopOverEvent> CLOSE                 = new EventType<>(Event.ANY, "CLOSE");

    public static final EventType<PopOverEvent> INIT_MUSTER_CONTAINER = new EventType<>(Event.ANY, "INIT_MUSTER_CONTAINER");

    private List<OldConfigurationData>          oldConfigurationDatas;

    private String                              name;

    public PopOverEvent(EventType<PopOverEvent> eventType) {
        super(eventType);
    }

    public PopOverEvent(EventType<PopOverEvent> eventType, String name,
                        List<OldConfigurationData> oldConfigurationDatas) {
        super(eventType);
        this.oldConfigurationDatas = oldConfigurationDatas;
        this.name = name;
    }

    public List<OldConfigurationData> getOldConfigurationDatas() {
        return oldConfigurationDatas;
    }

    public String getName() {
        return name;
    }

}
