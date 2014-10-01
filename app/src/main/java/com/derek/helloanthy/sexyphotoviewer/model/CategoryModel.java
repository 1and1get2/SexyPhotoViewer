package com.derek.helloanthy.sexyphotoviewer.model;

import java.util.List;

/**
 * Created by derek on 21/09/14.
 */
public class CategoryModel {
    //private URL url;
    private String uri;
    //private WebsiteModel websiteModel;
    private String categoryName;
    private int currentPageNumber;
//    private Map<Integer, List<ImagePageModel>> ImagePageMap; // imagepage links on every page
    private List<ImagePageModel> imagePageModelList;

    public List<ImagePageModel> getImagePageModelList() {
        return imagePageModelList;
    }

    public void setImagePageModelList(List<ImagePageModel> imagePageModelList) {
        this.imagePageModelList = imagePageModelList;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }
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
    public String getPageUri(int page){
        int position = uri.lastIndexOf('.');
        return uri.substring(0,position) + "_" + page + uri.substring(position);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}