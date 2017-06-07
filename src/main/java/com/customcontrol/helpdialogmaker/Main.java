package com.customcontrol.helpdialogmaker;

import org.apache.deltaspike.core.api.provider.BeanProvider;

import com.customcontrol.helpdialogmaker.container.ContainerManager;
import com.customcontrol.helpdialogmaker.container.ContainerView;
import com.preag.core.exception.sevice.DefaultUncaughtExcpetionHandler;
import com.preag.core.ui.app.DesktopApp;

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
        getPrimaryStage().setMaximized(true);
        closePreloader();
        showPrimaryStage(containerView);
       
    }

}
