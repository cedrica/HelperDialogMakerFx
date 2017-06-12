package com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.image.ImageMusterView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.imagetext.ImageTextMusterView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.text.TextMusterView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.textimage.TextImageMusterView;
import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.customcontrol.helpdialogmaker.enums.Muster;
import com.customcontrol.helpdialogmaker.event.PageEvent;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class ConfiguratorController implements Initializable {

    @FXML
    ComboBox<Muster> cbMuster;

    @FXML
    VBox vbMusterContainer;

    private static int ROW_INDEX = 0;

    @FXML
    ConfiguratorView configuratorView;

    @FXML
    Button btnAddNewRow;

    @FXML Button btnSave;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        cbMuster.setItems(FXCollections.observableList(Arrays.asList(Muster.values())));
        configuratorView.selectedMusterProperty().bind(cbMuster.getSelectionModel().selectedItemProperty());
        configuratorView.musterComponentProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal == null){
                cbMuster.getSelectionModel().clearSelection();
                vbMusterContainer.getChildren().clear();
                configuratorView.setMusterCount(vbMusterContainer.getChildren().size());
                ROW_INDEX = 0;
                return;
            }
            vbMusterContainer.getChildren().add(newVal);
            configuratorView.setMusterCount(vbMusterContainer.getChildren().size());
            ROW_INDEX++;
            configuratorView.fireEvent(new ConfiguratorEvent(ConfiguratorEvent.DIS_OR_ENABLE_SAVE, 0));
        });
        configuratorView.musterIndexProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != -1)
                removeRow(newVal.intValue());
            configuratorView.setMusterIndex(-1);
        });
        btnAddNewRow.disableProperty().bind(cbMuster.getSelectionModel().selectedItemProperty().isNull());
        btnSave.disableProperty().bind(configuratorView.saveDisableProperty());
    }

    public void onSavePage(ActionEvent evt) {
        StringBuilder htmlContent = new StringBuilder();
        List<ConfigurationData> configurationDatas = new ArrayList<>();
        for (Node row : vbMusterContainer.getChildren()) {
            if (row instanceof ImageTextMusterView) {
                htmlContent.append(((ImageTextMusterView) row).getWholeHtmlContent());
                configurationDatas.add(((ImageTextMusterView) row).getConfigurationData());
            } else if (row instanceof ImageMusterView) {
                htmlContent.append(((ImageMusterView) row).getWholeHtmlContent());
                configurationDatas.add(((ImageMusterView) row).getConfigurationData());
            } else if (row instanceof TextImageMusterView) {
                htmlContent.append(((TextImageMusterView) row).getWholeHtmlContent());
                configurationDatas.add(((TextImageMusterView) row).getConfigurationData());
            } else if (row instanceof TextMusterView) {
                htmlContent.append(((TextMusterView) row).getWholeHtmlContent());
                configurationDatas.add(((TextMusterView) row).getConfigurationData());
            }
        }
        ROW_INDEX = 0;
        configuratorView.fireEvent(new PageEvent(PageEvent.PAGE_CONFIGURATION, configuratorView.getPageIndex(), htmlContent.toString(), FXCollections.observableList(configurationDatas)));
        vbMusterContainer.getChildren().clear();
        configuratorView.setMusterCount(vbMusterContainer.getChildren().size());
    }

    public void onAddNewRow(ActionEvent evt) {
        Muster muster = configuratorView.getSelectedMuster();
        configuratorView.fireEvent(new ConfiguratorEvent(ConfiguratorEvent.ADD_NEW_ROW, muster));
    }

    public void removeRow(int musterNr) {
        if (vbMusterContainer.getChildren().size() > 0) {
            vbMusterContainer.getChildren().remove(musterNr);
            for (Node row : vbMusterContainer.getChildren()) {
                if (row instanceof ImageTextMusterView) {
                    int index = ((ImageTextMusterView) row).getPosInVbMusterContainer() - 1;
                    ((ImageTextMusterView) row).setPosInVbMusterContainer(index = (index < 0) ? 0 : index);
                } else if (row instanceof ImageMusterView) {
                    int index = ((ImageMusterView) row).getPosInVbMusterContainer() - 1;
                    ((ImageMusterView) row).setPosInVbMusterContainer(index = (index < 0) ? 0 : index);
                } else if (row instanceof TextImageMusterView) {
                    int index = ((TextImageMusterView) row).getPosInVbMusterContainer() - 1;
                    ((TextImageMusterView) row).setPosInVbMusterContainer(index = (index < 0) ? 0 : index);
                } else if (row instanceof TextMusterView) {
                    int index = ((TextMusterView) row).getPosInVbMusterContainer() - 1;
                    ((TextMusterView) row).setPosInVbMusterContainer(index = (index < 0) ? 0 : index);
                }
            }
            --ROW_INDEX;
            configuratorView.setMusterCount(vbMusterContainer.getChildren().size());
            return;
        }
    }

    public void onCancel(ActionEvent evt) {
        configuratorView.fireEvent(new PageEvent(PageEvent.UPDATE_PREVIEW,configuratorView.getPageIndex(),null,null));
        ROW_INDEX = 0;
        vbMusterContainer.getChildren().clear();
        configuratorView.setMusterCount(vbMusterContainer.getChildren().size());
    }

}
