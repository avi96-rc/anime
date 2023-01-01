package com.ravi_apps4k.anime;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataCategory {
    ArrayList<Object> objectList = new ArrayList<Object>();
    List<Object> idImageList = new ArrayList<>();
    public static List<String> name;
    public static List<Integer> url = new ArrayList<Integer>();
    List<Object> imagesforCarousel = new ArrayList<>();
//    Context context;

    public DataCategory(Context context) {
        // get the url here...
        name = new ArrayList<String>();


        for (String str : PublicMethods.listAssetFiles("json", context)) {

            //String f = file.substring(0,file.length()-5);
            name.add(str.substring(0, str.length() - 5));
            imagesforCarousel.add(PublicMethods.getImagesforCarousel(context, str));
            Log.d("TAG", "DataCategory: size of name: " + name.size());

        }


        for (int i = 0; i < name.size(); i++) {
//            CatItem item = new CatItem(name.get(i),R.drawable.ic_round_favorite_24);
            CatItem item = new CatItem(name.get(i), (List<String>) imagesforCarousel.get(i));
            objectList.add(item)
            ;
//            PublicMethods.itemListCategory.add(item);
            Log.d("TAG", "addObjects: loop: " + i + "  person added" + " " + "objectlist size: " + objectList.size());
        }
    }


    public List<Object> getObjectsList() {
        long seed = System.nanoTime();
        Collections.shuffle(objectList, new Random(seed));
        return objectList;
    }


}
