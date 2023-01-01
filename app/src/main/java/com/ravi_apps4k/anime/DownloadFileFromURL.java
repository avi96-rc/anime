package com.ravi_apps4k.anime;


//call this class for file Download :=
//        new DownloadFileFromURL().execute(file_url);


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import static android.content.ContentValues.TAG;
import static com.ravi_apps4k.anime.PublicMethods.folder;
import static com.ravi_apps4k.anime.PublicMethods.savefittedImage;
import static com.ravi_apps4k.anime.ShowPagerActivity.*;

/**
 * Background Async Task to download file
 * */
class DownloadFileFromURL extends AsyncTask<String, String, String> {


    String fileName;
    Boolean quickWall;
    Boolean setAs;
    Boolean share;
    Bitmap Gbitmap;
    String quality;


    /**
     * Before starting background thread
     * Show Progress Bar Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       // showDialog(progress_bar_type);
        showLoading();

    }

    /**
     * Downloading file in background thread
     * */
    @Override
    protected String doInBackground(String... f_url) {
        int count;
         fileName = f_url[1];
        Log.d(TAG, "doInBackground: f_url[1]: "+fileName);
         quickWall = Boolean.parseBoolean(f_url[2]);
         setAs = Boolean.parseBoolean(f_url[3]);
         share = Boolean.parseBoolean(f_url[4]);
        quality = f_url[5];

        Log.d(TAG, "doInBackground: quality: "+ quality);


        if (quality.equals("4k")){
            loadingtext.setText("");
            try {

                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file
                OutputStream output = new FileOutputStream(folder+"/"+fileName);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress(""+(int)((total*100)/lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

        }else if(quality.equals("4kfit")){

            Log.d(TAG, "doInBackground: bitmap");
            loadingtext.setText("resizing\nImage");
            try {
                URL url = new URL(f_url[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmappp = BitmapFactory.decodeStream(input);
                int height = PublicMethods.getResolution(MainActivity.context)[0];
                int width = PublicMethods.getResolution(MainActivity.context)[1];
                Gbitmap = Bitmap.createScaledBitmap(myBitmappp,width,height,true);
                Log.d(TAG, "doInBackground: mybitmappp: "+myBitmappp);
                Log.d(TAG, "doInBackground: Gbitmap: "+Gbitmap);
//            return myBitmap;
            } catch (IOException e) {
                // Log exception
                Log.e("Error: ", e.getMessage());

                return null;
            }


        }else if (quality.equals("fullhd")){


            Log.d(TAG, "doInBackground: full hd: ");


        }


//         if (setAs){
//             loadingtext.setText("");
//             Log.d(TAG, "doInBackground: quick wall :true");
//             try {
//
//                 URL url = new URL(f_url[0]);
//                 URLConnection conection = url.openConnection();
//                 conection.connect();
//                 // getting file length
//                 int lenghtOfFile = conection.getContentLength();
//
//                 // input stream to read file - with 8k buffer
//                 InputStream input = new BufferedInputStream(url.openStream(), 8192);
//
//                 // Output stream to write file
//                 OutputStream output = new FileOutputStream(folder+"/"+fileName);
//
//                 byte data[] = new byte[1024];
//
//                 long total = 0;
//
//                 while ((count = input.read(data)) != -1) {
//                     total += count;
//                     // publishing the progress....
//                     // After this onProgressUpdate will be called
//                     publishProgress(""+(int)((total*100)/lenghtOfFile));
//
//                     // writing data to file
//                     output.write(data, 0, count);
//                 }
//
//                 // flushing output
//                 output.flush();
//
//                 // closing streams
//                 output.close();
//                 input.close();
//
//             } catch (Exception e) {
//                 Log.e("Error: ", e.getMessage());
//             }
//
//         }else {
//             Log.d(TAG, "doInBackground: bitmap");
//             loadingtext.setText("resizing\nImage");
//             try {
//                 URL url = new URL(f_url[0]);
//                 HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                 connection.setDoInput(true);
//                 connection.connect();
//                 InputStream input = connection.getInputStream();
//                 Bitmap myBitmappp = BitmapFactory.decodeStream(input);
//                 int height = PublicMethods.getResolution(MainActivity.context)[0];
//                 int width = PublicMethods.getResolution(MainActivity.context)[1];
//                 Gbitmap = Bitmap.createScaledBitmap(myBitmappp,width,height,true);
//                 Log.d(TAG, "doInBackground: mybitmappp: "+myBitmappp);
//                 Log.d(TAG, "doInBackground: Gbitmap: "+Gbitmap);
////            return myBitmap;
//             } catch (IOException e) {
//                 // Log exception
//                 Log.e("Error: ", e.getMessage());
//
//                 return null;
//             }
//         }



        return fileName;
    }

    /**
     * Updating progress bar
     * */
    protected void onProgressUpdate(String... progress) {
        // setting progress percentage
        progressBar.setProgress(Integer.parseInt(progress[0]));
        loadingtext.setText(progress[0]+"%");


    }

    /**
     * After completing background task
     * Dismiss the progress dialog
     * **/
    @Override
    protected void onPostExecute(String fileName) {
        Log.i("TAG", "onPostExecute: ");
        // dismiss the dialog after the file was downloaded
        myDialog.dismiss();
//        dismissDialog(progress_bar_type);

        // Displaying downloaded image into image view
        // Reading image path from sdcard
        // setting downloaded into image view
        //my_image.setImageDrawable(Drawable.createFromPath(imagePath));
        Log.d(TAG, "onPostExecute: fileName: "+fileName);
        Log.d(TAG, "onPostExecute: global bitmap: "+Gbitmap);

        if (!quality.equals("4k")){
            savefittedImage(Gbitmap,fileName);
        }
        PublicMethods.scanFile(MainActivity.context,folder+"/"+fileName,quickWall,setAs,share);
        Toast.makeText(MainActivity.context, "Image saved to "+folder,Toast.LENGTH_LONG).show();




    }

}
