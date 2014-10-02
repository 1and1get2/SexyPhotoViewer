package com.derek.helloanthy.sexyphotoviewer.model;

import android.graphics.Bitmap;

import java.net.URL;
import java.util.List;

/**
 * Created by derek on 21/09/14.
 */

public class ImagePageModel {
    private int id;

    private String pageLink;
    private List<URL> bitmapLinks;
    private List<Bitmap> bitmaps;

}