package com.customcontrol.helpdialogmaker.viewmodel;

import com.customcontrol.helpdialogmaker.helper.DialogHelper;
import com.customcontrol.helpdialogmaker.helper.Helper;
import com.customcontrol.helpdialogmaker.model.OldConfigurationData;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

public class TextImageMusterViewModel {
	private StringProperty htmlText = new SimpleStringProperty();
	private byte[] imageBytes;
	private String imageName;
	private int posInVbMusterContainer;
	private String wholeHtmlContent;
	private OldConfigurationData oldConfigurationData;
	
	
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



	public String save(Window window) {
		if(imageBytes == null || imageBytes.length == 0){
			DialogHelper.error("Error", "Bild ist zwingen", window, ButtonType.OK);
			return null;
		}
		
		Helper.saveImageLocaly(imageBytes, imageName);
		String path = System.getProperty("user.dir") + "/images/"+ imageName;
		path = path.replace("\\", "/");
		String essential = htmlText.get().replace("<html dir=\"ltr\"><head></head><body contenteditable=\"true\">", "").replace("</body></html>", "");
		wholeHtmlContent =  "<div class=\"media\">"
								+ "<div style=\"float:left;margin-right:5px;\">"
									+ essential		
								+ "</div>"
								+ "<div class=\"media-left media-middle\">"
									+ "<img src='file:///" +path+"' class=\"media-object\" style=\"width:60px\">"
								+ "</div>"
							+ "</div>";
		String view =   "<html dir=\"ltr\"><head><meta charset=\"utf-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
						+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"
						+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js\"></script>"
						+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>"
						+ "</head>"
						+ "<body contenteditable=\"false\">"
						+ wholeHtmlContent
						+ "</body>"
						+ "</html>";
		return view;
	}




	
	public String getWholeHtmlContent() {
		return wholeHtmlContent;
	}




	
	public void setWholeHtmlContent(String wholeHtmlContent) {
		this.wholeHtmlContent = wholeHtmlContent;
	}



}
