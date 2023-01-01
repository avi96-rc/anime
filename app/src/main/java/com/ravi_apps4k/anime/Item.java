package com.ravi_apps4k.anime;

import android.util.Log;

public class Item {
   //String title;
    String id;
    String raw;
//    String full;
//    String regular;
//    String small;
//    String thumb;
//    String category1;
//    String category2;
//    String category3;

    public Item( String id, String raw) {
//        this.title = title;
        this.id = id;
        this.raw = raw;
//        this.full = full;
//        this.regular = regular;
//        this.small = small;
//        this.thumb = thumb;
//        this.category1 = category1;
//        this.category2 = category2;
//        this.category3 = category3;
    }



    public String getId() {
        return id;
    }

    public String getUrl() {
        String resolution0 = "720x1280.jpg";
        String resolution_1 = "1080x1920.jpg";
        String resolution1 = "1080x2280.jpg";
        String resolution2 = "1080x1920.jpg";
        String resolution3 = "1440x2560.jpg";

        String url = raw.substring(0,raw.length()-13)+resolution_1;
        Log.d("onBindViewHolder: ", "url of new pic: "+url);

//        make some changes in raw resoolution and retrurn as ... small res for thumbnail

        return url;
    }

//    public String getFull() {
//        return full;
//    }
//
//    public String getRegular() {
//        return regular;
//    }
//
    public String getSmall() {

        String resolution1 = "240x320.jpg";
        String resolution2 = "240x400.jpg";
        String resolution3 = "320x480.jpg";
        String small = raw.substring(0,raw.length()-13)+resolution1;
        Log.d("onBindViewHolder: ", "url of new pic: "+small);

//        make some changes in raw resoolution and retrurn as ... small res for thumbnail

        return small;
    }

    public String gethd() {

//        String resolution1 = "300x550.jpg";
        String resolution2 = "720x1280.jpg";
        String resolution3 = "1080x1920.jpg";
        String small = raw.substring(0,raw.length()-13)+resolution2;
        Log.d("onBindViewHolder: ", "url of new pic: "+small);

//        make some changes in raw resoolution and retrurn as ... small res for thumbnail

        return small;
    }
//
//    public String getThumb() {
//        return thumb;
//    }

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
