package com.customcontrol.helpdialogmaker.data;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="page")
public class Page {
	 private String name;

	    private int index;

	    private String html;

	    private boolean nameVisible;

	    private boolean rootNode;

	    private boolean home;

	    private boolean disablePopUpMenuBtn;

	    private List<ConfigurationData> configuration;
	    private List<Page> subPages;
		public String getName() {
			return name;
		}
		@XmlElement
		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}
		@XmlElement
		public void setIndex(int index) {
			this.index = index;
		}

		public String getHtml() {
			return html;
		}
		@XmlElement
		public void setHtml(String html) {
			this.html = html;
		}

		public boolean isNameVisible() {
			return nameVisible;
		}
		@XmlElement
		public void setNameVisible(boolean nameVisible) {
			this.nameVisible = nameVisible;
		}

		public boolean isRootNode() {
			return rootNode;
		}
		@XmlElement
		public void setRootNode(boolean rootNode) {
			this.rootNode = rootNode;
		}

		public boolean isHome() {
			return home;
		}
		@XmlElement
		public void setHome(boolean home) {
			this.home = home;
		}

		public boolean isDisablePopUpMenuBtn() {
			return disablePopUpMenuBtn;
		}
		@XmlElement
		public void setDisablePopUpMenuBtn(boolean disablePopUpMenuBtn) {
			this.disablePopUpMenuBtn = disablePopUpMenuBtn;
		}

		public List<ConfigurationData> getConfiguration() {
			return configuration;
		}

		@XmlElement(name="configurationData", type=ConfigurationData.class)
		public void setConfiguration(List<ConfigurationData> configuration) {
			this.configuration = configuration;
		}
		
		public List<Page> getSubPages() {
			return subPages;
		}
		
		@XmlElement(name="page", type=Page.class)
		public void setSubPages(List<Page> subPages) {
			this.subPages = subPages;
		}

}
