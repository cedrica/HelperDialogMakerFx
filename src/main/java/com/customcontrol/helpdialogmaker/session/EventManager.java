package com.customcontrol.helpdialogmaker.session;

import com.customcontrol.helpdialogmaker.event.PageEvent;

import javafx.stage.Stage;

public class EventManager {
	public static Stage stage;
	
	public static void handleEvent(){
		stage.addEventFilter(PageEvent.REMOVE_ROW, evt ->{
			
		});
	}

}
