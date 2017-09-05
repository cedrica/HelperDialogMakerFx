package com.customcontrol.helpdialogmaker.helper;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.codec.binary.Base64;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.glyphfont.GlyphFontRegistry;

import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page.PageView;
import com.customcontrol.helpdialogmaker.data.ConfigurationData;
import com.customcontrol.helpdialogmaker.data.Data;
import com.customcontrol.helpdialogmaker.data.Page;
import com.customcontrol.helpdialogmaker.enums.Muster;
import com.google.common.io.Files;
import com.google.common.primitives.Bytes;
import com.preag.core.ui.utils.FileUtil;
import com.preag.core.ui.utils.dialog.Dialogs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class Helper {

	public static void saveHelperAndImagesInTempdir(String content, String toSavedFilename) {
		File createTempDir = saveFileInTempdirAndOpen(content, toSavedFilename);
		File dir = new File(System.getProperty("user.dir") + "/images");
		for (File f : dir.listFiles()) {
			String name = f.getName();
			File fileOrCreate = FileUtil.getFileOrCreate(createTempDir.getAbsolutePath(), name);
			byte[] readBytesFromFile = FileUtil.readBytesFromFile(f);
			FileUtil.writeBytesToFile(fileOrCreate, readBytesFromFile);
		}
	}

	public static String convertByteArrayToStr(byte[] arr) {
		if (arr == null)
			return "";
		List<Byte> list = Bytes.asList(arr);
		return list.toString().replace("[", "").replace("]", "");
	}

	public static File saveFileInTempdirAndOpen(String content, String toSavedFilename) {
		File createTempDir = Files.createTempDir();
		try {
			FileUtil.writeStringToFile(content, createTempDir.getAbsolutePath() + File.separatorChar + toSavedFilename);
			Desktop.getDesktop().open(createTempDir);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		return createTempDir;
	}

	public static void saveImageLocaly(byte[] image, String imageName) {
		try {
			File file = new File(System.getProperty("user.dir") + "/images");
			if (!file.exists()) {
				file.mkdirs();
			}
			String path = System.getProperty("user.dir") + "/images/" + imageName;
			path.replace("\\", "/");
			System.out.println("path Save: " + path);
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(image);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
		} catch (IOException e) {
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
		Circle clip = new Circle(imageView.getFitWidth() / 2, imageView.getFitHeight() / 2, 15);
		imageView.setClip(clip);
		return imageView;
	}

	public static VBox roundedGlyph(FontAwesome.Glyph icon, double size) {
		VBox vbBox = new VBox();
		vbBox.setMinHeight(size);
		vbBox.setMinWidth(size);
		Label image = new Label();
		image.setGraphic(setIcon(Color.GRAY, icon, size));
		vbBox.getChildren().add(image);
		Circle clip = new Circle(size / 2, size / 2, size / 2);
		vbBox.setClip(clip);
		vbBox.setAlignment(Pos.CENTER);
		return vbBox;
	}

	public static void roundVBox(VBox vbBox, double size) {
		vbBox.setMinSize(size, size);
		Circle clip = new Circle(size / 2, size / 2, size / 2);
		vbBox.setClip(clip);
		vbBox.setAlignment(Pos.CENTER);
	}

	public static List<PageView> readHelperDialogTemplate(File template) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Data data = (Data) jaxbUnmarshaller.unmarshal(template);
			List<Page> pages = data.getPages();
			List<PageView> pageViews = mapToPageViews(pages);
			return pageViews;
		} catch (JAXBException e) {
			Dialog<ButtonType> error = Dialogs.error("Das Template kann nicht importiert werden. Die Datei wurde geschädigt", null);
			error.showAndWait();
		}
		return null;
	}

	private static List<PageView> mapToPageViews(List<Page> pages) {
		List<PageView> pageViews = pages.stream().map(Helper::mapToPageView).collect(Collectors.toList());
		return pageViews;
	}

	public static String createXmlTemplate(PageView pageView,TreeItem<String> root, String xml) {
		xml += "<page>" + "<name>" + pageView.getName() + "</name>" + "<index>" + pageView.getIndex() + "</index>"
				+ "<html>" + new String(Base64.encodeBase64(pageView.getHtml().getBytes()))
				+ "</html>" + "<rootNode>" + pageView.isRootNode() + "</rootNode>" + "<nameVisible>"
				+ pageView.isNameVisible() + "</nameVisible>" + "<home>" + pageView.isHome() + "</home>"
				+ "<disablePopUpMenuBtn>" + pageView.isDisablePopUpMenuBtn() + "</disablePopUpMenuBtn>";

		if(root.getChildren() != null && !root.getChildren().isEmpty()){
			ObservableList<TreeItem<String>> subPages = root.getChildren();
			for (TreeItem<String> treeItem : subPages) {
				PageView subPage = (PageView) treeItem.getGraphic();
				xml = createXmlTemplate(subPage, treeItem,xml);
			}
			
		}
		if (pageView.getConfiguration() == null) {
			return xml + "</page>";
		}else{
			xml += "<configurationData>";
			for (ConfigurationData configurationData : pageView.getConfiguration()) {
				Muster muster = configurationData.getMuster();
				if (muster == Muster.IMAGE) {
					xml += "<image>" + Helper.convertByteArrayToStr(configurationData.getImage()) + "</image>"
							+ "<htmlText>" + new String(Base64.encodeBase64((configurationData.getHtmlText()!=null)? configurationData.getHtmlText().getBytes():"".getBytes())) + "</htmlText>" + "<muster>"
							+ configurationData.getMuster() + "</muster>";
				} else if (muster == Muster.TEXT) {
					xml += "<htmlText>" +  new String(Base64.encodeBase64((configurationData.getHtmlText()!=null)? configurationData.getHtmlText().getBytes():"".getBytes())) + "</htmlText>" + "<muster>"
							+ configurationData.getMuster() + "</muster>";
				} else if (muster == Muster.TEXT_IMAGE || muster == Muster.IMAGE_TEXT) {
					xml += "<image>" + Helper.convertByteArrayToStr(configurationData.getImage()) + "</image>"
							+ "<htmlText>"
							+ new String(Base64.encodeBase64((configurationData.getHtmlText()!=null)? configurationData.getHtmlText().getBytes():"".getBytes()))
							+ "</htmlText>" + "<muster>" + configurationData.getMuster() + "</muster>";
				}
			}
		}
		xml += "</configurationData></page>";
		return xml;
	}
	
	public static PageView mapToPageView(Page page) {
		PageView pageView = new PageView();
		pageView.setName(page.getName());
		pageView.setDisablePopUpMenuBtn(page.isDisablePopUpMenuBtn());
		pageView.setRootNode(page.isRootNode());
		pageView.setIndex(page.getIndex());
		pageView.setNameVisible(page.isNameVisible());
		pageView.setHome(page.isHome());
		pageView.setHtml(decode(page.getHtml()));
		pageView.setConfiguration(FXCollections.observableList(new ArrayList<ConfigurationData>()));
		for (ConfigurationData configurationData : page.getConfiguration()) {
			configurationData.setHtmlText(decode(configurationData.getHtmlText()));
			pageView.getConfiguration().add(configurationData);
		}
		if (page.getSubPages() != null && page.getSubPages().size() > 0)
			pageView.setSubPages(FXCollections.observableList(mapToPageViews(page.getSubPages())));
		return pageView;
	}
	public static String decode(String s) {
	    return new String(Base64.decodeBase64(s.getBytes()));
	}
}
