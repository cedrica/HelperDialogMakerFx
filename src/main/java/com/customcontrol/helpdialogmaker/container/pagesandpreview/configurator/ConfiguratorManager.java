package com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.customcontrol.helpdialogmaker.container.pagesandpreview.PagesAndPreview;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.image.ImageMusterView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.imagetext.ImageTextMusterView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.text.TextMusterView;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.textimage.TextImageMusterView;
import com.customcontrol.helpdialogmaker.enums.Muster;
import com.customcontrol.helpdialogmaker.event.PageEvent;
import com.customcontrol.helpdialogmaker.event.PopOverEvent;
import com.customcontrol.helpdialogmaker.model.OldConfigurationData;

@Singleton
public class ConfiguratorManager {

    @Inject
    private ConfiguratorView configuratorView;

    public void handleAddedEvents() {
        configuratorView.addEventHandler(PopOverEvent.INIT_MUSTER_CONTAINER, evt -> {
            evt.consume();assignOldConfiguration(evt);
        });

        configuratorView.addEventHandler(ConfiguratorEvent.ADD_NEW_ROW, evt -> {
            evt.consume(); addNewRow(evt.getMuster());
        });
        
        configuratorView.addEventHandler(PageEvent.REMOVE_CONFIGURATION, evt -> {
            evt.consume();
            configuratorView.removeMuster(evt.getMusterIndex());
        });
    }

    public void assignOldConfiguration(PopOverEvent evt) {
        List<OldConfigurationData> oldConfigurationDatas = evt.getOldConfigurationDatas();
        for (OldConfigurationData oldConfigurationData : oldConfigurationDatas) {
            Muster muster = oldConfigurationData.getMuster();
            if (muster == Muster.IMAGE) {
                ImageMusterView imageMusterView = new ImageMusterView();
                imageMusterView.setImageInImageView(oldConfigurationData.getImage());
                configuratorView.setMusterComponent(imageMusterView);
            } else if (muster == Muster.TEXT) {
                TextMusterView textMusterView = new TextMusterView();
                textMusterView.setHtmlEditorText(oldConfigurationData.getHtmlText());
                configuratorView.setMusterComponent(textMusterView);
            } else if (muster == Muster.TEXT_IMAGE) {
                TextImageMusterView textImageMusterView = new TextImageMusterView();
                textImageMusterView.setImageInImageView(oldConfigurationData.getImage());
                textImageMusterView.setHtmlEditorText(oldConfigurationData.getHtmlText());
                configuratorView.setMusterComponent(textImageMusterView);
            } else if (muster == Muster.IMAGE_TEXT) {
                ImageTextMusterView imageTextMusterView = new ImageTextMusterView();
                imageTextMusterView.setImageBytes(oldConfigurationData.getImage());
                imageTextMusterView.setHtmlText(oldConfigurationData.getHtmlText());
                configuratorView.setMusterComponent(imageTextMusterView);
            }
        }
    }

    public void addNewRow(Muster muster) {
        if (muster == Muster.IMAGE) {
            ImageMusterView imageMusterView = new ImageMusterView();
            configuratorView.setMusterComponent(imageMusterView);
        } else if (muster == Muster.TEXT) {
            TextMusterView textMusterView = new TextMusterView();
            configuratorView.setMusterComponent(textMusterView);
        } else if (muster == Muster.TEXT_IMAGE) {
            TextImageMusterView textImageMusterView = new TextImageMusterView();
            configuratorView.setMusterComponent(textImageMusterView);
        } else if (muster == Muster.IMAGE_TEXT) {
            ImageTextMusterView imageTextMusterView = new ImageTextMusterView();
            configuratorView.setMusterComponent(imageTextMusterView);
        }
    }

    public void showConfigurator(PagesAndPreview pagesAndPreview) {
        pagesAndPreview.setPlaceHolder(configuratorView);
    }


}
