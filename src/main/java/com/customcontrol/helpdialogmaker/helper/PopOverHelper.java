package com.customcontrol.helpdialogmaker.helper;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Window;

/**
 * A Helper which manage a {@link PopOver} Component.</br>
 * How to Use: </br>
 * - Get an Instance with {@link #PopOverHelper}.{@link #getInstance()}. </br>
 * - Set the contentNode and (optional) parent and title with, for example
 * {@link #setContentNode(Node)}</br>
 * - Call {@link #show()} or {@link #show(Parent)} to display the PopOver with
 * his content</br>
 * - You can get the {@link FXMLLoader} from the <b><code>contentNode</code></b>
 * with {@link #getFxmlLoader()}</br>
 * - You can close the PopOver with the booleanProperty's
 * {@link #cancelProperty()} or {@link #saveProperty()}
 * 
 * @author p.kanbach
 */
public class PopOverHelper {

	private static PopOverHelper instance;

	public static PopOverHelper getInstance() {
		if (PopOverHelper.instance == null) {
			PopOverHelper.instance = new PopOverHelper();
		}
		return instance;
	}

	private Node	contentNode;
	private Object	parent;
	private PopOver	popOver;
	private String	title = "";

//	private OwnPopOverSkin ownSkin;

	/**
	 * Private Constructor for Singleton
	 */
	private PopOverHelper() {
		createPopOver();
	}

	/**
	 * Reset all to the default configuration
	 */
	public void clearAll() {
		parent = null;
		popOver = null;
		contentNode = null;
		title = "";
	}

	public Node getContentNode() {
		return contentNode;
	}

	public PopOver getPopOver() {
		return popOver;
	}

	public String getTitle() {
		return title;
	}

	/**
	 * Hides the PopOver Window
	 */
	public void hide() {
		popOver.hide();
	}

	/**
	 * Changes the {@link ArrowLocation} where the PopOver should be displayed.
	 * 
	 * @param show
	 */
	public void setArrowLocation(ArrowLocation arrowLocation) {
		popOver.setArrowLocation(arrowLocation);
	}

	/**
	 * Sets the ContentNode for the PopUp. Without a parent you need to use the
	 * function {@link PopOverHelper#show(Parent)}, or define a parent with
	 * {@link PopOverHelper#setParent(Parent)}}. The PopUp will use the default
	 * title. You can define a title with {@link #setTitle(String)}
	 * 
	 * @param contentNode
	 */
	public void setContentNode(Node contentNode) {
		this.contentNode = contentNode;
		updateContentNode();
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void setParent(Window parent) {
		this.parent = parent;
	}

	/**
	 * Sets the title the PopOver will use
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		if (popOver != null) {
			popOver.setTitle(title);
		}
		this.title = title;
	}

	/**
	 * Sets the ContentNode and the title of the PopUp. Without a parent you
	 * need to use the function {@link PopOverHelper#show(Parent)}, or define a
	 * parent with {@link PopOverHelper#setParent(Parent)}}
	 * 
	 * @param title
	 * @param contentNode
	 */
	public void setTitleAndContentNode(String title, Node contentNode) {
		this.contentNode = contentNode;
		setTitle(title);
		updateContentNode();
	}

	/**
	 * Shows the PopOver at the Parent Node which was set with
	 * {@link #setParent(Parent)} before.
	 */
	public void show() {
		showOnPopOver(parent);
	}

	/**
	 * Shows the PopOver at the parentNode
	 * 
	 * @param parentNode
	 */
	public void show(Node parentNode) {
		showOnPopOver(parentNode);
	}

	/**
	 * Shows the PopOver at the parentNode
	 * 
	 * @param parentNode
	 */
	public void show(Window parentNode) {
		showOnPopOver(parentNode);
	}

	public void showCloseIcon(boolean show) {
//		ownSkin = new OwnPopOverSkin();
//		ownSkin.showCloseIcon(show);
//		popOver.setSkin(ownSkin.createSkin(popOver));
	}

	/**
	 * Creates a new PopOver with default configurations
	 */
	private void createPopOver() {
		popOver = new PopOver();
		
	}

	public void setDetachable(boolean detachable){
		popOver.setDetachable(detachable);
	}
	private void showOnPopOver(Object parentNode) {
		if (parentNode != null) {
			if (popOver.getContentNode() != null) {
				if (parentNode instanceof Window) {
					popOver.show((Window) parentNode);
				} else if (parentNode instanceof Node) {
					popOver.show((Node) parentNode);
				} else {
					System.err.println("Parent is not a Node or Window");
				}
			} else {
				if (contentNode != null) {
					updateContentNode();
					showOnPopOver(parentNode);
				} else {
					System.err.println("ContentNode is Null");
				}
			}
		} else {
			System.err.println("Parent is Null");
		}
	}

	/**
	 * Updates the content Node of the #{@link #popOver}. If no PopOver exists,
	 * it creates a default PopOver from {@link #createPopOver()}
	 */
	private void updateContentNode() {
		if (contentNode != null) {
			if (popOver == null) {
				createPopOver();
			}
			popOver.setContentNode(contentNode);
		}
	}
}
