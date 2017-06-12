package com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.text;

import java.net.URL;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.ConfiguratorEvent;
import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.customcontrol.helpdialogmaker.enums.Muster;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class TextMusterController implements Initializable {

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
    HTMLEditor htmlEditor;

    private WebEngine webEngine;

    @FXML
    TextMusterView textMusterView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnSave.setOnAction(this::onSave);
        btnEdit.setOnAction(this::onEdit);
        btnRemoveRow.setOnAction(this::onRemoveRow);
        webEngine = webView.getEngine();
        btnEdit.setVisible(false);

        textMusterView.htmlTextProperty().addListener((obs, oldVal, newVal) -> {
            setHtmlEditorText(newVal);
        });
        textMusterView.saveDisableProperty().bind(btnSave.disableProperty());
    }

    public void setHtmlEditorText(String text) {
        htmlEditor.setHtmlText(text);
    }

    public void onRemoveRow(ActionEvent evt) {
        textMusterView.setConfigurationData(null);
        textMusterView.fireEvent(new ConfiguratorEvent(ConfiguratorEvent.REMOVE_MUSTER, textMusterView.getPosInVbMusterContainer(), -1));
    }

    public void onSave(ActionEvent evt) {
        textMusterView.fireEvent(new ConfiguratorEvent(ConfiguratorEvent.DIS_OR_ENABLE_SAVE,textMusterView.getPosInVbMusterContainer(), 1));
        textMusterView.setHtmlText(htmlEditor.getHtmlText());
        builtWholeContent();
        ConfigurationData configurationData = new ConfigurationData();
        configurationData.setHtmlText(textMusterView.getHtmlText());
        configurationData.setMuster(Muster.TEXT);
        textMusterView.setConfigurationData(configurationData);
        hbEditor.setVisible(false);
        hbViewer.setVisible(true);
        webEngine.loadContent(textMusterView.getWholeHtmlContent());
        btnEdit.setVisible(true);
        btnSave.setVisible(false);
    }

    public void builtWholeContent() {
        String innerHtml = textMusterView.getHtmlText().replace("<html dir=\"ltr\"><head></head><body contenteditable=\"true\">", "").replace("</body></html>", "");
        textMusterView.setWholeHtmlContent(innerHtml);
    }

    public void onEdit(ActionEvent evt) {
        textMusterView.fireEvent(new ConfiguratorEvent(ConfiguratorEvent.DIS_OR_ENABLE_SAVE,textMusterView.getPosInVbMusterContainer(), -1));
        hbEditor.setVisible(true);
        hbViewer.setVisible(false);
        btnEdit.setVisible(false);
        btnSave.setVisible(true);
    }

    @FXML
    public void onMoveUp() {
        textMusterView.fireEvent(new ConfiguratorEvent(ConfiguratorEvent.MOVE_UP, textMusterView.getPosInVbMusterContainer()));

    }

    @FXML
    public void onMoveDown() {
        textMusterView.fireEvent(new ConfiguratorEvent(ConfiguratorEvent.MOVE_DOWN, textMusterView.getPosInVbMusterContainer()));
    }

}
