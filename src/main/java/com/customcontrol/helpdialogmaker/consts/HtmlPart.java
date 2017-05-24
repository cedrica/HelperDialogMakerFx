package com.customcontrol.helpdialogmaker.consts;


public class HtmlPart {

	public static final String HEAD = "<html dir=\"ltr\"><head><meta charset=\"utf-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
					+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"
					+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js\"></script>"
					+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>" + "</head>"
					+ "<body contenteditable=\"false\">";
	
	public static final String MENU = "<!DOCTYPE html><html dir=\"ltr\"><head><meta charset=\"utf-8\">"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
					+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js\"></script>"
					+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>"
					+ "<link href=\"https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css\" rel=\"stylesheet\" />"
					+ "<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" rel=\"stylesheet\" />"
					+ "<script> "
							+ "$(document).ready(function(){"
								+ "$(\"#menu\").hide();"
								+ "$(\"#menu-buton\").click(function(){"
									+ "$(\"#menu\").slideToggle();"
								+ "});"
								+ "$(\"a\").click(function(){"
										+ "$(\"#menu\").slideToggle();"
								+ "});"
							+ "});"
						+ "</script>"
						+ "<style>"
						+ "ul {list-style-type: none;"
						+ "}a{color:#fff;}li{margin: 0 0 13px 0;}p{text-align:justify}</style>"
						+ "</head>"
						+ "<body contenteditable=\"false\">";
}
