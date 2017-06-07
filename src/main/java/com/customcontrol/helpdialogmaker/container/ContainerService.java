package com.customcontrol.helpdialogmaker.container;

import com.customcontrol.helpdialogmaker.consts.HtmlPart;
import com.customcontrol.helpdialogmaker.container.pagesandpreview.pages.page.PageView;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class ContainerService {
    public static String builtHtmlPage(TreeItem<String> treeItem ) {
        String htmlPage = HtmlPart.MENU
                + "<div style=\"background-color: #444; width:100%;height:30px;padding-left: 5px; padding-right: 5px; padding-top: 5px;position:fixed;top:0\">"
                + "<div class=\"col-lg-12\">" + "<div id=\"message\" style=\"color:white\"></div>"
                + "<i class=\"fa fa-home\" style=\"color: #fff; font-size: 25px;\" ></i>"
                + "<button id=\"menu-buton\" type=\"button\" class=\"pull-right\" style=\"background-color: transparent; border: none;\">"
                + "<i class=\"fa fa-bars\" style=\"color: #fff; font-size: 20px;\"></i></button></div></div>"
                + "<div id=\"menu\" style=\"position:fixed;top:0;width:100%;overflow:hidden;margin-top: 30px; "
                + "background-color: #000; opacity: 0.8; filter: alpha(opacity = 50);z-index:200;\"><ul>";
        htmlPage += repeatProcess(treeItem.getChildren(), 0);
        htmlPage += "</ul></div>";
        htmlPage = builtHtmlPageRec(treeItem.getChildren(), htmlPage);
        return htmlPage += "</body>" + "</html>";
    }
    private static String repeatProcess(ObservableList<TreeItem<String>> observableList, int wasOpenForChildren) {
        String apres = "";
        for (TreeItem<String> item : observableList) {
            PageView pageView = (PageView)item.getGraphic();
            apres += "<li><h4><a href=\"#section"
                    + pageView.getIndex() + "\">" + pageView.getIndex() + " " + pageView.getName() + "</a></h4>";
            if (item.getChildren()!= null && item.getChildren().size() > 0) {
                apres += "<ul>";
                apres += repeatProcess(item.getChildren(), ++wasOpenForChildren);
            } else if (wasOpenForChildren > 0) {
                for (int i = 0; i < wasOpenForChildren; i++) {
                    apres += "</li></ul>";
                }
                apres += "</li>";
            } else {
                apres += "</li>";
            }
        }

        return apres;
    }
    public static String builtHtmlPageRec(ObservableList<TreeItem<String>> treeItems, String htmlPage) {
        for (TreeItem<String> item : treeItems) {
            PageView pageView = (PageView) item.getGraphic();
            String pageIndex = pageView.getIndex() + "";
            if (!pageView.isRootNode()) {
                pageIndex = makePageIndex(pageIndex);
            }
            String title = pageView.getName() + "";
//            if (configurationData == null) {
//                htmlPage += "<h2><b>" + pageIndex + "</b>   " + title + "</h2><br/>";
//                continue;
//            }

            htmlPage += "<h2><b>" + pageIndex + "</b>   " + title + "</h2><br/>"
                    +pageView.getHtml();
            if (item.getChildren().size() > 0) {
                htmlPage = builtHtmlPageRec(item.getChildren(), htmlPage);
            }
        }
        return htmlPage;
    }
    

    private static String makePageIndex(String pageIndex) {
        String res = "";
        for (int i = 0; i < pageIndex.length(); i++) {
            res += pageIndex.charAt(i) + ".";
        }
        return res;
    }
    
    public static int calculIndex(TreeItem<String> page) {
        int res = 0;
        PageView pageView = (PageView)page.getGraphic();
        if (page.getChildren() != null &&  !page.getChildren().isEmpty()) {
            res = pageView.getIndex() * 10 + page.getChildren().size() + 1;
        } else {
            res = pageView.getIndex() * 10 + 1;
        }
        return res;
    }
}
