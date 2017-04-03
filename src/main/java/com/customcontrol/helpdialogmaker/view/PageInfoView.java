package com.customcontrol.helpdialogmaker.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.consts.Screens;
import com.customcontrol.helpdialogmaker.event.PageEvent;
import com.customcontrol.helpdialogmaker.event.PopOverEvent;
import com.customcontrol.helpdialogmaker.helper.DialogHelper;
import com.customcontrol.helpdialogmaker.helper.Helper;
import com.customcontrol.helpdialogmaker.session.Session;
import com.customcontrol.helpdialogmaker.viewmodel.PageInfoViewModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PageInfoView implements Initializable {

	@FXML
	HBox						hbRename;
	@FXML
	HBox						hbUnderPage;
	@FXML
	HBox						hbRemove;
	@FXML HBox hbConfig;
	private PageInfoViewModel	pageInfoViewModel;
	private Stage				stage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pageInfoViewModel = new PageInfoViewModel();
		hbRemove.setOnMouseClicked(evt -> {
			if (evt.getButton() == MouseButton.PRIMARY) {
				List<PageView> pages = Session.createInstance().getPages();
				if (pageInfoViewModel.isChild()) {
					recursivSearchAndRemove(pageInfoViewModel.getPageData().getIndex(), pages);
				} else {
					for (PageView pageView : pages) {
						if (pageInfoViewModel.getPageData().getIndex() == pageView.getPageViewModel().getPageData().getIndex()) {
							break;
						}
					}
				}
				stage.fireEvent(new PageEvent(PageEvent.REMOVE, pageInfoViewModel.getPageData()));
				hbRemove.fireEvent(new PopOverEvent(PopOverEvent.CLOSE));
			}
		});

		hbUnderPage.setOnMouseClicked(evt -> {
			if (evt.getButton() == MouseButton.PRIMARY) {
				hbUnderPage.fireEvent(new PopOverEvent(PopOverEvent.CLOSE));
				stage.fireEvent(new PageEvent(PageEvent.ADD_SUB_PAGE, pageInfoViewModel.getPageData()));
			}
		});

		hbRename.setOnMouseClicked(evt -> {
			if (evt.getButton() == MouseButton.PRIMARY) {
				pageInfoViewModel.setEditable(true);
			}
		});
		
		hbConfig.setOnMouseClicked(evt -> {
			if (evt.getButton() == MouseButton.PRIMARY) {
				hbConfig.fireEvent(new PopOverEvent(PopOverEvent.CLOSE));
				FXMLLoader parent = Helper.createView(Screens.CONFIGURATOR);
				Stage stage = DialogHelper.dialogStage(parent.getRoot(), Modality.WINDOW_MODAL, "Einstellung",null);
				ConfiguratorView configuratorView = parent.getController();
				configuratorView.setStage(stage);
			}
		});
	}


	private void recursivSearchAndRemove(int childIndex, List<PageView> pages) {
		boolean found = false;
		int i = 0;
		for (PageView pageView : pages) {
			if (childIndex == pageView.getPageViewModel().getPageData().getIndex()) {
				found = true;
				break;
			} else {
				if (!pageView.getSubPages().isEmpty())//has children
					recursivSearchAndRemove(childIndex, pageView.getSubPages());
			}
		}
		if (found) {
			for (PageView pageView : pages) {
				i++;
				if(pageView.getPageViewModel().getPageData().getIndex() == childIndex){
					break;
				}
			}
			pages.remove(i);
			return;
		}
	}




	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public PageInfoViewModel getPageInfoViewModel() {
		return this.pageInfoViewModel;
	}

}
