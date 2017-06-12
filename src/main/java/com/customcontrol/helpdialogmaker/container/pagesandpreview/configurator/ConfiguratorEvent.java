package com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator;

import com.customcontrol.helpdialogmaker.enums.Muster;
import com.preag.core.ui.event.ApplicationEvent;

import javafx.event.Event;
import javafx.event.EventType;

public class ConfiguratorEvent extends ApplicationEvent {

    private static final long                        serialVersionUID = 1L;

    public static final EventType<ConfiguratorEvent> REMOVE_MUSTER    = new EventType<>(Event.ANY, "REMOVE_ROW");

    public static final EventType<ConfiguratorEvent> ADD_NEW_ROW      = new EventType<ConfiguratorEvent>(ANY, "ADD_NEW_ROW");

    public static final EventType<ConfiguratorEvent> DIS_OR_ENABLE_SAVE = new EventType<ConfiguratorEvent>(ANY, "DIS_OR_ENABLE_SAVE");

    public static final EventType<ConfiguratorEvent> MOVE_UP            = new EventType<ConfiguratorEvent>(ANY, "MOVE_UP");

    public static final EventType<ConfiguratorEvent> MOVE_DOWN          = new EventType<ConfiguratorEvent>(ANY, "MOVE_DOWN");

    private Muster muster;

    private int incOrDec;

    private int musterIndex;

    private int moveUpDown;

    public ConfiguratorEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    public ConfiguratorEvent(EventType<? extends Event> eventType, int moveUpDown) {
        super(eventType);
        this.moveUpDown = moveUpDown;
    }

    public ConfiguratorEvent(EventType<ConfiguratorEvent> eventType, Muster muster) {
        super(eventType);
        this.muster = muster;
    }

    public ConfiguratorEvent(EventType<ConfiguratorEvent> eventType, int posInVbMusterContainer, int incOrDec) {
        super(eventType);
        musterIndex = posInVbMusterContainer;
        this.incOrDec = incOrDec;
    }

    public Muster getMuster() {
        return muster;
    }

    public int getMoveUpDown() {
        return moveUpDown;
    }

    public int getIncOrDec() {
        return incOrDec;
    }

    public int getMusterIndex() {
        return musterIndex;
    }

}
