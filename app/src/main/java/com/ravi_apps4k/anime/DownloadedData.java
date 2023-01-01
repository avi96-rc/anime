package com.ravi_apps4k.anime;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DownloadedData {
    ArrayList<Object> objectList = new ArrayList<Object>();
    List<Object>  idImageList = new ArrayList<>();
    List<String> id= new ArrayList<String>();
    List<Bitmap> bitmap= new ArrayList<Bitmap>();

    public DownloadedData() {
        idImageList = PublicMethods.getDowloadedImages();
        id = (List<String>) idImageList.get(0);
        bitmap = (List<Bitmap>) idImageList.get(1);

    }

    int  a = 0, p = 0;
    public int addObjects(){

        for (int i= 0; i<id.size();i++){
            DownloadedItem item = new DownloadedItem(id.get(i),bitmap.get(i));
            objectList.add(item);
            Log.d("TAG", "addObjects: loop: "+i+"  person added"+" "+"objectlist size: "+objectList.size());
        }
        return objectList.size();
    }


    public List<Object> getItemsList(){
        return objectList;
    }

}
