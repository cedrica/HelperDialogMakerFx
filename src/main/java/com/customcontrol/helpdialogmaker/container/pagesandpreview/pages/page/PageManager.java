package com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import com.customcontrol.helpdialogmaker.container.ContainerService;
import com.customcontrol.helpdialogmaker.container.ContainerView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.ConfiguratorManager;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.popovermenu.PopOverMenuView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.previews.PreviewView;
import com.customcontrol.helpdialogmaker.event.PageEvent;
import com.customcontrol.helpdialogmaker.event.PopOverEvent;

import javafx.scene.Node;

@Singleton
public class PageManager {

    @Inject
    private PopOverMenuView popOverMenuView;
    @Inject
    private ConfiguratorManager configuratorManager;
    public void handleAddedEvents(ContainerView containerView,PreviewView previewView) {
        configuratorManager.handleAddedEvents();
        popOverMenuView.addEventFilter(PageEvent.ADD_SUB_PAGE, evt -> {
            evt.consume();
            containerView.getPagesAndPreview().getPagesView().addSubPage(evt.getPageIndex());
        });
        popOverMenuView.addEventFilter(PageEvent.CONFIGURATION, evt -> {
            evt.consume();
            configuratorManager.showConfigurator(containerView.getPagesAndPreview());
        });
        popOverMenuView.addEventHandler(PageEvent.REMOVE_PAGE, evt -> {
            evt.consume();
            int pageIndex = evt.getPageIndex();
            containerView.getPagesAndPreview().getPagesView().setRemovePage(pageIndex);
        });
        popOverMenuView.addEventFilter(PageEvent.EDIT_NAME, e -> {
            e.consume();
            int pageIndex = e.getPageIndex();
            containerView.getPagesAndPreview().getPagesView().setRenamePage(pageIndex);
        });

        popOverMenuView.addEventFilter(PageEvent.CONFIGURATION, evt -> {
            evt.consume();
            containerView.fireEvent(new PageEvent(PageEvent.CONFIGURATION));
        });
        containerView.addEventFilter(PageEvent.SHOW_POPOVERMENU, evt -> {
            evt.consume();
            PopOver popOver = new PopOver();
            popOverMenuView.setPageIndex(evt.getPageIndex());
            popOver.setArrowLocation(ArrowLocation.LEFT_TOP);
            popOver.setContentNode(popOverMenuView);
            popOver.setDetachable(false);
            popOver.show((Node) evt.getTarget());
            popOverMenuView.addEventFilter(PopOverEvent.CLOSE, e -> {
                e.consume();
                previewView.setHtmlContent(ContainerService.builtHtmlPage(containerView.getPagesAndPreview().getPagesView().getRootNode()));
                popOver.hide();
            });
        });

    }
}
