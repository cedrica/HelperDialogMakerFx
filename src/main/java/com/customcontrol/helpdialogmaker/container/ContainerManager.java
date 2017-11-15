package com.customcontrol.helpdialogmaker.container;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import com.customcontrol.helpdialogmaker.container.events.ContainerViewEvent;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page.PageEvent;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page.PageManager;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page.PageView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.previews.PreviewView;
import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.customcontrol.helpdialogmaker.helper.Helper;
import com.preag.core.ui.utils.dialog.Dialogs;

import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TreeItem;
import javafx.stage.FileChooser;
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
			ObservableList<TreeItem<String>> children = containerView.getPagesAndPreview().getPagesView()
					.getParentTree().getChildren();
			if (children.size() <= 0) {
				Dialog<ButtonType> info = Dialogs.info("Speicherung unmöglich. Keine Seite verfügbar", containerView.getScene().getWindow());
				info.showAndWait();
				return;
			}
			String helpXmlContent = "<data>\n";
			for (TreeItem<String> root : containerView.getPagesAndPreview().getPagesView().getParentTree()
					.getChildren()) {
				PageView pageView = (PageView) root.getGraphic();
				String xml = "";
				String xmlTemplate = Helper.createXmlTemplate(pageView, root, xml);
				helpXmlContent += xmlTemplate + "\n";
			}
			helpXmlContent += "</data>";
			Helper.saveFileInTempdirAndOpen(helpXmlContent, "HELP" + System.currentTimeMillis() + ".xml");
			Dialog<ButtonType> info = Dialogs.info(
					"Das generierte Template darf nicht geändert werden sonst kann die Datei später nicht mehr importiert werden",
					containerView.getScene().getWindow());
			info.showAndWait();
		});
		containerView.addEventHandler(ContainerViewEvent.IMPORT_XML_TEMPLATE, event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Template wählen");
			File template = fileChooser.showOpenDialog(containerView.getScene().getWindow());
			if (template != null) {
				List<PageView> pageViews = Helper.readHelperDialogTemplate(template);
				if (pageViews == null)
					return;
				containerView.getPagesAndPreview().getPagesView().clearParentTree(true);
				for (PageView pageView : pageViews) {
					Pair<Integer, Pair<String, ObservableList<ConfigurationData>>> pair = new Pair<>(
							pageView.getIndex(), new Pair<>(pageView.getHtml(), pageView.getConfiguration()));
					containerView.getPagesAndPreview().getPagesView().setPageConfigutaion(pair);
					containerView.getPagesAndPreview().getPagesView()
							.setEnablePopUpMenuBtn(new Pair<Integer, Boolean>(pageView.getIndex(), false));
					containerView.getPagesAndPreview().getPagesView().addNewPage(pageView);
				}
				previewView.setHtmlContent(ContainerService
						.builtHtmlPage(containerView.getPagesAndPreview().getPagesView().getParentTree()));
				containerView.getPagesAndPreview().setPlaceHolder(previewView);
			}
		});
		
		containerView.addEventHandler(ContainerViewEvent.FILE_SUCESSFULLY_EXPORTED, event -> {
			if (containerView.getPagesAndPreview() != null && containerView.getPagesAndPreview().getPagesView() != null
					&& containerView.getPagesAndPreview().getPagesView().getPageConfigutaion() != null
					&& containerView.getPagesAndPreview().getPagesView().getPageConfigutaion().getValue() != null
					&& containerView.getPagesAndPreview().getPagesView().getPageConfigutaion().getValue().getValue() != null
					&& containerView.getPagesAndPreview().getPagesView().getPageConfigutaion().getValue().getValue()
							.size() > 0) {
				Helper.saveHelperAndImagesInTempdir(
						ContainerService.builtHtmlPage(containerView.getPagesAndPreview().getPagesView().getParentTree()),"help.html");
				containerView.fireEvent(new ContainerViewEvent(ContainerViewEvent.FILE_SUCESSFULLY_EXPORTED));
			}else{
				Dialog<ButtonType> info = Dialogs.info("Keine Konfiguration registriert. Erzeugen Sie eine Seite um diesen Vorgang durchführen zu können.", containerView.getScene().getWindow());
				info.showAndWait();
			}
		});
		
		containerView.addEventHandler(PageEvent.PAGE_CONFIGURATION, evt -> {
			int pageIndex = evt.getPageIndex();
			String html = evt.getPageHTML();
			Pair<Integer, Pair<String, ObservableList<ConfigurationData>>> pair = new Pair<>(pageIndex,
					new Pair<>(html, evt.getConfigurationDatas()));
			containerView.getPagesAndPreview().getPagesView().setPageConfigutaion(pair);
			containerView.getPagesAndPreview().setPlaceHolder(previewView);
			previewView.setHtmlContent(
					ContainerService.builtHtmlPage(containerView.getPagesAndPreview().getPagesView().getParentTree()));
			containerView.getPagesAndPreview().getPagesView()
					.setEnablePopUpMenuBtn(new Pair<Integer, Boolean>(pageIndex, false));
		});

		containerView.getPagesAndPreview().setPlaceHolder(previewView);

		containerView.addEventHandler(PageEvent.UPDATE_PREVIEW, evt -> {
			evt.consume();
			containerView.getPagesAndPreview().setPlaceHolder(previewView);
			previewView.setHtmlContent(
					ContainerService.builtHtmlPage(containerView.getPagesAndPreview().getPagesView().getParentTree()));
			containerView.getPagesAndPreview().getPagesView()
					.setEnablePopUpMenuBtn(new Pair<Integer, Boolean>(evt.getPageIndex(), false));
		});

		containerView.addEventFilter(PageEvent.ADD_MENU_POINT, evt -> {
			evt.consume();
			previewView.setHtmlContent(
					ContainerService.builtHtmlPage(containerView.getPagesAndPreview().getPagesView().getParentTree()));
		});
	}

}
