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
import javafx.util.Pair;

@Singleton
public class PageManager {

    @Inject
    private PopOverMenuView popOverMenuView;

    @Inject
    private ConfiguratorManager configuratorManager;

    public void handleAddedEvents(ContainerView containerView, PreviewView previewView) {
        configuratorManager.handleAddedEvents();

        popOverMenuView.addEventFilter(PopOverEvent.ADD_SUB_PAGE, evt -> {
            evt.consume();
            containerView.getPagesAndPreview().getPagesView().addSubPage(evt.getPageIndex());
        });
        popOverMenuView.addEventFilter(PopOverEvent.SHOW_CONFIGURATION, evt -> {
            evt.consume();
            int pageIndex = evt.getPageIndex();
            containerView.getPagesAndPreview().getPagesView().setEnablePopUpMenuBtn(new Pair<Integer, Boolean>(pageIndex,true));
            configuratorManager.showConfigurator(containerView.getPagesAndPreview(), pageIndex,evt.getPageName(), evt.getConfiguration());
        });
        
        popOverMenuView.addEventHandler(PopOverEvent.REMOVE_PAGE, evt -> {
            evt.consume();
            int pageIndex = evt.getPageIndex();
            containerView.getPagesAndPreview().getPagesView().setRemovePage(pageIndex);
        });
        popOverMenuView.addEventFilter(PopOverEvent.EDIT_NAME, e -> {
            e.consume();
            int pageIndex = e.getPageIndex();
            containerView.getPagesAndPreview().getPagesView().setRenamePage(pageIndex);
        });

        containerView.addEventFilter(PageEvent.SHOW_POPOVERMENU, evt -> {
            evt.consume();
            PopOver popOver = new PopOver();
            popOverMenuView.setPageIndex(evt.getPageIndex());
            popOverMenuView.setPageName(evt.getPageName());
            popOverMenuView.setConfiguration(evt.getConfigurationDatas());
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
