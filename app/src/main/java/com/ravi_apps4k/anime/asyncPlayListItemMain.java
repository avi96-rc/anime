package com.ravi_apps4k.anime;
//com.iLove.cats

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

//import static sapna.choudhary.app.Main2Activity.context;
import static com.ravi_apps4k.anime.Splash.contextx;

public  class asyncPlayListItemMain extends AsyncTask<String, String, String> {



    private static final String TAG = "Main";
   // String res="";
    String str1="";
    String str2="";
    String str3="";
    String str4="";
    String str5="";

    String id1="PLaZkk9hqBD_1lQjS4IQT28t_xV5cexlUP";
    String id2="PLaZkk9hqBD_3JvR32zHOCtprQM-aczkbX";
    String id3="PLaZkk9hqBD_1x5ttyCsua6k9YxTSzBAcY";
    String id4="PLaZkk9hqBD_14UX__NlQUBUxRg1XdMFpt";
    String id5="PLaZkk9hqBD_2NzA3pg71ejJBEDeoGDcwq";

    @Override
    protected String doInBackground(String... urls) {

        // getting str1;

        str1 = Request("https://www.googleapis.com/youtube/v3/playlistItems?playlistId="+id1+"&key=AIzaSyD9uJui6kF9F2v5qaK8CIgQyuvO51T4I0o&part=snippet&fields=items/snippet/title,items/snippet/thumbnails/standard/url,items/snippet/thumbnails/maxres/url,items/snippet/thumbnails/standard/url,items/snippet/thumbnails/high/url,items/snippet/resourceId/videoId&maxResults=50");
        str2 = Request("https://www.googleapis.com/youtube/v3/playlistItems?playlistId="+id2+"&key=AIzaSyD9uJui6kF9F2v5qaK8CIgQyuvO51T4I0o&part=snippet&fields=items/snippet/title,items/snippet/thumbnails/standard/url,items/snippet/thumbnails/maxres/url,items/snippet/thumbnails/standard/url,items/snippet/thumbnails/high/url,items/snippet/resourceId/videoId&maxResults=50");
        str3 = Request("https://www.googleapis.com/youtube/v3/playlistItems?playlistId="+id3+"&key=AIzaSyD9uJui6kF9F2v5qaK8CIgQyuvO51T4I0o&part=snippet&fields=items/snippet/title,items/snippet/thumbnails/standard/url,items/snippet/thumbnails/maxres/url,items/snippet/thumbnails/standard/url,items/snippet/thumbnails/high/url,items/snippet/resourceId/videoId&maxResults=50");
        str4 = Request("https://www.googleapis.com/youtube/v3/playlistItems?playlistId="+id4+"&key=AIzaSyD9uJui6kF9F2v5qaK8CIgQyuvO51T4I0o&part=snippet&fields=items/snippet/title,items/snippet/thumbnails/standard/url,items/snippet/thumbnails/maxres/url,items/snippet/thumbnails/standard/url,items/snippet/thumbnails/high/url,items/snippet/resourceId/videoId&maxResults=50");
        str5 = Request("https://www.googleapis.com/youtube/v3/playlistItems?playlistId="+id5+"&key=AIzaSyD9uJui6kF9F2v5qaK8CIgQyuvO51T4I0o&part=snippet&fields=items/snippet/title,items/snippet/thumbnails/standard/url,items/snippet/thumbnails/maxres/url,items/snippet/thumbnails/standard/url,items/snippet/thumbnails/high/url,items/snippet/resourceId/videoId&maxResults=50");

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(TAG, "onPostExecute: "+s);


        //if (str1.equals("null")){
            writeToFile(str1,id1);
            writeToFile(str2,id2);
            writeToFile(str3,id3);
            writeToFile(str4,id4);
            writeToFile(str5,id5);
        Log.d(TAG, "onPostExecute: "+str5);
           // writeToFile("testing from async","test");
              Log.d(TAG, "writing done");
              Log.d(TAG, "str1"+str1);

       // }


//        writeToFile("ravi",id1);
//        writeToFile("ravi",id2);
//        writeToFile("ravi",id3);
//        writeToFile("ravi",id4);
//        writeToFile("ravi",id5);



    }


    private void writeToFile(String data, String fileName) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(contextx.openFileOutput(fileName+".txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
           // Toast.makeText(context,"writing complete",Toast.LENGTH_LONG).show();

        }
        catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }






    private String Request(String urll){

        try {

            URL url = new URL(urll);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                //readStream(in);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine);
                }
                bufferedReader.close();

                 return response.toString();


            } finally {
                urlConnection.disconnect();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return  "null";
    }


}