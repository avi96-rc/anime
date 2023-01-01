package com.ravi_apps4k.anime;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ravi_apps4k.anime.networks.AsyncFirebaseFirst;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;
import static com.ravi_apps4k.anime.PublicMethods.*;

public class Splash extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT=300;
    Dialog myDialog;
    Intent i;
    public static Context contextx;
    int resHeight;
    int resWidht;
    public static ImageView imageView;
    Random randomGenerator;
    Boolean isFirst = true;


    public static String readFromInternalFile(String filename, Context context) {

        String ret = "nothing";

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

        Log.d(TAG, "readFromInternalFile: favouriteListfromJsonToJson "+ret);
        return ret;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        contextx = this;
        resHeight = PublicMethods.getResolution(contextx)[0];
        resWidht = PublicMethods.getResolution(contextx)[1];
         imageView= findViewById(R.id.splash_imageview);
        imageView.setMaxHeight(resHeight);
        imageView.setMaxWidth(resWidht);

        new AsyncFirebaseFirst().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"hello");

        // check if is this the first time app start
//        if (checkFirst()){
//            isFirst = false;
//            Log.d("TAG", "onCreate: first time");
//            // call the asyc class to get data firebase
//            new AsyncFirebaseFirst().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"hello");
//        }
//
//        else {
//
//// update every 10 days
//                new AsyncFirebaseUpdate().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"hello");
//
//            Log.d("TAG", "onCreate: app is not opened first time");
//            // start activity  from here
////            if (isNetworkConnected()){
////                Log.d("TAG", "onCreate: app started from splash");
////                i=new Intent(this,
////                        MainActivity.class);
//////                startActivity(i);
////                //Intent is used to switch from one activity to another.
////            }
////            else {
////                ShowPopup();
////
////            }
//
//        }


//        addNames();
//        loadData(this);

//        Log.d(TAG, "onCreate: size of favouriteListfromJson: before  "+favouriteListfromJson.size());
        loadFavouritesFromJson(this);
//        Log.d(TAG, "onCreate: size of favouriteListfromJson: after "+favouriteListfromJson.size());




        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myDialog = new Dialog(this);
        myDialog.setCanceledOnTouchOutside(false);



        // runnig a timeer

        final List<String > cat = PublicMethods.listAssetFiles("json",this);
        final ArrayList<String > categoryy = new ArrayList<>();


        for(String str: cat){
            Log.d("TAG", "run: cat "+str);
            categoryy.add(str);
        }


        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d("TAG", "run: timer running");

              String[] list  =  getFilesDir().list();
                ArrayList<String> allList = new ArrayList<>();
                for (String str: list) {
                    allList.add(str);
                    Log.d("TAG", "run: str: "+str);
                }
                Log.d(TAG, "run: timer running: is same-- contains "+allList.containsAll(categoryy));
                // all json is downloaded
                if (allList.containsAll(categoryy)){
                    i=new Intent(contextx,
                            MainActivity.class);
                    startActivity(i);
                   t.cancel();
                   finish();
                }


            }
        }, 0, 5*1000);


//        final Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Log.d("TAG", "run: update timer running");
//                Log.d("TAG", "run: isUpdate-- "+isUpdateAvailable.toString());
////                check if update is available
//
//                if (isUpdateAvailable){
//                    Log.d("TAG", "run: update available ");
//                }
//
//            }
//        }, 20*000, 5*1000);


//
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                i=new Intent(Splash.this,
//                        MainActivity.class);
////                i=new Intent(Splash.this,
////                        MainActivity.class);
//                //Intent is used to switch from one activity to another.
//
//                if (isNetworkConnected()){
//                  //d  startActivity(i);
////                    finish();
//                }
//                else {
//                    ShowPopup();
//
//                }
//
//
//                //invoke the SecondActivity.
//
//                //the current activity will get finished.
//            }
//        }, SPLASH_SCREEN_TIME_OUT);
    }



    private boolean checkFirst() {
        if (readFromInternalFile("start.txt",this).equals("nothing")) {
            // okay this is first time. lets create a file that says no
            PublicMethods.writeToFile("no","start.txt",contextx);
            return true;
        }

            return false;
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();

    }



    public void retry(){

        if (isNetworkConnected()){
            startActivity(i);
            finish();
        }
        else {
            ShowPopup();
        }

    }


    public void ShowPopup() {

        Button retry;
        Button exit;

        myDialog.setContentView(R.layout.custompopup);
        retry =(Button)myDialog.findViewById(R.id.retry);
        exit =(Button)myDialog.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                finish();
            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkConnected()){
//                    loadData(contextx);
                    myDialog.dismiss();
//                    finish();
                } else {
                    Toast.makeText(Splash.this,"no internet access", Toast.LENGTH_LONG).show();
                }

            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }



    private String readFromFile(String filename) {

        String ret = "nothing";

        try {
            InputStream inputStream = contextx.openFileInput(filename+".txt");

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

        return ret;
    }



    private void writeToFile(String data, String fileName) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(contextx.openFileOutput(fileName+".txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Log.d("TAG", "writeToFile: writing default valie to file done!");
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }





}

