package com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.textimage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;

import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.ConfiguratorEvent;
import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.customcontrol.helpdialogmaker.enums.Muster;
import com.customcontrol.helpdialogmaker.helper.Helper;
import com.preag.core.ui.utils.dialog.Dialogs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

public class TextImageMusterController implements Initializable {

    @FXML
    Button              btnRemoveRow;

    @FXML
    Button              btnSave;

    @FXML
    Button              btnEdit;

    @FXML
    HBox                hbViewer;

    @FXML
    WebView             webView;

    @FXML
    HBox                hbEditor;

    @FXML
    HTMLEditor          htmlEditor;

    @FXML
    VBox                vbLoadedImage;

    @FXML
    ImageView           ivLoadedImage;

    @FXML
    Button              btnChangeImage;

    @FXML
    Button              btnChooseImage;

    private WebEngine   webEngine;

    @FXML
    TextImageMusterView textImageMusterView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnChooseImage.setOnAction(this::onFileChooserAction);
        btnSave.setOnAction(this::onSave);
        btnEdit.setOnAction(this::onEdit);
        btnChangeImage.setOnAction(this::onChangeImage);
        btnRemoveRow.setOnAction(this::onRemoveRow);
        webEngine = webView.getEngine();
        btnEdit.setVisible(false);
        textImageMusterView.imageInImageViewProperty().addListener((obs, oldVal, newVal) -> {
            setImageInImageView(newVal);
        });
        textImageMusterView.htmlTextProperty().addListener((obs, oldVal, newVal) -> {
            htmlEditor.setHtmlText(textImageMusterView.getHtmlText());
        });
        textImageMusterView.saveDisableProperty().bind(btnSave.disableProperty());
    }

    public void onChangeImage(ActionEvent evt) {
        onFileChooserAction(evt);
    }

    public void onFileChooserAction(ActionEvent evt) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Bild auswählen");
        File file = chooser.showOpenDialog(btnChooseImage.getScene().getWindow());
        if (file != null) {
            try {
                textImageMusterView.setImageBytes(FileUtils.readFileToByteArray(file));
                textImageMusterView.setImageName(file.getName());
                InputStream is = FileUtils.openInputStream(file);
                Image image = new Image(is);
                ivLoadedImage.setImage(image);
                vbLoadedImage.setVisible(true);
                btnChooseImage.setVisible(false);
                btnSave.setDisable(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            btnSave.setDisable(true);
        }
    }

    public void setImageInImageView(byte[] bytes) {
        textImageMusterView.setImageBytes(bytes);
        InputStream is = new ByteArrayInputStream(bytes);
        Image image = new Image(is);
        ivLoadedImage.setImage(image);
        vbLoadedImage.setVisible(true);
        btnChooseImage.setVisible(false);
        btnSave.setDisable(false);
    }

    public void setHtmlEditorText(String text) {
        htmlEditor.setHtmlText(text);
    }

    public void onRemoveRow(ActionEvent evt) {
        textImageMusterView.setConfigurationData(null);
        textImageMusterView.fireEvent(new ConfiguratorEvent(ConfiguratorEvent.REMOVE_MUSTER, textImageMusterView.getPosInVbMusterContainer(), -1));
    }

    public void onSave(ActionEvent evt) {
        if (textImageMusterView.getImageBytes() == null || textImageMusterView.getImageBytes().length == 0) {
            Dialog<ButtonType> error = Dialogs.error("Bild ist erforderlich", textImageMusterView.getScene().getWindow());
            error.showAndWait();
            return;
        }
        textImageMusterView.fireEvent(new ConfiguratorEvent(ConfiguratorEvent.DIS_OR_ENABLE_SAVE, 1));
        textImageMusterView.setHtmlText(htmlEditor.getHtmlText());
        builtWholeContent();
        ConfigurationData configurationData = new ConfigurationData();
        configurationData.setHtmlText(textImageMusterView.getHtmlText());
        configurationData.setImage(textImageMusterView.getImageBytes());
        configurationData.setMuster(Muster.TEXT_IMAGE);
        textImageMusterView.setConfigurationData(configurationData);
        hbEditor.setVisible(false);
        hbViewer.setVisible(true);
        webEngine.loadContent(textImageMusterView.getWholeHtmlContent());
        btnEdit.setVisible(true);
        btnSave.setVisible(false);

    }

    public void builtWholeContent() {
        Helper.saveImageLocaly(textImageMusterView.getImageBytes(), textImageMusterView.getImageName());
        String path = System.getProperty("user.dir") + "/images/" + textImageMusterView.getImageName();
        path = path.replace("\\", "/");
        String innerHtml = textImageMusterView.getHtmlText().replace("<html dir=\"ltr\"><head></head><body contenteditable=\"true\">", "").replace("</body></html>", "");
        textImageMusterView.setWholeHtmlContent("<div class=\"media\">"
                + "<div style=\"float:left;margin-right:5px;text-align:justify;\">"
                + innerHtml
                + "</div>"
                + "<div class=\"media-left media-middle\">"
                + "<img src='file:///" + path + "' class=\"media-object\">"
                + "</div>"
                + "</div>");
    }

    public void onEdit(ActionEvent evt) {
        textImageMusterView.fireEvent(new ConfiguratorEvent(ConfiguratorEvent.DIS_OR_ENABLE_SAVE, -1));
        hbEditor.setVisible(true);
        hbViewer.setVisible(false);
        btnEdit.setVisible(false);
        btnSave.setVisible(true);
    }

}
