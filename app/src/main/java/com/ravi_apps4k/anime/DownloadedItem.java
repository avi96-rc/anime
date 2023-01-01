package com.ravi_apps4k.anime;

import android.graphics.Bitmap;

public class DownloadedItem {
   //String title;
    String id;
    Bitmap bitmap;

//    String category1;
//    String category2;
//    String category3;

    public DownloadedItem(String id, Bitmap bitmap) {
//        this.title = title;
        this.id = id;
        this.bitmap = bitmap;

//        this.category1 = category1;
//        this.category2 = category2;
//        this.category3 = category3;
    }



    public String getId() {
        return id;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }


//    public String getCategory1() {
//        return category1;
//    }
//
//    public String getCategory2() {
//        return category2;
//    }
//
//    public String getCategory3() {
//        return category3;
//    }
}
