package com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.image.ImageMusterView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.imagetext.ImageTextMusterView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.text.TextMusterView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.textimage.TextImageMusterView;
import com.customcontrol.helpdialogmaker.enums.Muster;
import com.customcontrol.helpdialogmaker.event.PageEvent;
import com.customcontrol.helpdialogmaker.helper.Helper;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConfiguratorController implements Initializable {

    private static boolean A_ROW_WAS_REMOVED = false;

    @FXML
    TextArea taContent;

    @FXML
    Label lblTitle;

    @FXML
    Label lblInfo;

    @FXML
    ComboBox<Muster> cbMuster;

    @FXML
    VBox vbInfo;

    @FXML
    HBox hbRow;

    @FXML
    VBox vbMusterContainer;


    private static int ROW_INDEX = 0;

    @FXML
    ConfiguratorView configuratorView;

    @FXML
    Button btnAddNewRow;

    @FXML
    Button btnSavePage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Helper.roundVBox(vbInfo, 30);
        cbMuster.setItems(FXCollections.observableList(Arrays.asList(Muster.values())));
        configuratorView.selectedMusterProperty().bind(cbMuster.getSelectionModel().selectedItemProperty());
        configuratorView.musterComponentProperty().addListener((obs, oldVal, newVal) -> {
            vbMusterContainer.getChildren().add(ROW_INDEX, newVal);
            ROW_INDEX++;
        });
        configuratorView.musterIndexProperty().addListener((obs, oldVal, newVal) -> {
            removeRow(newVal.intValue());
        });
        btnAddNewRow.disableProperty().bind(cbMuster.getSelectionModel().selectedItemProperty().isNull());
//        btnAddNewRow.getStyleClass().add("button-raised");
        lblTitle.textProperty().bind(configuratorView.titleProperty());
    }

    public void onSavePage(ActionEvent evt) {
        StringBuilder htmlContent = new StringBuilder();
        for (Node row : vbMusterContainer.getChildren()) {
            if (row instanceof ImageTextMusterView) {
                htmlContent.append(((ImageTextMusterView) row).getHtmlText());
                configuratorView.getOldConfigurationDatas().add(((ImageTextMusterView) row).getOldConfigurationData());
            } else if (row instanceof ImageMusterView) {
                htmlContent.append(((ImageMusterView) row).getWholeHtmlContent());
                configuratorView.getOldConfigurationDatas().add(((ImageMusterView) row).getOldConfigurationData());
            } else if (row instanceof TextImageMusterView) {
                htmlContent.append(((TextImageMusterView) row).getWholeHtmlContent());
                configuratorView.getOldConfigurationDatas().add(((TextImageMusterView) row).getOldConfigurationData());
            } else if (row instanceof TextMusterView) {
                htmlContent.append(((TextMusterView) row).getWholeHtmlContent());
                configuratorView.getOldConfigurationDatas().add(((TextMusterView) row).getOldConfigurationData());
            }
        }

        transferConfigurationToPage(htmlContent);
        configuratorView.fireEvent(new PageEvent(PageEvent.UPDATE_PREVIEW));
        ROW_INDEX = 0;
        A_ROW_WAS_REMOVED = false;

    }

    private void transferConfigurationToPage(StringBuilder htmlContent) {
    }

    public void onAddNewRow(ActionEvent evt) {
        Muster muster = configuratorView.getSelectedMuster();
        configuratorView.fireEvent(new ConfiguratorEvent(ConfiguratorEvent.ADD_NEW_ROW, muster));
    }


    public void removeRow(int configurationNr) {
        if (vbMusterContainer.getChildren().size() == 1) {
            vbMusterContainer.getChildren().remove(0);
            ROW_INDEX--;
            return;
        }
        if (A_ROW_WAS_REMOVED) {
            int index = configurationNr - 1;
            if (index >= 0 && index <= vbMusterContainer.getChildren().size() - 1) {
                vbMusterContainer.getChildren().remove(index);
            } else if (index == vbMusterContainer.getChildren().size()) {
                vbMusterContainer.getChildren().remove(index - 1);
            } else if (vbMusterContainer.getChildren().size() > 0) {
                vbMusterContainer.getChildren().remove(0);
            }
        } else {
            vbMusterContainer.getChildren().remove(configurationNr);
            A_ROW_WAS_REMOVED = true;
        }
        ROW_INDEX--;
    }


    @FXML public void onClose() {}

}
