package com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.popovermenu;

import java.net.URL;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.event.PageEvent;
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
        popOverMenuView.fireEvent(new PageEvent(PageEvent.REMOVE_PAGE, popOverMenuView.getPageIndex(),true));
        popOverMenuView.fireEvent(new PopOverEvent(PopOverEvent.CLOSE));
    }

    public void onUnderPage() {
        popOverMenuView.fireEvent(new PageEvent(PageEvent.ADD_SUB_PAGE, popOverMenuView.getPageIndex(),true));
        popOverMenuView.fireEvent(new PopOverEvent(PopOverEvent.CLOSE));
    }

    public void onRename() {
        popOverMenuView.fireEvent(new PageEvent(PageEvent.EDIT_NAME, popOverMenuView.getPageIndex(),true));
        popOverMenuView.fireEvent(new PopOverEvent(PopOverEvent.CLOSE));
    }

    public void onConfig() {
//        ConfiguratorView configuratorView = new ConfiguratorView();
        // FXMLLoader parent = Helper.createView(Screens.CONFIGURATOR);
        popOverMenuView.fireEvent(new PageEvent(PageEvent.CONFIGURATION, popOverMenuView.getPageIndex(),true));
//        Stage stage = DialogHelper.dialogStage(configuratorView, Modality.WINDOW_MODAL, "Einstellung", null);
        // ConfiguratorController configuratorController = parent.getController();
        // configuratorController.getConfigurationViewModel().
//        btnConfig.fireEvent(new PopOverEvent(PopOverEvent.INIT_MUSTER_CONTAINER, popupMenuView.getPageData().getName(), popupMenuView.getOldConfigurationDatas()));

        // configuratorView.setTitle(popupMenuView.getPageData().getName());
        // configuratorView.initVbMusterContainer(popupMenuView.getOldConfigurationDatas());

        popOverMenuView.addEventFilter(PageEvent.TRANSFER_CONFIG_TO_PAGE, e -> {
        });

        popOverMenuView.addEventFilter(PageEvent.TRANSFER_OLD_CONFIG, e -> {
//            List<OldConfigurationData> oldConfigurationDatas = e.getOldConfigurationData();
//            popupMenuView.setOldConfigurationData(oldConfigurationDatas);
        });

        popOverMenuView.fireEvent(new PageEvent(PageEvent.HANDE_BUTTON_ENABLING, true));
//        popupMenuView.setOnCloseRequest(e -> {
//            popupMenuView.fireEvent(new PageEvent(PageEvent.HANDE_BUTTON_ENABLING, false));
//        });
        popOverMenuView.fireEvent(new PopOverEvent(PopOverEvent.CLOSE));
    }
}
