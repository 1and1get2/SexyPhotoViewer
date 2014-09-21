package com.derek.helloanthy.sexyphotoviewer.model;

import android.graphics.Bitmap;

import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by derek on 19/09/14.
 */
public class WebsiteModel {
    private String uri = null;
    private String websiteContent;
    // a list of all the categories and each contain the name of the category and the links of every page
    private List<Map<String, List<URL>>> category = null;
    private List<Category> categories;


    public String getUrl() {
        return uri;
    }

    public void setUrl(String uri) {
        this.uri = uri;
    }

    public String getWebsiteContent() {
        return websiteContent;
    }

    public void setWebsiteContent(String websiteContent) {
        this.websiteContent = websiteContent;
    }

    public class Category{
        private URL url;
        private WebsiteModel websiteModel;
        private String category;

    }
    public class ImagePage {
        private int id;

        private URL pageLink;
        private List<URL> bitmapLinks;
        private List<Bitmap> bitmaps;

    }
}
