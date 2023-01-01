package com.ravi_apps4k.anime;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Data {
    ArrayList<Object> objectList = new ArrayList<Object>();
    List<Object>  idImageList = new ArrayList<>();
    java.util.List<String> id= new ArrayList<String>();
    java.util.List<String> raw= new ArrayList<String>();
//    java.util.List<String> full= new ArrayList<String>();
//    java.util.List<String> regular= new ArrayList<String>();
//    java.util.List<String> small= new ArrayList<String>();
//    java.util.List<String> thumb= new ArrayList<String>();

    public Data(Context context, String categoryName) {
        Integer jsonfileName = R.string.audi;
        

        switch (categoryName){

            case "audi":
                jsonfileName = R.string.audi;
                break;

            case "bentley":
                jsonfileName = R.string.bentley;
                break;

            case "bmw":
                jsonfileName = R.string.bmw;
                break;

            case "bugatti":
                jsonfileName = R.string.bugatti;
                break;

            case "corvette":
                jsonfileName = R.string.corvette;
                break;

            case "ferrari":
                jsonfileName = R.string.ferrari;
                break;

            case "ford":
                jsonfileName = R.string.ford;
                break;

            case "jaguar":
                jsonfileName = R.string.jaguar;
                break;

            case "lamborghini":
                jsonfileName = R.string.lamborghini;
                break;

            case "maserati":
                jsonfileName = R.string.maserati;
                break;

            case "mazda":
                jsonfileName = R.string.mazda;
                break;

            case "mclaren":
                jsonfileName = R.string.mclaren;
                break;

            case "mercedes":
                jsonfileName = R.string.mercedes;
                break;

            case "nissan":
                jsonfileName = R.string.nissan;
                break;

            case "other":
                jsonfileName = R.string.other;
                break;

            case "porsche":
                jsonfileName = R.string.porsche;
                break;

            case "rolls_royce":
                jsonfileName = R.string.rolls_royce;
                break;

            case "toyota":
                jsonfileName = R.string.toyota;
                break;

            default:
                Log.d("TAG", "Data: no cat name matched!");
        }

        String jsonfile = categoryName+".json";
        idImageList = PublicMethods.extractFromJson(context, PublicMethods.decrypt(PublicMethods.readFromInternalFile(context, jsonfile), context));//        idImageList = PublicMethods.extractFromJson(context,PublicMethods.readFromInternalFile(context, context.getString(jsonfileName)));
//        idImageList = PublicMethods.extractFromJson(context,PublicMethods.decrypt(PublicMethods.readFromInternalFile(context, context.getString(jsonfileName)),context));
//        idImageList = PublicMethods.extractFromJson(context,PublicMethods.readFromInternalFile(context, context.getString(jsonfileName)));
        id = (List<String>) idImageList.get(0);
        raw = (List<String>) idImageList.get(1);
//        full = (List<String>) idImageList.get(2);
//        regular = (List<String>) idImageList.get(3);
//        small = (List<String>) idImageList.get(4);
//        thumb = (List<String>) idImageList.get(5);

    }

   public Data(Context context, String categoryName, List<Object> itemlistt) {

        Integer jsonfileName = R.string.audi;

        switch (categoryName){

            case "audi":
                jsonfileName = R.string.audi;
                break;

            case "bentley":
                jsonfileName = R.string.bentley;
                break;

            case "bmw":
                jsonfileName = R.string.bmw;
                break;

            case "bugatti":
                jsonfileName = R.string.bugatti;
                break;

            case "corvette":
                jsonfileName = R.string.corvette;
                break;

            case "ferrari":
                jsonfileName = R.string.ferrari;
                break;

            case "ford":
                jsonfileName = R.string.ford;
                break;

            case "jaguar":
                jsonfileName = R.string.jaguar;
                break;

            case "lamborghini":
                jsonfileName = R.string.lamborghini;
                break;

            case "maserati":
                jsonfileName = R.string.maserati;
                break;

            case "mazda":
                jsonfileName = R.string.mazda;
                break;

            case "mclaren":
                jsonfileName = R.string.mclaren;
                break;

            case "mercedes":
                jsonfileName = R.string.mercedes;
                break;

            case "nissan":
                jsonfileName = R.string.nissan;
                break;

            case "other":
                jsonfileName = R.string.other;
                break;

            case "porsche":
                jsonfileName = R.string.porsche;
                break;

            case "rolls_royce":
                jsonfileName = R.string.rolls_royce;
                break;

            case "toyota":
                jsonfileName = R.string.toyota;
                break;

            default:
                Log.d("TAG", "Data: no cat name matched!");
        }

       String jsonfile = categoryName+".json";
        idImageList = PublicMethods.extractFromJson(context, PublicMethods.decrypt(PublicMethods.readFromInternalFile(context, jsonfile), context));//        idImageList = PublicMethods.extractFromJson(context,PublicMethods.readFromInternalFile(context, context.getString(jsonfileName)));
//        idImageList = PublicMethods.extractFromJson(context,PublicMethods.readFromInternalFile(context, context.getString(jsonfileName)));
       Log.d("TAG", "Data: "+categoryName);
        id = (List<String>) idImageList.get(0);
        raw = (List<String>) idImageList.get(1);
//        full = (List<String>) idImageList.get(2);
//        regular = (List<String>) idImageList.get(3);
//        small = (List<String>) idImageList.get(4);
//        thumb = (List<String>) idImageList.get(5);

    }




    public int addObjects(){

        for (int i= 0; i<id.size();i++){
            Item item = new Item(id.get(i),raw.get(i));
            objectList.add(item);
            Log.d("TAG", "addObjects: loop: "+i+"  person added"+" "+"objectlist size: "+objectList.size());
        }
        return objectList.size();
    }



    public List<Object> addObjects(List<Object> itemlistt, String home){

        for (int i= 0; i<id.size();i++){
            Item item = new Item(id.get(i),raw.get(i));
            itemlistt.add(item);
            objectList.add(item);
            Log.d("TAG", "addObjects: loop: "+i+"  person added"+" "+"objectlist size: "+objectList.size());
        }
        return  objectList;

    }

 public int addObjects(List<String> fouriteList){

     List<String> fouriteListt = new ArrayList<>();
     for (String items: fouriteList){
         fouriteListt.add(items);
     }

        for (int i= 0; i<id.size();i++){
            for (int j = 0; j<fouriteListt.size(); j++){
                if (id.get(i).equals(fouriteListt.get(j))){
                    Item item = new Item(id.get(i),raw.get(i));
                    objectList.add(item);
                    Log.d("TAG", "addObjects: loop: "+i+"  person added"+" "+"objectlist size: "+objectList.size());
                    fouriteListt.remove(j);
                    break;
                }
            }

        }
        return objectList.size();
    }

    public List<Object> getItemsList(){
        return objectList;
    }

}
