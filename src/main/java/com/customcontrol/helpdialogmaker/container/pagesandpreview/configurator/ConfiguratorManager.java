package com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator;

import javax.inject.Singleton;

import com.customcontrol.helpdialogmaker.container.pagesandpreview.PagesAndPreview;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.image.ImageMusterView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.imagetext.ImageTextMusterView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.text.TextMusterView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.textimage.TextImageMusterView;
import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.customcontrol.helpdialogmaker.enums.Muster;

import javafx.collections.ObservableList;

@Singleton
public class ConfiguratorManager {

    private ConfiguratorView configuratorView;

    public void showConfigurator(PagesAndPreview pagesAndPreview, int pageIndex, String pageName,
                                 ObservableList<ConfigurationData> configurationDatas) {
        configuratorView = new ConfiguratorView();
        registerEventHandler();
        assignConfiguration(configurationDatas);
        configuratorView.setPageIndex(pageIndex);
        configuratorView.setPageName(pageName);
        pagesAndPreview.setPlaceHolder(configuratorView);
    }

    public void registerEventHandler() {
        configuratorView.setSaveDisable(true);
        configuratorView.addEventHandler(ConfiguratorEvent.DIS_OR_ENABLE_SAVE, evt -> {
            evt.consume();
            handleDisabling(evt);
        });
        configuratorView.addEventHandler(ConfiguratorEvent.ADD_NEW_ROW, evt -> {
            evt.consume();
            addNewRow(evt.getMuster());
        });

        configuratorView.addEventHandler(ConfiguratorEvent.REMOVE_MUSTER, evt -> {
            evt.consume();
            configuratorView.removeMuster(evt.getMusterIndex());
            handleDisabling(evt);
        });
        configuratorView.addEventHandler(ConfiguratorEvent.MOVE_DOWN, evt -> {
            evt.consume();
        });
        configuratorView.addEventHandler(ConfiguratorEvent.MOVE_UP, evt -> {
            evt.consume();
            configuratorView.setMoveUp(evt.getMoveUpDown());
        });
    }

    public void handleDisabling(ConfiguratorEvent evt) {
        configuratorView.setCounterSave((configuratorView.getCounterSave() < 0) ? 0
                : configuratorView.getCounterSave() + evt.getIncOrDec());
        if (configuratorView.getCounterSave() == configuratorView.getMusterCount()
                && configuratorView.getMusterCount() > 0) {
            configuratorView.setSaveDisable(false);
        } else {
            configuratorView.setSaveDisable(true);
        }
    }

    public void assignConfiguration(ObservableList<ConfigurationData> configurationDatas) {
        if (configurationDatas == null) {
            configuratorView.setMusterComponent(null);
            return;
        }

        for (ConfigurationData configurationData : configurationDatas) {
            Muster muster = configurationData.getMuster();
            if (muster == Muster.IMAGE) {
                ImageMusterView imageMusterView = new ImageMusterView();
                imageMusterView.setImageInImageView(configurationData.getImage());
                imageMusterView.setImageBytes(configurationData.getImage());
                imageMusterView.setPosInVbMusterContainer(configuratorView.getLastMusterIndex());
                configuratorView.setMusterComponent(imageMusterView);
            } else if (muster == Muster.TEXT) {
                TextMusterView textMusterView = new TextMusterView();
                textMusterView.setHtmlText(configurationData.getHtmlText());
                textMusterView.setPosInVbMusterContainer(configuratorView.getLastMusterIndex());
                configuratorView.setMusterComponent(textMusterView);
            } else if (muster == Muster.TEXT_IMAGE) {
                TextImageMusterView textImageMusterView = new TextImageMusterView();
                textImageMusterView.setImageInImageView(configurationData.getImage());
                textImageMusterView.setImageBytes(configurationData.getImage());
                textImageMusterView.setHtmlText(configurationData.getHtmlText());
                textImageMusterView.setPosInVbMusterContainer(configuratorView.getLastMusterIndex());
                configuratorView.setMusterComponent(textImageMusterView);
            } else if (muster == Muster.IMAGE_TEXT) {
                ImageTextMusterView imageTextMusterView = new ImageTextMusterView();
                imageTextMusterView.setImageInImageView(configurationData.getImage());
                imageTextMusterView.setImageBytes(configurationData.getImage());
                imageTextMusterView.setHtmlText(configurationData.getHtmlText());
                imageTextMusterView.setPosInVbMusterContainer(configuratorView.getLastMusterIndex());
                configuratorView.setMusterComponent(imageTextMusterView);

            }
        }

    }

    public void addNewRow(Muster muster) {
        if (muster == Muster.IMAGE) {
            ImageMusterView imageMusterView = new ImageMusterView();
            imageMusterView.setPosInVbMusterContainer(configuratorView.getLastMusterIndex());
            configuratorView.setMusterComponent(imageMusterView);
        } else if (muster == Muster.TEXT) {
            TextMusterView textMusterView = new TextMusterView();
            textMusterView.setPosInVbMusterContainer(configuratorView.getLastMusterIndex());
            configuratorView.setMusterComponent(textMusterView);
        } else if (muster == Muster.TEXT_IMAGE) {
            TextImageMusterView textImageMusterView = new TextImageMusterView();
            textImageMusterView.setPosInVbMusterContainer(configuratorView.getLastMusterIndex());
            configuratorView.setMusterComponent(textImageMusterView);
        } else if (muster == Muster.IMAGE_TEXT) {
            ImageTextMusterView imageTextMusterView = new ImageTextMusterView();
            imageTextMusterView.setPosInVbMusterContainer(configuratorView.getLastMusterIndex());
            configuratorView.setMusterComponent(imageTextMusterView);

        }

    }

}
