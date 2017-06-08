package com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.image;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;

import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.customcontrol.helpdialogmaker.enums.Muster;
import com.customcontrol.helpdialogmaker.event.PopOverEvent;
import com.customcontrol.helpdialogmaker.helper.DialogHelper;
import com.customcontrol.helpdialogmaker.helper.Helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

public class ImageMusterController implements Initializable {

    @FXML
    Button btnRemoveRow;

    @FXML
    Button btnSave;

    @FXML
    Button btnEdit;

    @FXML
    HBox hbViewer;

    @FXML
    WebView webView;

    @FXML
    HBox hbEditor;

    @FXML
    VBox vbLoadedImage;

    @FXML
    ImageView ivLoadedImage;

    @FXML
    Button btnChangeImage;

    @FXML
    Button btnChooseImage;

    private WebEngine webEngine;

    @FXML
    ImageMusterView imageMusterView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnChooseImage.setOnAction(this::onFileChooserAction);
        btnSave.setOnAction(this::onSave);
        btnEdit.setOnAction(this::onEdit);
        btnChangeImage.setOnAction(this::onChangeImage);
        btnRemoveRow.setOnAction(this::onRemoveRow);
        webEngine = webView.getEngine();
        btnSave.setDisable(true);
        btnEdit.setVisible(false);
        imageMusterView.imageInImageViewProperty().addListener((obs, oldVal, newVal) -> {
            setImageInImageView(newVal);
        });

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
                imageMusterView.setImageBytes(FileUtils.readFileToByteArray(file));
                imageMusterView.setImageName(file.getName());
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
        imageMusterView.setImageBytes(bytes);
        InputStream is = new ByteArrayInputStream(bytes);
        Image image = new Image(is);
        ivLoadedImage.setImage(image);
        vbLoadedImage.setVisible(true);
        btnChooseImage.setVisible(false);
        btnSave.setDisable(false);
    }

    public void onRemoveRow(ActionEvent evt) {
        imageMusterView.setConfigurationData(null);
        imageMusterView.fireEvent(new PopOverEvent(PopOverEvent.REMOVE_CONFIGURATION, imageMusterView.getPosInVbMusterContainer(), false));
    }

    public void onSave(ActionEvent evt) {
        if (imageMusterView.getImageBytes() == null || imageMusterView.getImageBytes().length == 0) {
            DialogHelper.error("Error", "Bild ist zwingen", imageMusterView.getScene().getWindow(), ButtonType.OK);
            return;
        }
        builtWholeContent();
        ConfigurationData configurationData = new ConfigurationData();
        configurationData.setHtmlText(imageMusterView.getHtmlText());
        configurationData.setImage(imageMusterView.getImageBytes());
        configurationData.setMuster(Muster.IMAGE);
        imageMusterView.setConfigurationData(configurationData);
        hbEditor.setVisible(false);
        hbViewer.setVisible(true);
        webEngine.loadContent(imageMusterView.getWholeHtmlContent());
        btnEdit.setVisible(true);
        btnSave.setVisible(false);

    }

    public void builtWholeContent() {

        Helper.saveImageLocaly(imageMusterView.getImageBytes(), imageMusterView.getImageName());
        String path = System.getProperty("user.dir") + "/images/" + imageMusterView.getImageName();
        path = path.replace("\\", "/");
        imageMusterView.setWholeHtmlContent("<div style=\"width:100%\">"
                + "<div style=\"margin:auto\">"
                + "<img src='file:///" + path + "' class=\"media-object\">"
                + "</div></div>");
    }

    public void onEdit(ActionEvent evt) {
        hbEditor.setVisible(true);
        hbViewer.setVisible(false);
        btnEdit.setVisible(false);
        btnSave.setVisible(true);
    }

}
