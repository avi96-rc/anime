package com.ravi_apps4k.anime;

import android.Manifest;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ravi_apps4k.anime.networks.AsyncFirebaseFirst;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.ravi_apps4k.anime.MainActivity.STORAGE_PERMISSION_CODE;

public class PublicMethods {

    public static int pagerAdsGap= 9;
    public static Boolean isUpdateAvailable= false;
    public static String appname="Anime";
    public static String folder =Environment.getExternalStorageDirectory().toString()+"/"+appname;
    public static String folderNew =Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ "/"+appname ;

    public static List<String > listOfImages = new ArrayList<>();
//    public static List<Object > imagesInDowloads = new ArrayList<>();
    public static List<String > favouriteListfromJson = new ArrayList<>();
    public static List<String > favouriteListfromJsonurl = new ArrayList<>();
    public static List<Object > favouriteList = new ArrayList<>();
    public static List<BottomSheetDialogFragment>  bottomSheetDialogFragmentList = new ArrayList<>();
//    public static List<Object> itemListMain;
    public static List<Object> itemListOriginal;
    public static List<Object> itemListPager;
    public static List<Object> itemListDownloads;
    public static List<Object> itemListCategory = new ArrayList<>();
    public static List<String> favouriteIdfromJson = new ArrayList<>();

    public static List<Object> itemListCat = new ArrayList<>();
    public static List<Object> itemListRose = new ArrayList<>();
    public static List<Object> itemListCar = new ArrayList<>();
    public static List<Object> itemList = new ArrayList<>();
    public static List<Object> itemListAbstract = new ArrayList<>();
    public static List<Object> itemListAircraft = new ArrayList<>();
    public static List<Object> itemListArchitecture = new ArrayList<>();
    public static List<Object> itemListArt = new ArrayList<>();
    public static List<Object> itemListBaby = new ArrayList<>();
    public static List<Object> itemListBeach = new ArrayList<>();
    public static List<Object> itemListBlack = new ArrayList<>();
    public static List<Object> itemListChristmas = new ArrayList<>();
    public static List<Object> itemListCity = new ArrayList<>();
    public static List<Object> itemListDrink = new ArrayList<>();
    public static List<Object> itemListFire = new ArrayList<>();
//    public static List<Object> itemList = new ArrayList<>();
//    public static List<Object> itemList = new ArrayList<>();
//    public static List<Object> itemList = new ArrayList<>();
//    public static List<Object> itemList = new ArrayList<>();
//    public static List<Object> itemList = new ArrayList<>();
//    public static List<Object> itemList = new ArrayList<>();
//    public static List<Object> itemList = new ArrayList<>();
//    public static List<Object> itemList = new ArrayList<>();
    public static long seed ;


static int i=0;



    public static  void convertfavouriteListToJsonToJson( Context context, List<String> idd,List<String> imagee) {

        JSONObject jsonObject1 = new JSONObject();
        JSONArray jsonArray1 = new JSONArray();

        Log.d(TAG, "convertfavouriteListfromJsonToJson: size of favouriteListfromJson "+idd.size());


        for(int i=0; i< idd.size();i++){
            JSONObject jsonObject2 = new JSONObject();
            try {
                jsonObject2.put("id",idd.get(i));
                jsonObject2.put("url",imagee.get(i));
                Log.d(TAG, "convertfavouriteListfromJsonToJson: favouriteListfromJson "+i+" :"+idd.get(i));
                jsonArray1.put(jsonObject2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            jsonObject1.put("main",jsonArray1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AsyncFirebaseFirst.encrypt(jsonObject1.toString(),context,"favourite.json");
//        writeToFile(jsonObject1.toString(),"favourite.json",context);

    }



// make it write to internal
    public static  void writeToFile(String data, String fileName, Context context) {
        Log.d(TAG, "writeToFile: string to write: "+ data);

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Log.d("TAG", "writeToFile: writing value to file done!");
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }




    public static List<Object> loadFavouritesFromJson(Context context) {
        List<Object> itemList = new ArrayList<>();
        List<Object> idImageList  = extractFromJsonforFavour(context,decrypt(readFromInternalFile(context,context.getString(R.string.favourite_file_name)),context) );

//        Log.d(TAG, "loadFavouritesFromJson: size of favouriteListfromJson: "+favouriteListfromJson);

        List<String> id = (List<String>) idImageList.get(0);
        List<String> image =(List<String>)  idImageList.get(1);



        for (int i= 0; i<id.size();i++){
            Item item = new Item(id.get(i),image.get(i));
            itemList.add(item);
            favouriteList.add(item);
//            Log.d("TAG", "addObjects: loop: "+i+"  person added"+" "+"objectlist size: "+objectList.size());
        }

//        favouriteList = data.getItemsList();
//        for (Object obj: favouriteList){
//            final Item item= (Item) obj;
//            Log.d(TAG, "loadFavourites: item.getid: "+item.getSmall());
//        }

        return itemList;
    }

    public static void addItemsTofavouriteListfromJson(String id,String url, Context context){
        List<Object> idImageList  = extractFromJsonforFavour(context,decrypt(readFromInternalFile(context,context.getString(R.string.favourite_file_name)),context) );

        List<String> idd = (List<String>) idImageList.get(0);
        List<String> imagee =(List<String>)  idImageList.get(1);

        idd.add(id);
        imagee.add(url);


//        favouriteListfromJson.add(id);
//       favouriteListfromJsonurl.add(url);

//        for (String  items: favouriteListfromJson){
//            Log.d(TAG, "addItemsTofavouriteListfromJson: "+ id);
//        }
        convertfavouriteListToJsonToJson(context, idd,imagee);
        reLoadFavourites(context);
    }

    public static void removeItemsFromfavouriteListfromJson(String id,String url, Context context){
        List<Object> idImageList  = extractFromJsonforFavour(context,decrypt(readFromInternalFile(context,context.getString(R.string.favourite_file_name)),context) );

        List<String> idd = (List<String>) idImageList.get(0);
        List<String> imagee =(List<String>)  idImageList.get(1);

        idd.remove(id);
        imagee.remove(url);
//        favouriteListfromJson.remove(id);
//        favouriteListfromJsonurl.remove(url);
        convertfavouriteListToJsonToJson(context, idd,imagee);
        reLoadFavourites(context);
    }

    // call this when when add favourite after items to favouriteListfromJson
 public static void reLoadFavourites(Context context) {

     loadFavouritesFromJson(MainActivity.context);

    }



//    public static List<Object> extractFromFavouriteJson(Context context, String jsonObjectString){
//        //String jsonString= jsonObjectString;
//        // do something with jsonbjectString
//        List<Object>  idImageList = new ArrayList<>();
//        List<String>  idList = new ArrayList<>();
//        List<Object>  ImageList = new ArrayList<>();
//
//
//
//        // Log.d(TAG, "setResult: "+jsonObjectString);
//
//        try{
//            JSONObject jsonObject = new JSONObject(jsonObjectString);
//            JSONArray jsonArray = jsonObject.getJSONArray("main");
//
//            // Log.d(TAG, "extractFromJson: main array"+jsonArray.toString());
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                //
//                idList.add(jsonArray.getJSONObject(i).getString("id"));
//                ImageList.add(jsonArray.getJSONObject(i).getString("url"));
//                Log.d(TAG, "extractFromFavouriteJson: id:"+jsonArray.getJSONObject(i).getString("id"));
//
//
//            }
//
//
//            for (int  i= 0; i<idList.size();i++){
//                Log.d(TAG, "Id:"+i+" "+ idList.get(i));
//            }
//
//
//            Log.d(TAG, "extractFromFavouriteJson: json Extraction complete. All okay! "+  idList.size());
//            return idList;
//
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            Log.d(TAG, "extractFromFavouriteJson : json: execption cought");
//           // Toast.makeText(context, "something is wrong with json text file", Toast.LENGTH_SHORT).show();
//        }
//
//        return idList;
//
//    }


    public static String readFromInternalFile(Context context,String filename) {

        String ret = "nothing";
        Log.d("TAG", "readFromInternalFile: "+filename);


        try {
            InputStream inputStream = context.openFileInput(filename);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader;
                inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();

                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: file not created yet" + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        Log.d("TAG", "readFromInternalFile: file content"+ret);
        return ret;
    }



    public static String readEncodedFile(Context context, String filename) {

        String result = "empty";
        Log.d(TAG, "readEncodedFile: file name: "+filename);

        try {
            InputStream inputStream = context.getAssets().open(filename);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader;
                inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                Log.d(TAG, "readFromFile: text found. All okay!\n\n"+stringBuilder.toString());

                result = stringBuilder.toString();
                return  decrypt(result,context);
//                return result;
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: file not created yet " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return result;
    }

   public static String readFileFromAssets(Context context, String filename) {

        String result = "empty";

        try {
            InputStream inputStream = context.getAssets().open(filename);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader;
                inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                Log.d(TAG, "readFromFile: text found. All okay!\n\n"+stringBuilder.toString());

                result = stringBuilder.toString();
//                return  decrypt(result,context);
                return result;
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: file not created yet " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return result;
    }






    public static List<Object> extractFromJson(Context context, String jsonObjectString){
        //String jsonString= jsonObjectString;
        // do something with jsonbjectString
        List<Object>  idImageList = new ArrayList<>();
        List<String> id= new ArrayList<String>();
        List<String> raw= new ArrayList<String>();
//        List<String> full= new ArrayList<String>();
//        List<String> regular= new ArrayList<String>();
//        List<String> small= new ArrayList<String>();
//        List<String> thumb= new ArrayList<String>();

         Log.d(TAG, "extractFromJson: "+jsonObjectString);

        try{
            JSONObject jsonObject = new JSONObject(jsonObjectString);
            JSONArray jsonArray = jsonObject.getJSONArray("main");

           // Log.d(TAG, "extractFromJson: main array"+jsonArray.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                //

                Log.d(TAG, "extractFromJson: id:"+jsonArray.getJSONObject(i).getString("id"));
                id.add(jsonArray.getJSONObject(i).getString("id"));
                raw.add(jsonArray.getJSONObject(i).getString("url"));
//                raw.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("raw"));
//                full.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("full"));
//                regular.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("regular"));
//                small.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("small"));
//                thumb.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("thumb"));

            }


            for (int  i= 0; i<id.size();i++){
                Log.d(TAG, "Id:"+i+" "+ id.get(i));
//                Log.d(TAG, "Title: "+ id.get(i));
//                Log.d(TAG, "Title: "+ id.get(i));
                Log.d(TAG, "  \n \n");
            }

            idImageList.add(id);
            idImageList.add(raw);
//            idImageList.add(full);
//            idImageList.add(regular);
//            idImageList.add(small);
//            idImageList.add(thumb);
            Log.d(TAG, "extractFromJson: json Extraction complete. All okay! "+  idImageList.size());
            return idImageList;


        }
        catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "json: execption cought");
           // Toast.makeText(context, "something is wrong with json text file", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "extractFromJson : json: execption cought");
        }

        return idImageList;

    }



    public static List<Object> extractFromJsonforFavour(Context context, String jsonObjectString){
        //String jsonString= jsonObjectString;
        // do something with jsonbjectString

        List<String> id= new ArrayList<String>();
        List<String> raw= new ArrayList<String>();
        List<Object>  idImageList = new ArrayList<>();
        idImageList.add(id);
        idImageList.add(raw);

//        List<String> full= new ArrayList<String>();
//        List<String> regular= new ArrayList<String>();
//        List<String> small= new ArrayList<String>();
//        List<String> thumb= new ArrayList<String>();

        Log.d(TAG, "extractFromJson: "+jsonObjectString);

        try{
            JSONObject jsonObject = new JSONObject(jsonObjectString);
            JSONArray jsonArray = jsonObject.getJSONArray("main");

            // Log.d(TAG, "extractFromJson: main array"+jsonArray.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                //

                Log.d(TAG, "extractFromJson: id:"+jsonArray.getJSONObject(i).getString("id"));
                id.add(jsonArray.getJSONObject(i).getString("id"));
                favouriteIdfromJson.add(jsonArray.getJSONObject(i).getString("id"));
                raw.add(jsonArray.getJSONObject(i).getString("url"));
//                raw.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("raw"));
//                full.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("full"));
//                regular.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("regular"));
//                small.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("small"));
//                thumb.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("thumb"));

            }


            for (int  i= 0; i<id.size();i++){
                Log.d(TAG, "Id:"+i+" "+ id.get(i));
//                Log.d(TAG, "Title: "+ id.get(i));
//                Log.d(TAG, "Title: "+ id.get(i));
                Log.d(TAG, "  \n \n");
            }

//            idImageList.add(id);
//            idImageList.add(raw);
//            idImageList.add(full);
//            idImageList.add(regular);
//            idImageList.add(small);
//            idImageList.add(thumb);
            Log.d(TAG, "extractFromJson: json Extraction complete. All okay! "+  idImageList.size());
            return idImageList;


        }
        catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "json: execption cought");
            // Toast.makeText(context, "something is wrong with json text file", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "extractFromJson : json: execption cought");
        }

        return idImageList;

    }




    public static List<Object> extractFromJsonforCarousel(Context context, String jsonObjectString){
        //String jsonString= jsonObjectString;
        // do something with jsonbjectString
        List<Object>  idImageList = new ArrayList<>();
//        List<String> id= new ArrayList<String>();
        List<String> raw= new ArrayList<String>();
//        List<String> full= new ArrayList<String>();
//        List<String> regular= new ArrayList<String>();
//        List<String> small= new ArrayList<String>();
//        List<String> thumb= new ArrayList<String>();

//         Log.d(TAG, "extractFromJson: "+jsonObjectString);

        try{
            JSONObject jsonObject = new JSONObject(jsonObjectString);
            JSONArray jsonArray = jsonObject.getJSONArray("main");

           // Log.d(TAG, "extractFromJson: main array"+jsonArray.toString());

            for (int i = 0; i < 9; i++) {
                //

                Log.d(TAG, "extractFromJson: id:"+jsonArray.getJSONObject(i).getString("id"));
//                id.add(jsonArray.getJSONObject(i).getString("id"));
                String rrr = jsonArray.getJSONObject(i).getString("url");
                String resolution1 = "300x550.jpg";
                String resolution2 = "1280x720.jpg";
                String small = rrr.substring(0,rrr.length()-13)+resolution2;
                raw.add(small);

//                raw.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("raw"));
//                full.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("full"));
//                regular.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("regular"));
//                small.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("small"));
//                thumb.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("thumb"));

            }


//            for (int  i= 0; i<id.size();i++){
//                Log.d(TAG, "Id:"+i+" "+ id.get(i));
////                Log.d(TAG, "Title: "+ id.get(i));
////                Log.d(TAG, "Title: "+ id.get(i));
//                Log.d(TAG, "  \n \n");
//            }

//            idImageList.add(id);
            idImageList.add(raw);
//            idImageList.add(full);
//            idImageList.add(regular);
//            idImageList.add(small);
//            idImageList.add(thumb);
            Log.d(TAG, "extractFromJson: json Extraction complete. All okay! "+  idImageList.size());
            return idImageList;


        }
        catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "json: execption cought");
           // Toast.makeText(context, "something is wrong with json text file", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "extractFromJson : json: execption cought");
        }

        return idImageList;

    }



    public static List<Object> extractFromJsonUnsplash(Context context, String jsonObjectString){
        //String jsonString= jsonObjectString;
        // do something with jsonbjectString
        List<Object>  idImageList = new ArrayList<>();
        List<String> id= new ArrayList<String>();
        List<String> raw= new ArrayList<String>();
        List<String> full= new ArrayList<String>();
        List<String> regular= new ArrayList<String>();
        List<String> small= new ArrayList<String>();
        List<String> thumb= new ArrayList<String>();

        // Log.d(TAG, "setResult: "+jsonObjectString);

        try{
            JSONObject jsonObject = new JSONObject(jsonObjectString);
            JSONArray jsonArray = jsonObject.getJSONArray("main");

            // Log.d(TAG, "extractFromJson: main array"+jsonArray.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                //
                Log.d(TAG, "extractFromJson: id:"+jsonArray.getJSONObject(i).getString("id"));
                id.add(jsonArray.getJSONObject(i).getString("id"));
                raw.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("raw"));
                full.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("full"));
                regular.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("regular"));
                small.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("small"));
                thumb.add(jsonArray.getJSONObject(i).getJSONObject("urls").getString("thumb"));

            }


            for (int  i= 0; i<id.size();i++){
                Log.d(TAG, "Id:"+i+" "+ id.get(i));
//                Log.d(TAG, "Title: "+ id.get(i));
//                Log.d(TAG, "Title: "+ id.get(i));
                Log.d(TAG, "  \n \n");
            }

            idImageList.add(id);
            idImageList.add(raw);
            idImageList.add(full);
            idImageList.add(regular);
            idImageList.add(small);
            idImageList.add(thumb);
            Log.d(TAG, "extractFromJson: json Extraction complete. All okay! "+  idImageList.size());
            return idImageList;


        }
        catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "json: execption cought");
            // Toast.makeText(context, "something is wrong with json text file", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "extractFromJson : json: execption cought");
        }

        return idImageList;

    }


    public static String decrypt(String encodedText, Context context){
        String decodedText =null;
        Log.d(TAG, "decrypt: encoded test: "+encodedText);
            byte[] data1 = Base64.decode(encodedText, Base64.URL_SAFE);
        try {
            decodedText = new String(data1, "UTF-8");
            Log.d(TAG, "decrypt: decodedText: "+decodedText);
//            writeToFile(decodedText, "decoded.json",context);
            return  decodedText;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return  decodedText;
    }


    public static String encrypt(String plainText, Context context,String filename){
        String encryptedText =null;
        Log.d(TAG, "decrypt: plain text: "+plainText);
        // Sending side
        byte[] data = new byte[0];
        try {
            data = plainText.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        encryptedText = Base64.encodeToString(data, Base64.URL_SAFE);
        writeToFile(encryptedText,filename,context);
        return  encryptedText;
    }





    public  static void makecarousel(CarouselView carouselView, final List<String> videoImage){

        Log.d(TAG, "makecarousel: executed");
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(final int position, ImageView imageView) {
                Picasso.get().load(videoImage.get(position)).into(imageView);
                //imageView.setImageResource(sampleImages[position]);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        Intent intent = new Intent(context,Player.class);
//                        intent.putExtra("videourl",videoId.get(position));
//                        intent.putExtra("title",videotitle.get(position));
//                        context.startActivity(intent);

                    }
                });
            }
        });
        carouselView.setPageCount(videoImage.size());


    }


    public static int[] getResolution (Context context){

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);

        int  height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int resolution [] = new int[2];
        resolution[0] = height;
        resolution[1] = width;
        Log.d(TAG, "getResolution: height: "+resolution[0]);
        Log.d(TAG, "getResolution: width: "+resolution[1]);
        return resolution;

    }





    public static void saveToInternalStorage(

            Context context,
            Bitmap bitmap,
            String fileName,
            String url,
            Boolean quickWall,
            Boolean setAs,
            Boolean share,
            String quality){

       Boolean gotPermission = checkPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                STORAGE_PERMISSION_CODE,context);

       if (gotPermission){
           String fname = fileName+".JPEG";
           Boolean isAlreadySaved = checkIfAlreadySaved(fname);        // check here if file  is already dowloaded..

           if (!isAlreadySaved){

               Log.d(TAG, "saveToInternalStorage: file url: "+ url);
                // saving file using async class

              if (quality.equals("fullhd")){
                  Log.d(TAG, "saveToInternalStorage: quality: "+ quality);
                  savefittedImage(bitmap,fname);

                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                      Log.d("TAG", "commonDocumentDirPath: android 11");
                      scanFile(context,folderNew+"/"+fname,quickWall,setAs,share);
                  } else {
                      Log.d("TAG", "commonDocumentDirPath: android below 11");
                      scanFile(context,folder+"/"+fname,quickWall,setAs,share);
                  }

              } else if (quality.equals("share")){
                   Log.d(TAG, "saveToInternalStorage: quality: "+ quality);
                   savefittedImage(bitmap,fname);

                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                      Log.d("TAG", "commonDocumentDirPath: android 11");
                      scanFile(context,folderNew+"/"+fname,quickWall,setAs,share);
                  } else {
                      Log.d("TAG", "commonDocumentDirPath: android below 11");
                      scanFile(context,folder+"/"+fname,quickWall,setAs,share);
                  }


               }


           /*

               File myDir = new File(folder);
               myDir.mkdirs();

               File file = new File(myDir, fname);
               Log.d("TAG", "" + file);
               if (file.exists())
                   file.delete();
               try {
                   final FileOutputStream out = new FileOutputStream(file);
                   bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                   out.flush();
                   out.close();
                   listOfImages.add(fname);
               } catch (Exception e) {
                   Log.d(TAG, "saveToInternalStorage: Image not saved to internalStorage");
                   e.printStackTrace();
               }
               */



           }else {
               if (!quickWall && !setAs && !share){
                   // image is already downloaded..
                   Log.d(TAG, "saveToInternalStorage: image already  downloaded.");
                   Toast.makeText(MainActivity.context," Image already downloaded!!",Toast.LENGTH_LONG).show();
               }

               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                   Log.d("TAG", "commonDocumentDirPath: android 11");
                   scanFile(context,folderNew+"/"+fname,quickWall,setAs,share);
               } else {
                   Log.d("TAG", "commonDocumentDirPath: android below 11");
                   scanFile(context,folder+"/"+fname,quickWall,setAs,share);
               }



           }





       } else  {
           // didn't  get permission
           Toast.makeText(MainActivity.context," Give Storage permission",Toast.LENGTH_LONG).show();

       }


    }



    public  static  void savefittedImage(Bitmap bitmap, String fname){

        Boolean inDonwload = true;
        File myDir =null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Log.d("TAG", "commonDocumentDirPath: android 11");
            myDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ "/"+appname );
            myDir.mkdirs();
        } else {
            myDir = new File(folder);
            myDir.mkdirs();
            inDonwload = false;
        }


               File file = new File(myDir, fname);
               Log.d("TAG", "" + file);
               if (file.exists())
                   file.delete();
               try {
                   final FileOutputStream out = new FileOutputStream(file);
                   bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                   out.flush();
                   out.close();
                   //listOfImages.add(fname);
                   getListOfImages();
                   if (inDonwload){
                       Toast.makeText(MainActivity.context, "Image saved to "+"Downloads/"+appname,Toast.LENGTH_LONG).show();
                   }else {
                       Toast.makeText(MainActivity.context, "Image saved to "+folder.toString(),Toast.LENGTH_LONG).show();
                   }
                   Log.d(TAG, "savefittedImage: Image  saved to internalStorage");


               } catch (Exception e) {
                   Log.d(TAG, "saveToInternalStorage: Image not saved to internalStorage");
                   e.printStackTrace();
               }

    }


    public static void scanFile(final Context context,
                                String path,
                                final Boolean quickWall,
                                final Boolean setAs,
                                final Boolean share) {

        Log.d(TAG, "scanFile: scanning media");

        MediaScannerConnection.scanFile(context,
                new String[] { path }, null,
                new MediaScannerConnection.OnScanCompletedListener() {

                    public void onScanCompleted(String path, Uri uri) {

                        Log.d(TAG, "onScanCompleted: uri: "+uri);
                        if(uri == null){
                            Log.d("Tag", "Scan finished. image not  saved... invalid uri.");
                        }else {
                            Log.d("Tag", "Scan finished. You can view the image in the gallery now.");

                        }


                        if (quickWall){
                            setQuickWallpaperPhoneByUri(uri,context);
                        }

                        if (setAs){
                            setPhotoAs(uri,context);
                        }
                        if (share){
                            sharePhotoByUri(uri,context);
                        }


                    }
                });


    }


    public  static void setQuickWallpaperPhoneByUri(Uri uri, Context contextt){
        Log.d(TAG, "setQuickWallpaperPhoneByUri: ");
        WallpaperManager wallManager = WallpaperManager.getInstance(contextt.getApplicationContext());
        Intent intent = new Intent(wallManager.getCropAndSetWallpaperIntent(uri));
        contextt.startActivity(intent);

    }



    public  static void sharePhotoByUri(Uri uri, Context contextt){
        Log.d(TAG, "sharePhotoByUri: ");
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        share.putExtra(Intent.EXTRA_STREAM, uri);
        String downhands ="\n\uD83D\uDC47\uD83C\uDFFC\uD83D\uDC47\uD83C\uDFFC\uD83D\uDC47\uD83C\uDFFC\uD83D\uDC47\uD83C\uDFFC";
        String desc= "Get more " + contextt.getString(R.string.app_name)+" wallpapers.";
        String shareBody =

                desc  +downhands+


                        "\nhttps://play.google.com/store/apps/details?id=" + contextt.getPackageName();
        share.putExtra(Intent.EXTRA_SUBJECT, contextt.getString(R.string.app_name));
        share.putExtra(Intent.EXTRA_TEXT, shareBody);
        contextt.startActivity(Intent.createChooser(share, "Share Image"));
//        WallpaperManager wallManager = WallpaperManager.getInstance(contextt.getApplicationContext());
//        Intent intent = new Intent(wallManager.getCropAndSetWallpaperIntent(uri));
//        contextt.startActivity(intent);

    }




    public  static void setPhotoAs(Uri uri, Context contextt){
        Log.d(TAG, "setPhotoAs: ");

        Intent intent = new Intent(Intent.ACTION_ATTACH_DATA);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setDataAndType(uri, "image/jpeg");
        intent.putExtra("mimeType", "image/jpeg");
        contextt.startActivity(Intent.createChooser(intent, "Set as:"));
//        WallpaperManager wallManager = WallpaperManager.getInstance(contextt.getApplicationContext());
//        Intent intent = new Intent(wallManager.getCropAndSetWallpaperIntent(uri));
//        contextt.startActivity(intent);

    }




    public static Bitmap loadImageFromStorage(String fileName)
    {
        try {
            File f=new File(folder, fileName+".JPEG");
            i++;
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            Log.d("TAG", "loadImageFromStorage: "+b);
            return b;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return null;

    }

    public  static void setWallpaperPhone(Bitmap bitmap, Context contextt){
        WallpaperManager wallManager = WallpaperManager.getInstance(contextt.getApplicationContext());
        try {

            wallManager.setBitmap(bitmap);
            Log.d("TAG", "onBitmapLoaded: Wallpaper Set Successfully!! ");
            Toast.makeText(MainActivity.context,"Wallpaper Set Successfully!! ",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.d("TAG", "onBitmapLoaded: Wallpaper not Set !! ");
        }

    }



    public static Boolean  checkPermission(String permission, int requestCode, Context context)
    {

        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(
                MainActivity.context,
                permission)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions((MainActivity)context, new String[] { permission }, requestCode);
        }
        else {
            makeFolder();
            getListOfImages();
            return true;
             //Toast.makeText(context, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
        return false;
    }



    public static void makeFolder(){

        makeFolderInDownloads(PublicMethods.appname).mkdirs();
//
//        String folder = Environment.getExternalStorageDirectory().toString()+PublicMethods.appname;
//        File myDir = new File(folder);
//        myDir.mkdirs();
    }


    public  static  File makeFolderInDownloads(String FolderName){
        File dir = null ;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Log.d("TAG", "commonDocumentDirPath: android 11");
            dir = new File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ "/"+FolderName );
        } else {
            Log.d("TAG", "commonDocumentDirPath: android below 11");
            dir = new File(Environment.getExternalStorageDirectory() + "/"+FolderName);
        }


        return  dir ;

    }





    public static List<String> getListOfImages(){
        
//        //
//        String path = folder;
//        Log.d("Files", "Path: " + path);
//        File directory = new File(path);
//        File[] files = directory.listFiles();
//        Log.d("Files", "Size: "+ files.length);
//        for (int i = 0; i < files.length; i++) {
//            Log.d("Files", "FileName:" + files[i].getName());
//            listOfImages.add(files[i].getName());
//        }
        return listOfImages;
    }

 public static List<Object> getDowloadedImages(){

        //
        String path = folder;
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] listFile;
         List<Object > images = new ArrayList<>();
     List<Object>  idImageList = new ArrayList<>();
      List<Object > imagesInDowloads = new ArrayList<>();

//     File file= new File(android.os.Environment.getExternalStorageDirectory(),"MapleBear");
     File file= new File(path);

     if (file.isDirectory()) {

         listFile = file.listFiles();

         for (int i = 0; i < listFile.length; i++)
         {
             images.add(listFile[i].getAbsolutePath());
//             imagesInDowloads.add(listFile[i].getAbsolutePath());


         }

         for (int i = 0; i < images.size(); i++)
         {
             Bitmap myBitmap = BitmapFactory.decodeFile((String) images.get(i));
             Bitmap Gbitmap = Bitmap.createScaledBitmap(myBitmap,400,600,true);
             imagesInDowloads.add(Gbitmap);
             Log.d(TAG, "getDowloadedImages: files in downloads: "+  images.get(i));


         }
     }

     idImageList.add(images);
     idImageList.add(imagesInDowloads);

        return idImageList;
    }



    private static Boolean checkIfAlreadySaved(String fileName) {
        
        for (String item: listOfImages) {
            
            if (item.equals(fileName)){
                Log.d(TAG, "checkIfAlreadySaved: file match found.."+ item);
                return true;
            }
        }
        return false;
    }



    public static List<Object> getItemlist(Context context, String categoryName) {
         List<String> itemListCategoryString = new ArrayList<>();
        for ( String str :PublicMethods.listAssetFiles("json",context) ){

            //String f = file.substring(0,file.length()-5);
            itemListCategoryString.add(str.substring(0,str.length()-5));
            Log.d("TAG", "DataCategory: size of name: "+itemListCategoryString.size());

        }

        List<Object> itemListt = new ArrayList<>();
        if (categoryName.equals("home")){
            for (String str:itemListCategoryString){
//                 CatItem item= (CatItem) object;
                Data data = new Data(context,str,itemListt);
                data.addObjects(itemListt,"home");
//                switch (item.getName()){
//                    case "rose":
//                        itemListRose = data.addObjects(itemListt,"home");
//                        break;
//                    case "home":
//                        itemListRose = data.addObjects(itemListt,"home");
//                        break;
//                    case "car":
//                        itemListCar = data.addObjects(itemListt,"home");
//                        break;
//                    case "cat":
//                        itemListCat = data.addObjects(itemListt,"home");
//                        break;
//                    default:
//                        Log.d("TAG", "Data: no category name matched!");
//                }

            }

         return itemListt;
        }
        else {
            Data data = new Data(context,categoryName);
            int sizeOfItemlist = data.addObjects();
            Log.d(TAG, "loadData: itemlist size: " +sizeOfItemlist);
            return data.getItemsList();
        }

    }


    public static List<Object> loadDownloadedData(Context context) {
        DownloadedData downloadedData = new DownloadedData();
        int sizeOfItemlist = downloadedData.addObjects();
        Log.d(TAG, "loadData: itemlist size: " +sizeOfItemlist);
        itemListDownloads = downloadedData.getItemsList();
        return downloadedData.getItemsList();
    }



    public static void   loadCategory(Context context) {

        String cat0 = "Guardian of Galaxy";
        String cat1= "thor";
        String cat2 = "ironman";
        String cat3 = "black panther";
        String cat4 = "captain marvel";
        String cat5 = "captain america";
        String cat6 = "spiderman";
        String cat7 = "batman";
        String cat8 = "Wonder Woman";
        String cat9 = "Flash";
        String cat10 = "X-Men";
        String cat11 = "Arrow";
        String cat12 = "Super Girl";
        String cat13 = "Wolverine";
        String cat14 = "Joker";
        String cat15 = "Hulk";
        String cat16 = "Deadpool";
        String cat17 = "Venom";
        String cat18 = "Doctor Strange";
        String cat19 = "Avengers";
        String cat20 = "superman";
        String cat21 = "Black Widow";





//        DataCategory dataCategory = new DataCategory();
//        itemListCategory = dataCategory.getObjectsList();

//        new AsyncTaskk().executeOnExecutor(AsyncTaskk.THREAD_POOL_EXECUTOR);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat0);

//
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat0);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat1);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat2);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat3);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat4);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat5);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat6);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat7);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat8);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat9);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat10);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat11);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat12);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat13);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat14);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat15);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat16);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat17);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat18);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat19);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat20);
//        new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat21);
//         asyncFirebasee = new AsyncFirebaseUpTime().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cat21);

//        new AsyncFirebaseUpTime().execute(cat8);
        return;
    }


    public static List<String> getImagesforCarousel(Context context, String fileName){

        List<Object>  idImageList = new ArrayList<>();
        List<String> id= new ArrayList<String>();
        List<String> raw= new ArrayList<String>();
//        java.util.List<String> full= new ArrayList<String>();
//        java.util.List<String> regular= new ArrayList<String>();
//        java.util.List<String> small= new ArrayList<String>();
//        java.util.List<String> thumb= new ArrayList<String>();
//


//        idImageList = PublicMethods.extractFromJsonforCarousel(context,PublicMethods.readFromInternalFile(context, fileName));
        idImageList = PublicMethods.extractFromJsonforCarousel(context,decrypt(PublicMethods.readFromInternalFile(context, fileName),context));
//        id = (List<String>) idImageList.get(0);
        raw = (List<String>) idImageList.get(0);
//        full = (List<String>) idImageList.get(2);
//        regular = (List<String>) idImageList.get(3);
//        small = (List<String>) idImageList.get(1);
//        thumb = (List<String>) idImageList.get(5);
//
        return raw;
    }


    public static List<String> listAssetFiles(String path, Context context) {

        List<String> str = new ArrayList<>();
        String [] list;
        try {
//            list = getAssets().list(path);
            list = context.getAssets().list(path);
            if (list.length > 0) {
                // This is a folder
                String dd ="";
                for (String file : list) {
                    // This is a file
                    // TODO: add file name to an array list
//                  TODO: some stuff
                    String f = file.substring(0,file.length()-5);

//                   dd= dd+  " case \" "+f +"\":" +
//                            "                        jsonfileName = R.string."+f+";\n" +
//                            "                        break;\n\n";
//

                        dd = dd +"<string name=\""+f+"\">"+f+".json</string>\n";



                    str.add(file);
                    Log.d(TAG, "listAssetFiles: "+file );
                }

                Log.d(TAG, "listAssetFiles:dd "+dd);
            }
        } catch (IOException e) {
            return str;
        }

        return str;
    }

}









