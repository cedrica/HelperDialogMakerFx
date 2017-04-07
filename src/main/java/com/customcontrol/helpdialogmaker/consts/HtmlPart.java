package com.customcontrol.helpdialogmaker.consts;


public class HtmlPart {

	public static final String HEAD = "<html dir=\"ltr\"><head><meta charset=\"utf-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
					+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"
					+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js\"></script>"
					+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>" + "</head>"
					+ "<body contenteditable=\"false\">";
	
	public static final String MENU = "<html dir=\"ltr\"><head><meta charset=\"utf-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
					+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"
					+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js\"></script>"
					+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script><script>"
					+ "$(document).ready(function(){$(\"#menu\").hide();$(\"#menu-buton\").click(function(){$(\"#menu\").slideToggle();});"
					+ "$(\"a\").click(function(){"
					+ "$(\"#menu\").slideToggle();});});</script>" 
					+ "</head>";
}
