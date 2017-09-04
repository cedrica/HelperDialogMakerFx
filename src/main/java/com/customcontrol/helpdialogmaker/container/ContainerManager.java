package com.customcontrol.helpdialogmaker.container;

import java.io.File;

import javax.inject.Inject;

import com.customcontrol.helpdialogmaker.container.events.ContainerViewEvent;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page.PageEvent;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page.PageManager;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page.PageView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.previews.PreviewView;
import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.customcontrol.helpdialogmaker.enums.Muster;
import com.customcontrol.helpdialogmaker.helper.Helper;
import com.google.common.io.Files;
import com.preag.core.ui.utils.FileUtil;
import com.preag.core.ui.utils.dialog.Dialogs;

import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TreeItem;
import javafx.util.Pair;

public class ContainerManager {

	@Inject
	private PageManager pageManager;

	@Inject
	private PreviewView previewView;

	public void handleAddedEvents(ContainerView containerView) {

		pageManager.handleAddedEvents(containerView, previewView);
		containerView.addEventHandler(ContainerViewEvent.FILE_SUCESSFULLY_EXPORTED, event -> {
			Dialog<ButtonType> success = Dialogs.success(
					"Vorlage wurde exportiert. Schieben sie die gewünschte Datei in der gewünschten Ordner.",
					containerView.getScene().getWindow());
			success.showAndWait();
		});
		containerView.addEventHandler(ContainerViewEvent.SAVE_AS_TEMPLATE, event -> {
			String help = "<help>\n";
			for (TreeItem<String> root : containerView.getPagesAndPreview().getPagesView().getRootNode()
					.getChildren()) {
				PageView pageView = (PageView) root.getGraphic();
				String saveConfigurationOfPage = saveConfigurationOfPage(pageView);
				help += saveConfigurationOfPage+"\n";
			}
			help += "</help>";
			Helper.saveFileInTempdirAndOpen(help, "HELP"+System.currentTimeMillis()+".xml");
			
		});

		containerView.addEventHandler(PageEvent.PAGE_CONFIGURATION, evt -> {
			int pageIndex = evt.getPageIndex();
			String html = evt.getPageHTML();
			Pair<Integer, Pair<String, ObservableList<ConfigurationData>>> pair = new Pair<>(pageIndex,
					new Pair<>(html, evt.getConfigurationDatas()));
			containerView.getPagesAndPreview().getPagesView().setPageConfigutaion(pair);
			containerView.getPagesAndPreview().setPlaceHolder(previewView);
			previewView.setHtmlContent(
					ContainerService.builtHtmlPage(containerView.getPagesAndPreview().getPagesView().getRootNode()));
			containerView.getPagesAndPreview().getPagesView()
					.setEnablePopUpMenuBtn(new Pair<Integer, Boolean>(pageIndex, false));
		});

		containerView.getPagesAndPreview().setPlaceHolder(previewView);

		containerView.addEventHandler(PageEvent.UPDATE_PREVIEW, evt -> {
			evt.consume();
			containerView.getPagesAndPreview().setPlaceHolder(previewView);
			previewView.setHtmlContent(
					ContainerService.builtHtmlPage(containerView.getPagesAndPreview().getPagesView().getRootNode()));
			containerView.getPagesAndPreview().getPagesView()
					.setEnablePopUpMenuBtn(new Pair<Integer, Boolean>(evt.getPageIndex(), false));
		});

		containerView.addEventFilter(PageEvent.ADD_MENU_POINT, evt -> {
			evt.consume();
			previewView.setHtmlContent(
					ContainerService.builtHtmlPage(containerView.getPagesAndPreview().getPagesView().getRootNode()));
		});
	}

	public String saveConfigurationOfPage(PageView pageView) {
		String xml = "<page>"
				+ "<name>" + pageView.getName() + "<name>"
				+ "<index>" + pageView.getIndex() + "</index>"
				+ "<html>"+ pageView.getHtml() + "</html>"
				+ "<rootNode>" + pageView.isRootNode() + "</rootNode>"
				+ "<nameVisible>"+ pageView.isNameVisible() + "</nameVisible>"
				+ "<home>" + pageView.isHome() + "</home>"
				+ "<disablePopUpMenuBtn>"+ pageView.isDisablePopUpMenuBtn() + "</disablePopUpMenuBtn>";
		if (pageView.getConfiguration() == null) {
			return xml+"</page>";
		}
		
		xml+= "<configurationData>";
		for (ConfigurationData configurationData : pageView.getConfiguration()) {
			Muster muster = configurationData.getMuster();
			if (muster == Muster.IMAGE) {
				xml += "<image>" + configurationData.getImage() + "</image>" 
						+ "<htmlText>"+ configurationData.getHtmlText() + "</htmlText>" 
						+ "<muster>" + configurationData.getMuster()+ "</muster>";
			} else if (muster == Muster.TEXT) {
				xml += "<htmlText>" + configurationData.getHtmlText() + "</htmlText>"
						+ "<muster>"+ configurationData.getMuster() + "</muster>";
			} else if (muster == Muster.TEXT_IMAGE || muster == Muster.IMAGE_TEXT) {
				xml += "<image>" + configurationData.getImage() + "</image>" 
						+ "<htmlText>"+ configurationData.getHtmlText() + "</htmlText>" 
						+ "<muster>" + configurationData.getMuster()+ "</muster>";
			}
		}
		xml += "</configurationData></page>";
		return xml;
	}
}
