package com.customcontrol.helpdialogmaker;

import org.apache.deltaspike.core.api.provider.BeanProvider;

import com.customcontrol.helpdialogmaker.container.ContainerManager;
import com.customcontrol.helpdialogmaker.container.ContainerView;
import com.preag.core.exception.sevice.DefaultUncaughtExcpetionHandler;
import com.preag.core.ui.app.DesktopApp;
import com.preag.core.ui.utils.dialog.Dialogs;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;

public class Main extends DesktopApp {


    public static void main(String[] args) {
        enablePreloader();
        launch(args);
    }

    @Override
    public void initialize() {
        Thread.setDefaultUncaughtExceptionHandler(new DefaultUncaughtExcpetionHandler());
        ContainerView containerView = BeanProvider.getContextualReference(ContainerView.class, false);
        ContainerManager containerManager = BeanProvider.getContextualReference(ContainerManager.class, false);
        containerManager.handleAddedEvents(containerView);
        getPrimaryStage().getIcons().add(new Image(getClass().getResource("/images/stage_icon.png").toString()));
        setStageIcon("/images/stage_icon.png");
        getPrimaryStage().setMaximized(true);
        closePreloader();
        showPrimaryStage(containerView);
       Dialog<ButtonType> info = Dialogs.info("Von diesem Program generierte Dateie werden in Temporäre Ordner abgelegt. Deswegen diese\n Ordner nach Benutzung aufräumen oder automatisches Aufräumen von Temp Ordner auf dem PC einstellen.", containerView.getScene().getWindow());
       info.showAndWait();
    }

}
