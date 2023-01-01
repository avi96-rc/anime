package com.ravi_apps4k.anime;

import java.util.List;

public class CatItem {
   //String title;
    String name;
//    Integer url;
    List<String> imagesforCarousel;
//    String full;
//    String regular;
//    String small;
//    String thumb;
//    String category1;
//    String category2;
//    String category3;

    public CatItem(String name, List<String> imagelinks ) {
//        this.title = title;
        this.name = name;
//        this.url = url;
        this.imagesforCarousel = imagelinks;

//        this.full = full;
//        this.regular = regular;
//        this.small = small;
//        this.thumb = thumb;
//        this.category1 = category1;
//        this.category2 = category2;
//        this.category3 = category3;
    }



    public String getName() {
        return name;
    }

//    public Integer getUrl() {
//        return url;
//    }
    public List<String> getimagesforCarousel() {
        return  imagesforCarousel;
    }

//    public String getFull() {
//        return full;
//    }
//
//    public String getRegular() {
//        return regular;
//    }
//
//    public String getSmall() {
//        return small;
//    }
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
