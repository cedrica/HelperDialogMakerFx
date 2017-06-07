package com.customcontrol.helpdialogmaker.container;

import javax.inject.Inject;

import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page.PageManager;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.previews.PreviewView;
import com.customcontrol.helpdialogmaker.event.PageEvent;
import com.customcontrol.helpdialogmaker.event.PopOverEvent;

public class ContainerManager {

    @Inject
    private PageManager pageManager;

    @Inject
    private PreviewView previewView;

    public void handleAddedEvents(ContainerView containerView) {

        pageManager.handleAddedEvents(containerView, previewView);
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

        containerView.addEventHandler(PopOverEvent.INIT_MUSTER_CONTAINER, evt -> {
            evt.consume();
            containerView.addEventFilter(PopOverEvent.CLOSE, e -> {
                containerView.setClearPlaceHolder(true);
            });
        });

        containerView.addEventFilter(PageEvent.HANDE_BUTTON_ENABLING, e -> {
            e.consume();
        });

//        containerView.addEventFilter(PageEvent.ADD_MENU_POINT, evt -> {
//            evt.consume();
//            previewView.setHtmlContent(ContainerService.builtHtmlPage(containerView.getPagesAndPreview().getPagesView().getRootNode()));
//        });

    }
}
