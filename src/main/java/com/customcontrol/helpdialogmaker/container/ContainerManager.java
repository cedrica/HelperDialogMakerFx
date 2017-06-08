package com.customcontrol.helpdialogmaker.container;

import javax.inject.Inject;

import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page.PageManager;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.previews.PreviewView;
import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.customcontrol.helpdialogmaker.event.PageEvent;

import javafx.collections.ObservableList;
import javafx.util.Pair;

public class ContainerManager {

    @Inject
    private PageManager pageManager;

    @Inject
    private PreviewView previewView;

    public void handleAddedEvents(ContainerView containerView) {

        pageManager.handleAddedEvents(containerView, previewView);
        
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
        });

        containerView.addEventFilter(PageEvent.ADD_MENU_POINT, evt -> {
            evt.consume();
            previewView.setHtmlContent(ContainerService.builtHtmlPage(containerView.getPagesAndPreview().getPagesView().getRootNode()));
        });
    }
}
