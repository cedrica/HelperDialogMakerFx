package com.customcontrol.helpdialogmaker.container;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.customcontrol.helpdialogmaker.container.pagesandpreview.PagesAndPreview;
import com.preag.core.ui.utils.FileUtil;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;

public class ContainerController implements Initializable {

    @FXML
    ContainerView containerView;
    @FXML PagesAndPreview pagesAndPreview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        containerView.setPagesAndPreview(pagesAndPreview);
    }

    public void onSave(ActionEvent evt) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File location = directoryChooser.showDialog(pagesAndPreview.getScene().getWindow());
        if (location == null) {
            return;
        }
        File from = new File(System.getProperty("user.dir") + "/additionalResources/images");
//        File to = FileUtil.getFileOrCreate(location.getAbsolutePath(), "image.png");

        for (File file : from.listFiles()) {
            FileUtil.saveFileAsTemp(file);
        }
    }

    public static void saveToFile(Image image) {
        File outputFile = new File("F:/test/");
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
