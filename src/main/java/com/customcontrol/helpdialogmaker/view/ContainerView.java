package com.customcontrol.helpdialogmaker.view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.customcontrol.helpdialogmaker.consts.HtmlPart;
import com.customcontrol.helpdialogmaker.consts.Screens;
import com.customcontrol.helpdialogmaker.event.PageEvent;
import com.customcontrol.helpdialogmaker.event.PopOverEvent;
import com.customcontrol.helpdialogmaker.helper.Helper;
import com.customcontrol.helpdialogmaker.model.ConfigurationData;
import com.customcontrol.helpdialogmaker.model.PageData;
import com.customcontrol.helpdialogmaker.session.Session;
import com.preag.core.ui.utils.FileUtil;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ContainerView implements Initializable {

    @FXML
    Button            btnPages;

    @FXML
    Button            btnPreView;

    @FXML
    WebView           wvPreview;

    WebEngine         webEngine;

    private Stage     stage;

    private PagesView pagesView;

    @FXML
    VBox              vbPlaceHolder;

    @FXML
    Button            btnSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnPages.setOnAction(this::onPagesClick);
        btnPreView.setOnAction(this::onPreview);
        btnSave.setOnAction(this::onSave);
  
    }

    public void onSave(ActionEvent evt) {
        String html = pagesView.getHtmlContent();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File location = directoryChooser.showDialog(stage);
        if (location == null) {
            return;
        }
        // FileUtil.writeStringToFile(html, to.getAbsolutePath() + "/helperDialog.html");
        File from = new File(System.getProperty("user.dir") + "/additionalResources/images");
        // File from = new File(ContainerView.class.getResourceAsStream("/images").toString());
        File to = FileUtil.getFileOrCreate(location.getAbsolutePath(), "image.png");

        for (File file : from.listFiles()) {
            FileUtil.saveFileAsTemp(file);
        }
    }

    public static void saveToFile(Image image) {
        File outputFile = new File("F:/test/");
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onPreview(ActionEvent evt) {
        vbPlaceHolder.getChildren().clear();
        webEngine = wvPreview.getEngine();
        webEngine.loadContent(builtHtmlPage());
        vbPlaceHolder.getChildren().add(wvPreview);
        btnPages.setDisable(false);
    }

    private String builtHtmlPage() {
        String htmlPage = HtmlPart.MENU;
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
        vbPlaceHolder.getChildren().clear();
        FXMLLoader fxmlLoader = Helper.createView(Screens.PAGES);
        pagesView = fxmlLoader.getController();
        pagesView.setStage(stage);
        pagesView.setHtmlContent(builtHtmlPage());
        stage.addEventFilter(PopOverEvent.CLOSE, e -> {
            vbPlaceHolder.getChildren().clear();
        });
        vbPlaceHolder.getChildren().add(fxmlLoader.getRoot());
        btnPages.setDisable(true);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.addEventFilter(PageEvent.ADD_MENU_POINT, evt -> {
            evt.consume();
            pagesView.setHtmlContent(builtHtmlPage());
        });
    }

}
