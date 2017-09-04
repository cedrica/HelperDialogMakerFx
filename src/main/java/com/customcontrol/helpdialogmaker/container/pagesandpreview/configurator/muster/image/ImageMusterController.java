package com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.image;

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

    @FXML
    Button btnMoveUp;

    @FXML
    Button btnMoveDown;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnChooseImage.setOnAction(this::onFileChooserAction);
        btnSave.setOnAction(this::onSave);
        btnEdit.setOnAction(this::onEdit);
        btnChangeImage.setOnAction(this::onChangeImage);
        btnRemoveRow.setOnAction(this::onRemoveRow);
        webEngine = webView.getEngine();
        btnEdit.setVisible(false);
        imageMusterView.imageInImageViewProperty().addListener((obs, oldVal, newVal) -> {
            setImageInImageView(newVal);
        });
        imageMusterView.saveDisableProperty().bind(btnSave.disableProperty());

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
        imageMusterView.fireEvent(new ConfiguratorEvent(ConfiguratorEvent.REMOVE_MUSTER, imageMusterView.getPosInVbMusterContainer(), -1));
    }

    public void onSave(ActionEvent evt) {
        if (imageMusterView.getImageBytes() == null || imageMusterView.getImageBytes().length == 0) {
            Dialog<ButtonType> error = Dialogs.error("Bild ist erforderlich", imageMusterView.getScene().getWindow());
            error.showAndWait();
            return;
        }
        imageMusterView.fireEvent(new ConfiguratorEvent(ConfiguratorEvent.DIS_OR_ENABLE_SAVE,imageMusterView.getPosInVbMusterContainer(), 1));
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
        imageMusterView.fireEvent(new ConfiguratorEvent(ConfiguratorEvent.DIS_OR_ENABLE_SAVE,-1, -1));
        hbEditor.setVisible(true);
        hbViewer.setVisible(false);
        btnEdit.setVisible(false);
        btnSave.setVisible(true);
    }

    @FXML
    public void onMoveUp() {
        imageMusterView.fireEvent(new ConfiguratorEvent(ConfiguratorEvent.MOVE_UP, imageMusterView.getPosInVbMusterContainer()));

    }


	@FXML public void onMoveDown(ActionEvent event) {  imageMusterView.fireEvent(new ConfiguratorEvent(ConfiguratorEvent.MOVE_DOWN, imageMusterView.getPosInVbMusterContainer()));}

}
