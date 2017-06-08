package com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.image;

import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.preag.core.ui.service.FXMLService;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.VBox;

public class ImageMusterView extends VBox {

    private StringProperty htmlText = new SimpleStringProperty();

    private ObjectProperty<byte[]> imageBytes = new SimpleObjectProperty<>();

    private StringProperty imageName = new SimpleStringProperty();

    private IntegerProperty posInVbMusterContainer = new SimpleIntegerProperty();

    private StringProperty wholeHtmlContent = new SimpleStringProperty();

    private ObjectProperty<ConfigurationData> configurationData = new SimpleObjectProperty<>();

    private ObjectProperty<byte[]> imageInImageView = new SimpleObjectProperty<>();

    public ImageMusterView() {
        FXMLService.INSTANCE.loadView(this);
    }

    public final StringProperty htmlTextProperty() {
        return this.htmlText;
    }

    public final String getHtmlText() {
        return this.htmlTextProperty().get();
    }

    public final void setHtmlText(final String htmlText) {
        this.htmlTextProperty().set(htmlText);
    }

    public final ObjectProperty<byte[]> imageInImageViewProperty() {
        return this.imageInImageView;
    }

    public final byte[] getImageInImageView() {
        return this.imageInImageViewProperty().get();
    }

    public final void setImageInImageView(final byte[] imageInImageView) {
        this.imageInImageViewProperty().set(imageInImageView);
    }

    public final IntegerProperty posInVbMusterContainerProperty() {
        return this.posInVbMusterContainer;
    }

    public final int getPosInVbMusterContainer() {
        return this.posInVbMusterContainerProperty().get();
    }

    public final void setPosInVbMusterContainer(final int posInVbMusterContainer) {
        this.posInVbMusterContainerProperty().set(posInVbMusterContainer);
    }

    public final StringProperty imageNameProperty() {
        return this.imageName;
    }

    public final String getImageName() {
        return this.imageNameProperty().get();
    }

    public final void setImageName(final String imageName) {
        this.imageNameProperty().set(imageName);
    }

    public final ObjectProperty<byte[]> imageBytesProperty() {
        return this.imageBytes;
    }

    public final byte[] getImageBytes() {
        return this.imageBytesProperty().get();
    }

    public final void setImageBytes(final byte[] imageBytes) {
        this.imageBytesProperty().set(imageBytes);
    }

    public final StringProperty wholeHtmlContentProperty() {
        return this.wholeHtmlContent;
    }

    public final String getWholeHtmlContent() {
        return this.wholeHtmlContentProperty().get();
    }

    public final void setWholeHtmlContent(final String wholeHtmlContent) {
        this.wholeHtmlContentProperty().set(wholeHtmlContent);
    }

    public final ObjectProperty<ConfigurationData> configurationDataProperty() {
        return this.configurationData;
    }

    public final ConfigurationData getConfigurationData() {
        return this.configurationDataProperty().get();
    }

    public final void setConfigurationData(final ConfigurationData configurationData) {
        this.configurationDataProperty().set(configurationData);
    }

}
