package com.derek.helloanthy.sexyphotoviewer.model;

import java.util.List;
import java.util.Map;

/**
 * Created by derek on 21/09/14.
 */
public class CategoryModel {
    //private URL url;
    private String uri;
    //private WebsiteModel websiteModel;
    private String category;
    private Map<Integer, List<ImagePageModel>> ImagePageMap; // imagepage links on every page

//    public URL getUrl() {
//        return url;
//    }
//
//    public void setUrl(URL url) {
//        this.url = url;
//    }


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getPage(int page){
        int position = uri.lastIndexOf('.');
        return uri.substring(0,position) + "_" + page + uri.substring(position);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<Integer, List<ImagePageModel>> getImagePageMap() {
        return ImagePageMap;
    }

    public void setImagePageMap(Map<Integer, List<ImagePageModel>> imagePageMap) {
        ImagePageMap = imagePageMap;
    }
}