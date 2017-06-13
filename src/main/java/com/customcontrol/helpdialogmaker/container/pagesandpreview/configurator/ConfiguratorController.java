package com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.container.ContainerService;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.image.ImageMusterView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.imagetext.ImageTextMusterView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.text.TextMusterView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.textimage.TextImageMusterView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page.PageEvent;
import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.customcontrol.helpdialogmaker.enums.Muster;

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

    @FXML
    ConfiguratorView configuratorView;

    @FXML
    Button btnAddNewRow;

    @FXML
    Button btnSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbMuster.setItems(FXCollections.observableList(Arrays.asList(Muster.values())));
        registerListener();
        registerBinding();
    }

    public void registerListener() {
        configuratorView.moveUpProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != -1)
                doMoveUp(newVal);
            configuratorView.setMoveUp(-1);
        });
        configuratorView.moveDownProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != -1)
                doMoveDown(newVal);
            configuratorView.setMoveDown(-1);
        });
        configuratorView.musterComponentProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) {
                cbMuster.getSelectionModel().clearSelection();
                vbMusterContainer.getChildren().clear();
                configuratorView.setMusterCount(vbMusterContainer.getChildren().size());
                return;
            }
            vbMusterContainer.getChildren().add(newVal);
            configuratorView.setLastMusterIndex(vbMusterContainer.getChildren().size());
            configuratorView.setMusterCount(vbMusterContainer.getChildren().size());
            configuratorView.fireEvent(new ConfiguratorEvent(ConfiguratorEvent.DIS_OR_ENABLE_SAVE, -1, 0));
        });
        configuratorView.musterIndexProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != -1)
                removeRow(newVal.intValue());
            configuratorView.setMusterIndex(-1);
        });
    }

    public void doMoveDown(Integer newVal) {
        for (Iterator<?> it = vbMusterContainer.getChildren().iterator(); it.hasNext();) {
            Object node = it.next();
            if (node instanceof ImageTextMusterView) {
                if (((ImageTextMusterView) node).getPosInVbMusterContainer() == newVal.intValue()) {
                    int pos = ((ImageTextMusterView) node).getPosInVbMusterContainer();
                    vbMusterContainer.getChildren().remove(pos);
                    pos = (pos == vbMusterContainer.getChildren().size()) ? pos : pos + 1;
                    ((ImageTextMusterView) node).setPosInVbMusterContainer(pos);
                    vbMusterContainer.getChildren().add(pos, (ImageTextMusterView) node);
                    adjustPositionAfterDown(pos - 1);
                    break;
                }
            } else if (node instanceof ImageMusterView) {
                if (((ImageMusterView) node).getPosInVbMusterContainer() == newVal.intValue()) {
                    int pos = ((ImageMusterView) node).getPosInVbMusterContainer();
                    vbMusterContainer.getChildren().remove(pos);
                    pos = (pos == vbMusterContainer.getChildren().size()) ? pos : pos + 1;
                    ((ImageMusterView) node).setPosInVbMusterContainer(pos);
                    vbMusterContainer.getChildren().add(pos, (ImageMusterView) node);
                    adjustPositionAfterDown(pos - 1);
                    break;
                }
            } else if (node instanceof TextImageMusterView) {
                if (((TextImageMusterView) node).getPosInVbMusterContainer() == newVal.intValue()) {
                    int pos = ((TextImageMusterView) node).getPosInVbMusterContainer();
                    vbMusterContainer.getChildren().remove(pos);
                    pos = (pos == vbMusterContainer.getChildren().size()) ? pos : pos + 1;
                    ((TextImageMusterView) node).setPosInVbMusterContainer(pos);
                    vbMusterContainer.getChildren().add(pos, (TextImageMusterView) node);
                    adjustPositionAfterDown(pos - 1);
                    break;
                }
            } else if (node instanceof TextMusterView) {
                if (((TextMusterView) node).getPosInVbMusterContainer() == newVal.intValue()) {
                    int pos = ((TextMusterView) node).getPosInVbMusterContainer();
                    vbMusterContainer.getChildren().remove(pos);
                    pos = (pos == vbMusterContainer.getChildren().size()) ? pos : pos + 1;
                    ((TextMusterView) node).setPosInVbMusterContainer(pos);
                    vbMusterContainer.getChildren().add(pos, (TextMusterView) node);
                    adjustPositionAfterDown(pos - 1);
                    break;
                }
            }
        }
    }

    public void doMoveUp(Integer newVal) {
        for (Iterator<?> it = vbMusterContainer.getChildren().iterator(); it.hasNext();) {
            Object node = it.next();
            if (node instanceof ImageTextMusterView) {
                if (((ImageTextMusterView) node).getPosInVbMusterContainer() == newVal.intValue()) {
                    int pos = ((ImageTextMusterView) node).getPosInVbMusterContainer();
                    vbMusterContainer.getChildren().remove(pos);
                    pos = (pos - 1 < 0) ? 0 : pos - 1;
                    ((ImageTextMusterView) node).setPosInVbMusterContainer(pos);
                    vbMusterContainer.getChildren().add(pos, (ImageTextMusterView) node);
                    adjustPositionAfterUp(pos + 1);
                    break;
                }
            } else if (node instanceof ImageMusterView) {
                if (((ImageMusterView) node).getPosInVbMusterContainer() == newVal.intValue()) {
                    int pos = ((ImageMusterView) node).getPosInVbMusterContainer();
                    vbMusterContainer.getChildren().remove(pos);
                    pos = (pos - 1 < 0) ? 0 : pos - 1;
                    ((ImageMusterView) node).setPosInVbMusterContainer(pos);
                    vbMusterContainer.getChildren().add(pos, (ImageMusterView) node);
                    adjustPositionAfterUp(pos + 1);
                    break;
                }
            } else if (node instanceof TextImageMusterView) {
                if (((TextImageMusterView) node).getPosInVbMusterContainer() == newVal.intValue()) {
                    int pos = ((TextImageMusterView) node).getPosInVbMusterContainer();
                    vbMusterContainer.getChildren().remove(pos);
                    pos = (pos - 1 < 0) ? 0 : pos - 1;
                    ((TextImageMusterView) node).setPosInVbMusterContainer(pos);
                    vbMusterContainer.getChildren().add(pos, (TextImageMusterView) node);
                    adjustPositionAfterUp(pos + 1);
                    break;
                }
            } else if (node instanceof TextMusterView) {
                if (((TextMusterView) node).getPosInVbMusterContainer() == newVal.intValue()) {
                    int pos = ((TextMusterView) node).getPosInVbMusterContainer();
                    vbMusterContainer.getChildren().remove(pos);
                    pos = (pos - 1 < 0) ? 0 : pos - 1;
                    ((TextMusterView) node).setPosInVbMusterContainer(pos);
                    vbMusterContainer.getChildren().add(pos, (TextMusterView) node);
                    adjustPositionAfterUp(pos + 1);
                    break;
                }
            }
        }
    }

    private void adjustPositionAfterUp(int pos) {
        Node node = vbMusterContainer.getChildren().get(pos);
        if (node instanceof ImageTextMusterView) {
            ((ImageTextMusterView) node).setPosInVbMusterContainer(pos);
        } else if (node instanceof ImageMusterView) {
            ((ImageMusterView) node).setPosInVbMusterContainer(pos);
        } else if (node instanceof TextImageMusterView) {
            ((TextImageMusterView) node).setPosInVbMusterContainer(pos);
        } else if (node instanceof TextMusterView) {
            ((TextMusterView) node).setPosInVbMusterContainer(pos);
        }
    }

    private void adjustPositionAfterDown(int pos) {
        Node node = vbMusterContainer.getChildren().get(pos);
        if (node instanceof ImageTextMusterView) {
            ((ImageTextMusterView) node).setPosInVbMusterContainer(pos);
        } else if (node instanceof ImageMusterView) {
            ((ImageMusterView) node).setPosInVbMusterContainer(pos);
        } else if (node instanceof TextImageMusterView) {
            ((TextImageMusterView) node).setPosInVbMusterContainer(pos);
        } else if (node instanceof TextMusterView) {
            ((TextMusterView) node).setPosInVbMusterContainer(pos);
        }
    }

    public void registerBinding() {
        configuratorView.selectedMusterProperty().bind(cbMuster.getSelectionModel().selectedItemProperty());
        btnAddNewRow.disableProperty().bind(cbMuster.getSelectionModel().selectedItemProperty().isNull());
        btnSave.disableProperty().bind(configuratorView.saveDisableProperty());
    }

    public void onSavePage(ActionEvent evt) {
        StringBuilder htmlContent = new StringBuilder();
        List<ConfigurationData> configurationDatas = new ArrayList<>();
        for (Node node : vbMusterContainer.getChildren()) {
            if (node instanceof ImageTextMusterView) {
                htmlContent.append(((ImageTextMusterView) node).getWholeHtmlContent());
                configurationDatas.add(((ImageTextMusterView) node).getConfigurationData());
            } else if (node instanceof ImageMusterView) {
                htmlContent.append(((ImageMusterView) node).getWholeHtmlContent());
                configurationDatas.add(((ImageMusterView) node).getConfigurationData());
            } else if (node instanceof TextImageMusterView) {
                htmlContent.append(((TextImageMusterView) node).getWholeHtmlContent());
                configurationDatas.add(((TextImageMusterView) node).getConfigurationData());
            } else if (node instanceof TextMusterView) {
                htmlContent.append(((TextMusterView) node).getWholeHtmlContent());
                configurationDatas.add(((TextMusterView) node).getConfigurationData());
            }
        }
        configuratorView.fireEvent(new PageEvent(PageEvent.PAGE_CONFIGURATION, configuratorView.getPageIndex(), htmlContent.toString(), FXCollections.observableList(configurationDatas)));
        vbMusterContainer.getChildren().clear();
        ContainerService.CONFIGURATOR_SHOWABLE = true;
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
            configuratorView.setMusterCount(vbMusterContainer.getChildren().size());
            return;
        }
    }

    public void onCancel(ActionEvent evt) {
        configuratorView.fireEvent(new PageEvent(PageEvent.UPDATE_PREVIEW, configuratorView.getPageIndex(), null, null));
        vbMusterContainer.getChildren().clear();
        configuratorView.setMusterCount(vbMusterContainer.getChildren().size());
        ContainerService.CONFIGURATOR_SHOWABLE = true;
    }

}
