package com.customcontrol.helpdialogmaker.container.pagesandpreview.configurator.muster.imagetext;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;

import com.customcontrol.helpdialogmaker.consts.HtmlPart;
import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.customcontrol.helpdialogmaker.enums.Muster;
import com.customcontrol.helpdialogmaker.event.PopOverEvent;
import com.customcontrol.helpdialogmaker.helper.Helper;
import com.preag.core.ui.utils.dialog.Dialogs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

public class ImageTextMusterController implements Initializable {

	@FXML
	Button		btnChooseImage;
	@FXML
	ImageView	ivLoadedImage;
	@FXML
	Button		btnChangeImage;
	@FXML
	VBox		vbLoadedImage;
	@FXML
	Button		btnSave;
	@FXML
	Button		btnEdit;
	@FXML
	HTMLEditor	htmlEditor;
	@FXML
	WebView		webView;
	@FXML
	HBox								hbViewer;
	@FXML
	HBox								hbEditor;
	@FXML Button btnRemoveRow;
	private WebEngine					webEngine;
    @FXML ImageTextMusterView imageTextMusterView;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnChooseImage.setOnAction(this::onFileChooserAction);
		btnSave.setOnAction(this::onSave);
		btnEdit.setOnAction(this::onEdit);
		btnChangeImage.setOnAction(this::onChangeImage);
		btnRemoveRow.setOnAction(this::onRemoveRow);
		webEngine = webView.getEngine();
		btnSave.setDisable(true);
		btnEdit.setVisible(false);
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
				imageTextMusterView.setImageBytes(FileUtils.readFileToByteArray(file));
				imageTextMusterView.setImageName(file.getName());
				InputStream is = FileUtils.openInputStream(file);
				Image image = new Image(is);
				ivLoadedImage.setImage(image);
				vbLoadedImage.setVisible(true);
				btnChooseImage.setVisible(false);
				btnSave.setDisable(false);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			btnSave.setDisable(true);
		}
	}

	public void setImageInImageView(byte[] bytes) {
		imageTextMusterView.setImageBytes(bytes);
		InputStream is =  new ByteArrayInputStream(bytes);
		Image image = new Image(is);
		ivLoadedImage.setImage(image);
		vbLoadedImage.setVisible(true);
		btnChooseImage.setVisible(false);
		btnSave.setDisable(false);
	}
	

	public void onRemoveRow(ActionEvent evt){
	    imageTextMusterView.fireEvent(new PopOverEvent(PopOverEvent.REMOVE_CONFIGURATION,imageTextMusterView.getPosInVbMusterContainer(),false));
	}
	
	public void onSave(ActionEvent evt) {
		imageTextMusterView.setHtmlText(htmlEditor.getHtmlText());
		String totalContent = saveAndRetrieveHtml();
		ConfigurationData configurationData = new ConfigurationData();
		configurationData.setHtmlText(imageTextMusterView.getHtmlText());
		configurationData.setImage(imageTextMusterView.getImageBytes());
		configurationData.setMuster(Muster.IMAGE_TEXT);
		imageTextMusterView.setOldConfigurationData(configurationData);
		if (totalContent != null){
			hbEditor.setVisible(false);
			hbViewer.setVisible(true);
			webEngine.loadContent(totalContent);
			btnEdit.setVisible(true);
			btnSave.setVisible(false);
		}
			
	}
	

    public String saveAndRetrieveHtml() {
        if(imageTextMusterView.getImageBytes() == null || imageTextMusterView.getImageBytes().length == 0){
            Dialogs.error("Bild ist zwingen", btnSave.getScene().getWindow());
            return null;
        }
        
        Helper.saveImageLocaly(imageTextMusterView.getImageBytes(), imageTextMusterView.getImageName());
        String path = System.getProperty("user.dir") + "/images/"+  imageTextMusterView.getImageName();
        path = path.replace("\\", "/");
        String text = imageTextMusterView.getHtmlText().replace("<html dir=\"ltr\"><head></head><body contenteditable=\"true\">", "").replace("</body></html>", "");
        String imageText =  "<div class=\"media\">"
                                + "<div class=\"media-left media-middle\">"
                                    + "<img src='file:///" +path+"' class=\"media-object\">"
                                + "</div>"
                                + "<div class=\"media-body\">"
                                    + text     
                                + "</div>"
                            + "</div>";
        String view =   HtmlPart.HEAD
                        + imageText
                        + "</body>"
                        + "</html>";
        return view;
    }


	public void onEdit(ActionEvent evt) {
		hbEditor.setVisible(true);
		hbViewer.setVisible(false);
		btnEdit.setVisible(false);
		btnSave.setVisible(true);
	}

	
}
