package com.customcontrol.helpdialogmaker.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import com.customcontrol.helpdialogmaker.consts.HtmlPart;
import com.customcontrol.helpdialogmaker.consts.Screens;
import com.customcontrol.helpdialogmaker.event.PopOverEvent;
import com.customcontrol.helpdialogmaker.helper.Helper;
import com.customcontrol.helpdialogmaker.model.ConfigurationData;
import com.customcontrol.helpdialogmaker.model.PageData;
import com.customcontrol.helpdialogmaker.session.Session;
import com.google.common.io.Files;
import com.preag.core.ui.utils.FileUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ContainerView implements Initializable {

	@FXML
	Button				btnPages;
	@FXML
	Button				btnPreView;
	@FXML
	WebView				wvPreview;
	WebEngine			webEngine;
	private PopOver		popOver;
	private Stage		stage;
	private PagesView	pagesView;
	@FXML
	VBox				vbPlaceHolder;
	@FXML
	Button				btnSave;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnPages.setOnAction(this::onPagesClick);
		btnPreView.setOnAction(this::onPreview);
		btnSave.setOnAction(this::onSave);
	}

	public void onSave(ActionEvent evt) {
		String html = builtHtmlPage();
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File to = directoryChooser.showDialog(stage);
		if (to == null) {
			return;
		}
		FileUtil.writeStringToFile(html, to.getAbsolutePath() + "/helperDialog.html");
		File from = new File(System.getProperty("user.dir") + "/images");
		for (File file : from.listFiles()) {
			try {
				byte[] imageBytes = FileUtils.readFileToByteArray(file);
				FileUtil.writeBytesToFile(to, imageBytes);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void onPreview(ActionEvent evt) {
		webEngine = wvPreview.getEngine();
		webEngine.loadContent(builtHtmlPage());
	}

	private String builtHtmlPage() {
		String htmlPage = HtmlPart.HEAD;
		htmlPage = builtHtmlPageRec(Session.createInstance().pages, htmlPage);
		return htmlPage += "</body>" + "</html>";
	}

	public String builtHtmlPageRec(List<PageView> pages, String htmlPage) {
		for (PageView pageView : pages) {
			PageData pageData = pageView.getPageViewModel().getPageData();
			ConfigurationData configurationData = pageView.getPageInfoView().getPageInfoViewModel().getConfigurationData();
			String pageIndex = pageData.getIndex() + "";
			if (!pageView.isRootNode()) {
				pageIndex = makePageIndex(pageIndex);
			}
			String title = pageData.getName() + "";
			if (configurationData == null) {
				htmlPage += "<h2><b>" + pageIndex + "</b>	" + title + "</h2><br/>";
				continue;
			}

			String content = configurationData.getHtmlContent();
			htmlPage += "<h2><b>" + pageIndex + "</b>	" + title + "</h2><br/><p>" + content + "</p>";
			if (pageView.getSubPages().size() > 0) {
				htmlPage = builtHtmlPageRec(pageView.getSubPages(), htmlPage);
			}
		}
		return htmlPage;
	}

	private String makePageIndex(String pageIndex) {
		String res = "";
		for (int i = 0; i < pageIndex.length(); i++) {
			res += pageIndex.charAt(i) + ".";
		}
		return res;
	}

	public void onPagesClick(ActionEvent evt) {
		popOver = new PopOver();
		popOver.setArrowLocation(ArrowLocation.TOP_CENTER);
		FXMLLoader fxmlLoader = Helper.createView(Screens.PAGES);
		pagesView = fxmlLoader.getController();
		pagesView.setStage(stage);
		popOver.setContentNode(fxmlLoader.getRoot());
		popOver.setDetachable(true);
		popOver.show(btnPages);
		popOver.addEventFilter(PopOverEvent.CLOSE, e -> {
			popOver.hide();
			btnPages.setDisable(false);
		});
		btnPages.disableProperty().bind(pagesView.getPagesViewModel().btnPagesDisabledProperty());
	}


	public Stage getStage() {
		return stage;
	}


	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
