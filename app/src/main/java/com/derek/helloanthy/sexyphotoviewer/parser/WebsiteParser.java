package com.derek.helloanthy.sexyphotoviewer.parser;

import android.util.Log;

import com.derek.helloanthy.sexyphotoviewer.R;
import com.derek.helloanthy.sexyphotoviewer.model.CategoryModel;
import com.derek.helloanthy.sexyphotoviewer.model.WebsiteModel;
import com.derek.helloanthy.sexyphotoviewer.view.HomeActivity;
import com.derek.tools.HttpManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by derek on 20/09/14.
 */
public class WebsiteParser {
    private static String TAG = "WebsiteParser";
    public static WebsiteModel parser(String uri){
        String[] photoTableKeyword = HomeActivity.context.getResources().getStringArray(R.array.type);
        String websiteContent = HttpManager.getData(uri);
        Document webDoc = Jsoup.parse(websiteContent);
        WebsiteModel wm = new WebsiteModel();

        wm.setTitle(webDoc.title());
        wm.setUrl(uri);
        wm.setWebsiteContent(websiteContent);
        wm.setCategoryMap(parseCategoryMap(webDoc, uri, photoTableKeyword));



        Map<String, CategoryModel> categoryMap = new HashMap<String, CategoryModel>();

        return wm;
    }
    private static Map<String, CategoryModel> parseCategoryMap(
            Document webDoc, String uri, String[] photoTableKeyword){
        Map<String, CategoryModel> categoryMap = new HashMap<String, CategoryModel>();
        // find photo row
        Element photoTable = null;
        for (Element e : webDoc.getElementsByTag("ul")){
            if (ifContainString(e, photoTableKeyword)){
                photoTable = e;
                Log.v(TAG, "found element: " + e.text());
                // assume there is only one table that satisfies
                break;
            } else {
                continue;
            }
        }
        Elements children = null;
        try{
            children = photoTable.children();
//            Log.v(TAG, "children: " + children.text());
            // ignore the first element (0)
            for (int i = 1; i < children.size(); i++){
//                categoryMap.put()
                String categoryName = children.get(i).text();
                //Element e = children.get(i).select("li").get(0).select("a[href]").get(0);
                String categoryUrl = uri + children.get(i).select("li").get(0).select("a[href]").get(0).attr("href");
//                Log.v(TAG, categoryName/*.substring(0, categoryName.length() - 4)*/ + ": " + categoryUrl);
                CategoryModel category = new CategoryModel();
                category.setUri(categoryUrl);
                category.setCategory(categoryName);
                //test:
                //Log.v(TAG, "test for pages 25:" + category.getPage(25));
                categoryMap.put(categoryName, category);
            }
        }catch (Exception ex){
            Log.e(TAG, "failed to get children elements");
        }finally {

        }
        return categoryMap;
    }
    private static boolean ifContainString(Element e, String... s){
        for (String str : s){
            if (e.text().contains(str)) {
                return true;
            }
        }
        return false;
    }
}
