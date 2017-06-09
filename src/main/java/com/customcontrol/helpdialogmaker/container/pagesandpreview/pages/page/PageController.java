package com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page;

import java.net.URL;
import java.util.ResourceBundle;

import com.customcontrol.helpdialogmaker.event.PageEvent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PageController implements Initializable {

    @FXML
    Label lblName;

    @FXML
    Label lblHome;

    @FXML
    TextField tfName;

    @FXML
    Button btnOk;

    @FXML
    PageView pageView;

    @FXML
    Button btnPopupMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        binding();
        btnPopupMenu.setOnAction(this::onBtnPopupMenu);
        btnOk.setOnAction(this::onOk);
        pageView.homeProperty().addListener((obs, oldVal, newVal) -> {
            setHomeIconVisible(newVal);
        });
        pageView.setPopOverNode(btnPopupMenu);
    }

    public void binding() {
        pageView.nameProperty().bind(tfName.textProperty());
        tfName.visibleProperty().bind(pageView.nameVisibleProperty());
        lblName.visibleProperty().bind(tfName.visibleProperty().not());
        lblName.textProperty().bind(pageView.nameProperty());
        btnOk.visibleProperty().bind(pageView.nameVisibleProperty());
        btnPopupMenu.visibleProperty().bind(tfName.visibleProperty().not());
        btnPopupMenu.disableProperty().bind(pageView.disablePopUpMenuBtnProperty());
    }

    public void onOk(ActionEvent evt) {
        evt.consume();
        pageView.setNameVisible(false);
        btnOk.fireEvent(new PageEvent(PageEvent.ADD_MENU_POINT));
    }

    public void onBtnPopupMenu(ActionEvent evt) {
        evt.consume();
        btnPopupMenu.fireEvent(new PageEvent(PageEvent.SHOW_POPOVERMENU, pageView.getIndex(),pageView.getName(), pageView.getConfiguration()));
    }

    public void setHomeIconVisible(boolean b) {
        lblHome.setVisible(b);
    }
}
