package com.customcontrol.helpdialogmaker.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver.ArrowLocation;

import com.customcontrol.helpdialogmaker.consts.Screens;
import com.customcontrol.helpdialogmaker.event.PageEvent;
import com.customcontrol.helpdialogmaker.event.PopOverEvent;
import com.customcontrol.helpdialogmaker.helper.Helper;
import com.customcontrol.helpdialogmaker.helper.PopOverHelper;
import com.customcontrol.helpdialogmaker.viewmodel.PageViewModel;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PageView implements Initializable {

	@FXML
	Label					lblName;
	@FXML
	Button					btnInfo;
	@FXML
	Label					lblHome;
	@FXML
	JFXTextField				tfName;
	@FXML
	Button					btnOk;
	@FXML
	PageViewModel			pageViewModel;
	private boolean			home;
	private PageInfoView	pageInfoView;
	private Stage			stage;
	private FXMLLoader		mySquelet;
	private List<PageView>	subPages;
	private FXMLLoader		fxmlForPageInfoView;
	private boolean rootNode;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fxmlForPageInfoView = Helper.createView(Screens.PAGE_INFO);
		pageInfoView = fxmlForPageInfoView.getController();
		pageViewModel = new PageViewModel();
		binding();
		btnInfo.setOnAction(this::onInfo);
		btnOk.setOnAction(this::onOk);
		subPages = new ArrayList<>();
	}

	public void binding() {
		pageViewModel.nameProperty().bind(tfName.textProperty());
		tfName.visibleProperty().bind(pageViewModel.tfNameVisibleProperty());
		lblName.visibleProperty().bind(tfName.visibleProperty().not());
		lblName.textProperty().bind(pageViewModel.nameProperty());
		btnOk.visibleProperty().bind(pageViewModel.tfNameVisibleProperty());
		btnInfo.visibleProperty().bind(tfName.visibleProperty().not());
		pageViewModel.tfNameVisibleProperty().bind(pageInfoView.getPageInfoViewModel().editableProperty());
	}

	public void onOk(ActionEvent evt) {
		 pageInfoView.getPageInfoViewModel().setEditable(false);
		 pageViewModel.getPageData().setName(pageViewModel.getName());
		 stage.fireEvent(new PageEvent(PageEvent.ADD_MENU_POINT, pageViewModel.getPageData()));
	}

	public void onInfo(ActionEvent evt) {
		pageInfoView.getPageInfoViewModel().setPageData(pageViewModel.getPageData());
		pageInfoView.setStage(stage);
		PopOverHelper popOverHelper = PopOverHelper.getInstance();
		popOverHelper.setArrowLocation(ArrowLocation.LEFT_TOP);
		popOverHelper.setParent(btnInfo);
		popOverHelper.setContentNode(fxmlForPageInfoView.getRoot());
		popOverHelper.setDetachable(false);
		popOverHelper.show();
		popOverHelper.getPopOver().addEventFilter(PopOverEvent.CLOSE, e -> {
			popOverHelper.hide();
		});
	}

	public void setHomeIconVisible(boolean b) {
		lblHome.setVisible(b);
	}


	public void setHome(boolean home) {
		this.home = home;
	}

	public boolean isHome() {
		return this.home;
	}


	public PageViewModel getPageViewModel() {
		return pageViewModel;
	}


	public void setPageViewModel(PageViewModel pageViewModel) {
		this.pageViewModel = pageViewModel;
	}


	
	public PageInfoView getPageInfoView() {
		return pageInfoView;
	}

	
	public void setPageInfoView(PageInfoView pageInfoView) {
		this.pageInfoView = pageInfoView;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		this.stage.addEventFilter(PageEvent.HANDE_BUTTON_ENABLING, e ->{
			boolean disabled = e.isDisabled();
			btnInfo.setDisable(disabled);
		});
	}


	public List<PageView> getSubPages() {
		return subPages;
	}


	public void setSubPages(List<PageView> subPages) {
		this.subPages = subPages;
	}

	public void setMySquelet(FXMLLoader mySquelet) {
		this.mySquelet = mySquelet;
	}

	public FXMLLoader getMySquelet() {
		return mySquelet;
	}

	
	public void setRootNode(boolean rootNode) {
		this.rootNode = rootNode;
	}

	public boolean isRootNode() {
		return this.rootNode;
	}


}
