package com.derek.helloanthy.sexyphotoviewer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by derek on 19/09/14.
 */
public class WebsiteModel {
    private static String TAG = "WebsiteModel";
    private String uri = null;
    private String charset = "UTF-8";
    private String title;
    private String websiteContent;
    // a list of all the categories and each contain the name of the category and the links of every page
    //private Map<String, List<URL>> categories = null;
    //private List<CategoryModel> categories;
    private Map<String, CategoryModel> categoryMap = null;
    private List<CategoryModel> categoryList = null;
/*
    public List<CategoryModel> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryModel> categoryList) {
        this.categoryList = categoryList;
    }
    public CategoryModel getCategory(int index){
        CategoryModel cm = null;
        try {
            cm = categoryList.get(index);
        } catch (NullPointerException e){
            throw e;
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
        return  cm;
    }
    public void setCategoryModel(int index, CategoryModel cm){
        categoryList.set(index, cm);
    }
    public void addCategoryModel(CategoryModel cm){
        categoryList.add(cm);
    }
    public void addCategoryModel(int index, CategoryModel cm){
        categoryList.add(index, cm);
    }*/

    ///////////// NOT SURE Should use list or LinkedHashMap
    public Map<String, CategoryModel> getCategoryMap() {
        return categoryMap;
    }

    public void setCategoryMap(Map<String, CategoryModel> categoryMap) {
        this.categoryMap = categoryMap;
    }

    public List<String> getCategories() {
        return new ArrayList<String>(categoryMap.keySet());
    }



    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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



}

