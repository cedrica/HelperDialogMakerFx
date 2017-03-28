package com.customcontrol.helpdialogmaker.helper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.glyphfont.GlyphFontRegistry;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class Helper {


	public static boolean isString(String itemProPage) {
		for (char c : itemProPage.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}

	public static boolean isNotNullAndNotEmpty(String str) {
		return str != null && !str.isEmpty();
	}

	public static void greyOutNodes(List<Node> list, double opacity) {
		for (Node node : list) {
			node.setOpacity(opacity);
		}
	}

	public static <T> void createJavascriptAlias(WebEngine browser, T t) {
		browser.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
				if (newValue == State.SUCCEEDED) {
					JSObject win = (JSObject) browser.executeScript("window");
					win.setMember("app", t);
				}
			}
		});
	}

	public static FXMLLoader createView(String viewPath) {
		FXMLLoader fxmlLoader = new FXMLLoader(Helper.class.getResource(viewPath));
		try {
			fxmlLoader.load();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return fxmlLoader;
	}

	public static Glyph setIcon(Color color, FontAwesome.Glyph iconTyp, double size) {
		Glyph font = GlyphFontRegistry.font("FontAwesome").create(iconTyp);
		font.setColor(color);
		font.setFontSize(size);
		return font;
	}

	public static boolean isAnExcelFile(File file) {
		String[] parts = file.getName().split("\\.");
		if (parts != null && parts.length > 1) {
			String endung = parts[parts.length - 1];
			return (endung.toLowerCase().equals("xls") || endung.toLowerCase().equals("xlsx")) ? true : false;
		}
		return false;
	}


	public static <T> List<T> filter(Predicate<T> predicate, ObservableList<T> items) {
		if (items == null || items.size() <= 0)
			return null;
		List<T> resultList = items.stream().filter(predicate).collect(Collectors.toList());
		return resultList;
	}


	public static ImageView roundedImage(String path, double height, double width) {
		Image image = new Image(Helper.class.getResourceAsStream(path));
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(height);
		imageView.setFitWidth(width);
		imageView.setPickOnBounds(true);
		Circle clip = new Circle(imageView.getFitWidth()/2,imageView.getFitHeight()/2, 15);
		imageView.setClip(clip);
		return imageView;
	}

	public static VBox roundedGlyph(FontAwesome.Glyph icon,double size) {
		VBox vbBox = new VBox();
		vbBox.setMinHeight(size);
		vbBox.setMinWidth(size);
		Label image = new Label();
		image.setGraphic(setIcon(Color.GRAY, icon, size));
		vbBox.getChildren().add(image);
		Circle clip = new Circle(size/2,size/2, size/2);
		vbBox.setClip(clip);
		vbBox.setAlignment(Pos.CENTER);
		return vbBox;
	}
	
}
