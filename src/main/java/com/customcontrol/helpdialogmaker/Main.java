package com.customcontrol.helpdialogmaker;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.deltaspike.core.api.provider.BeanProvider;

import com.customcontrol.helpdialogmaker.container.ContainerManager;
import com.customcontrol.helpdialogmaker.container.ContainerView;
import com.google.common.io.Files;
import com.preag.core.exception.sevice.DefaultUncaughtExcpetionHandler;
import com.preag.core.ui.app.DesktopApp;
import com.preag.core.ui.utils.FileUtil;

import javafx.scene.image.Image;

public class Main extends DesktopApp {


    public static void main(String[] args) {
        enablePreloader();
        launch(args);

    	
//        File file = new File ("C:/Users/the-file-name.txt");
//        BufferedWriter out;
//		try {
//			out = new BufferedWriter(new FileWriter(file));
//	        out.write("mamam");
//	        out.close();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} 
//
//        
//        String prefix = "/urgent/Eric";
//        String suffix = ".txt";
//        File tempFile2;
//		try {
//			tempFile2 = File.createTempFile(prefix, suffix);
//			 tempFile2.deleteOnExit();
//			 System.out.println("----------> "+tempFile2.getAbsolutePath());
////			 Desktop.getDesktop().open(new File("C:\\Users/CA5B33~1.LEU/AppData/Local/Temp"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//       
		
    }

    @Override
    public void initialize() {
        Thread.setDefaultUncaughtExceptionHandler(new DefaultUncaughtExcpetionHandler());
        ContainerView containerView = BeanProvider.getContextualReference(ContainerView.class, false);
        ContainerManager containerManager = BeanProvider.getContextualReference(ContainerManager.class, false);
        containerManager.handleAddedEvents(containerView);
        getPrimaryStage().getIcons().add(new Image(getClass().getResource("/images/stage_icon.png").toString()));
        setStageIcon("/images/stage_icon.png");
        getPrimaryStage().setMaximized(true);
        closePreloader();
        showPrimaryStage(containerView);
       
    }

}
