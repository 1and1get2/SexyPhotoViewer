package com.derek.tools;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpManager {
    private static String TAG = "HttpManager";
    private static String CHAR_SET = "GBK";// "UTF-8"
    public static String getData(String uri) {

        BufferedReader reader = null;

        try {
            URL url = new URL(uri);
            return getData(url);

        } catch (Exception e) {
            //e.printStackTrace();
            Log.e(TAG, e.getStackTrace().toString());
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //e.printStackTrace();
                    Log.e(TAG, e.getStackTrace().toString());
                    return null;
                }
            }
        }

    }
    public static String getData(URL url) {

        BufferedReader reader = null;

        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            // set char-set
            con.setRequestProperty("contentType", CHAR_SET);
            con.setRequestProperty("Accept-Charset", CHAR_SET);

            StringBuilder sb = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(con.getInputStream(), CHAR_SET));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            //String content = new String(sb, );
            return sb.toString();

        } catch (Exception e) {
            //e.printStackTrace();
            Log.e(TAG, e.getStackTrace().toString());
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
//                    e.printStackTrace();
                    Log.e(TAG, e.getStackTrace().toString());
                    return null;
                }
            }
        }

    }
	
}
