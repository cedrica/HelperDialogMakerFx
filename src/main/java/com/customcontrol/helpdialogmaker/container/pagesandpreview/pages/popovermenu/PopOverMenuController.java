package com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.popovermenu;

import java.net.URL;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.event.PopOverEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class PopOverMenuController implements Initializable {


    @FXML
    PopOverMenuView popOverMenuView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onRemove() {
        popOverMenuView.fireEvent(new PopOverEvent(PopOverEvent.REMOVE_PAGE, popOverMenuView.getPageIndex(),true));
        popOverMenuView.fireEvent(new PopOverEvent(PopOverEvent.CLOSE));
    }

    public void onUnderPage() {
        popOverMenuView.fireEvent(new PopOverEvent(PopOverEvent.ADD_SUB_PAGE, popOverMenuView.getPageIndex(),true));
        popOverMenuView.fireEvent(new PopOverEvent(PopOverEvent.CLOSE));
    }

    public void onRename() {
        popOverMenuView.fireEvent(new PopOverEvent(PopOverEvent.EDIT_NAME, popOverMenuView.getPageIndex(),true));
        popOverMenuView.fireEvent(new PopOverEvent(PopOverEvent.CLOSE));
    }

    public void onConfig() {
        popOverMenuView.fireEvent(new PopOverEvent(PopOverEvent.SHOW_CONFIGURATION, popOverMenuView.getPageIndex(),popOverMenuView.getPageName(),popOverMenuView.getConfiguration()));
        popOverMenuView.fireEvent(new PopOverEvent(PopOverEvent.CLOSE));
    }
}
