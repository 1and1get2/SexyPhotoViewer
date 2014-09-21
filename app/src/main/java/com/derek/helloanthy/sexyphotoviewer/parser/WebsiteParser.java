package com.derek.helloanthy.sexyphotoviewer.parser;

import com.derek.helloanthy.sexyphotoviewer.model.WebsiteModel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by derek on 20/09/14.
 */
public class WebsiteParser {

    public static WebsiteModel parser(String websiteContent){
        WebsiteModel wm = new WebsiteModel();
        try{
            boolean inElementTag = false;
            String currentTagName = "";
            String currentFullTagName = "";
            List<WebsiteModel.Category> categories = new ArrayList<WebsiteModel.Category>();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(websiteContent));

            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName();
                        currentFullTagName += "." + currentTagName;
                        break;

                }
            }

        } catch (Exception ex){

        }
        return null;
    }
}
