package com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.popovermenu;

import java.net.URL;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.container.ContainerService;
import com.preag.core.ui.utils.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class PopOverMenuController implements Initializable {

    

    @FXML
    PopOverMenuView popOverMenuView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onRemove() {
        popOverMenuView.fireEvent(new PopOverMenuEvent(PopOverMenuEvent.REMOVE_PAGE, popOverMenuView.getPageIndex(), true));
        popOverMenuView.fireEvent(new PopOverMenuEvent(PopOverMenuEvent.CLOSE));
    }

    public void onUnderPage() {
        popOverMenuView.fireEvent(new PopOverMenuEvent(PopOverMenuEvent.ADD_SUB_PAGE, popOverMenuView.getPageIndex(), true));
        popOverMenuView.fireEvent(new PopOverMenuEvent(PopOverMenuEvent.CLOSE));
    }

    public void onRename() {
        popOverMenuView.fireEvent(new PopOverMenuEvent(PopOverMenuEvent.EDIT_NAME, popOverMenuView.getPageIndex(), true));
        popOverMenuView.fireEvent(new PopOverMenuEvent(PopOverMenuEvent.CLOSE));
    }

    public void onConfig() {
        if (ContainerService.CONFIGURATOR_SHOWABLE) {
            popOverMenuView.fireEvent(new PopOverMenuEvent(PopOverMenuEvent.SHOW_CONFIGURATION, popOverMenuView.getPageIndex(), popOverMenuView.getPageName(), popOverMenuView.getConfiguration()));
            popOverMenuView.fireEvent(new PopOverMenuEvent(PopOverMenuEvent.CLOSE));
            ContainerService.CONFIGURATOR_SHOWABLE = false;
        } else {
            Dialog<ButtonType> info = Dialogs.info("Eine andere Seite wird gerade configuriert. Diese erst abschließen und speichern oder abbrechen.", null);
            info.showAndWait();
        }
    }
}
