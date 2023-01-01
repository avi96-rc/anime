    package com.ravi_apps4k.anime.networks;


    import android.content.Context;
    import android.os.AsyncTask;
    import android.util.Base64;
    import android.util.Log;

    import androidx.annotation.NonNull;

    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;


    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.BufferedInputStream;
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.io.OutputStreamWriter;
    import java.io.UnsupportedEncodingException;
    import java.net.HttpURLConnection;
    import java.net.URL;

    import static com.ravi_apps4k.anime.Splash.contextx;



    public  class AsyncFirebaseFirst extends AsyncTask<String, String, String> {

        private static final String TAG = "Main";
        private DatabaseReference mDatabase;
        int i = 0;






        @Override
        protected String doInBackground(String... urls) {

    //        Log.d(TAG, "doInBackground: string: "+ urls[0]);

                // get the url here...
                mDatabase = FirebaseDatabase.getInstance().getReference("/");
    //            mDatabase = FirebaseDatabase.getInstance().getReference("images");

                final int[] counter = new int[22];




                 Log.d("TAG", "doInBackground: got it");
                 // getting all the category from firebase
                 mDatabase.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot) {




                         /// this is root
                         Log.d(TAG, "onDataChange: snap: "+snapshot.getKey());
                         Log.d(TAG, "onDataChange: snap: "+snapshot.getChildrenCount());



                         for (DataSnapshot postSnapshot: snapshot.getChildren()) {

                             JSONArray jsonArray = new JSONArray();
                             JSONObject jsonObject = new JSONObject();
                             // this is category
    //                    Log.d(TAG, "onDataChange: category:"+postSnapshot.getKey());
    //                    JSONObject jsonObjectcat = new JSONObject();
    //                    category.add(postSnapshot.getKey().toString());



                             for(DataSnapshot postpostSnapshot: postSnapshot.getChildren()){
    //                        this is child
                                 JSONObject jsonObjectChild = new JSONObject();
                                 try {
                                     jsonObjectChild.put("url",postpostSnapshot.getValue());
                                     jsonObjectChild.put("id",postpostSnapshot.getKey());
    //                            Log.d(TAG, "onDataChange: jsonchild: "+jsonObjectChild.toString());

                                 } catch (JSONException e) {
                                     e.printStackTrace();
                                 }

                                 jsonArray.put(jsonObjectChild);
                             }




                             try {
                                 jsonObject.put("main",jsonArray);
                                 if (postSnapshot.getKey().equals("rolls royce")){
                                     encrypt(jsonObject.toString(),contextx,"rolls_royce"+".json");
//                                     writeToFile(jsonObject.toString(),"rolls_royce"+".json",contextx);
                                     Log.d("TAG", "onDataChange: added a cat");
                                     i++;
                                 }else {
                                     i++;
                                     encrypt(jsonObject.toString(),contextx,postSnapshot.getKey()+".json");
//                                     writeToFile("",postSnapshot.getKey()+".json",contextx);
//
//                                     writeToFile(jsonObject.toString(),postSnapshot.getKey()+".json",contextx);
                                 }

                             } catch (JSONException e) {
                                 e.printStackTrace();
                             }

                         }




                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {
                         Log.d("TAG", "onCancelled: "+error.toString());
                     }
                 });






            Log.d("TAG", "doInBackground: im ravi");

            // set the ArraylIst for category


            // now getting all the images from every category



                return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("TAG", "onPostExecute: done executing");
            Log.d("TAG", "onCreate: app started from async");
            if (2 == 4){
                Log.d("TAG", "onDataChange: postexecute");

            }

            // problem here .....
//            contextx.startActivity( new Intent(contextx,
//                    MainActivity.class));
//


            super.onPostExecute(s);
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