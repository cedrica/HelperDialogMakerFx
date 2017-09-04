package com.customcontrol.helpdialogmaker.container;

import javax.inject.Inject;

import com.customcontrol.helpdialogmaker.container.events.ContainerViewEvent;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page.PageEvent;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page.PageManager;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.previews.PreviewView;
import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.preag.core.ui.utils.dialog.Dialogs;

import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.util.Pair;

public class ContainerManager {

    @Inject
    private PageManager pageManager;

    @Inject
    private PreviewView previewView;

    public void handleAddedEvents(ContainerView containerView) {

        pageManager.handleAddedEvents(containerView, previewView);
        containerView.addEventHandler(ContainerViewEvent.FILE_SUCESSFULLY_EXPORTED, event->{
        	Dialog<ButtonType> success = Dialogs.success("Vorlage wurde exportiert. Schieben sie die gewünschte Datei in der gewünschten Ordner.", containerView.getScene().getWindow());
        	success.showAndWait();
        });
        containerView.addEventHandler(PageEvent.PAGE_CONFIGURATION, evt ->{
            int pageIndex = evt.getPageIndex();
            String html = evt.getPageHTML();
            Pair<Integer, Pair<String,ObservableList<ConfigurationData>>> pair = new Pair<>(pageIndex, new Pair<>(html,evt.getConfigurationDatas()));
            containerView.getPagesAndPreview().getPagesView().setPageConfigutaion(pair);
            containerView.getPagesAndPreview().setPlaceHolder(previewView);
            previewView.setHtmlContent(ContainerService.builtHtmlPage(containerView.getPagesAndPreview().getPagesView().getRootNode()));
            containerView.getPagesAndPreview().getPagesView().setEnablePopUpMenuBtn(new Pair<Integer, Boolean>(pageIndex, false));
        });
        
        containerView.getPagesAndPreview().setPlaceHolder(previewView);

        containerView.addEventHandler(PageEvent.UPDATE_PREVIEW, evt -> {
            evt.consume();
            containerView.getPagesAndPreview().setPlaceHolder(previewView);
            previewView.setHtmlContent(ContainerService.builtHtmlPage(containerView.getPagesAndPreview().getPagesView().getRootNode()));
            containerView.getPagesAndPreview().getPagesView().setEnablePopUpMenuBtn(new Pair<Integer, Boolean>(evt.getPageIndex(), false));
        });

        containerView.addEventFilter(PageEvent.ADD_MENU_POINT, evt -> {
            evt.consume();
            previewView.setHtmlContent(ContainerService.builtHtmlPage(containerView.getPagesAndPreview().getPagesView().getRootNode()));
        });
    }
}
