package com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.imagetext;

import com.customcontrol.helpdialogmaker.model.OldConfigurationData;
import com.preag.core.ui.service.FXMLService;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.VBox;

public class ImageTextMusterView extends VBox{
	private StringProperty htmlText = new SimpleStringProperty();
	private StringProperty imageText = new SimpleStringProperty();
	private byte[] imageBytes;
	private String imageName;
	private int posInVbMusterContainer;
	private OldConfigurationData oldConfigurationData;
	
	public  ImageTextMusterView(){
	   FXMLService.INSTANCE.loadView(this);
	}
	
	public OldConfigurationData getOldConfigurationData() {
		return oldConfigurationData;
	}

	
	public void setOldConfigurationData(OldConfigurationData oldConfigurationData) {
		this.oldConfigurationData = oldConfigurationData;
	}




	public int getPosInVbMusterContainer() {
		return posInVbMusterContainer;
	}



	
	public void setPosInVbMusterContainer(int posInVbMusterContainer) {
		this.posInVbMusterContainer = posInVbMusterContainer;
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


	public void setImageBytes(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}
	


	
	
	public byte[] getImageBytes() {
		return imageBytes;
	}




	public String getImageName() {
		return imageName;
	}



	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

    
    public final StringProperty imageTextProperty() {
        return this.imageText;
    }
    

    
    public final String getImageText() {
        return this.imageTextProperty().get();
    }
    

    
    public final void setImageText(final String imageText) {
        this.imageTextProperty().set(imageText);
    }
    
	


}
