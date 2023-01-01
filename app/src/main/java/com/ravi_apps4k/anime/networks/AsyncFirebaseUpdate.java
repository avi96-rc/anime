    package com.ravi_apps4k.anime.networks;


    import android.content.Context;
    import android.os.AsyncTask;
    import android.util.Log;

    import androidx.annotation.NonNull;

    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;
    import com.ravi_apps4k.anime.PublicMethods;


    import java.io.BufferedInputStream;
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.io.OutputStreamWriter;
    import java.net.HttpURLConnection;
    import java.net.URL;

    import static com.ravi_apps4k.anime.Splash.contextx;


    public  class AsyncFirebaseUpdate extends AsyncTask<String, String, String> {

        private static final String TAG = "Main";
        private DatabaseReference mDatabase;
        String upDate = "no";


        @Override
        protected String doInBackground(String... urls) {

    //        Log.d(TAG, "doInBackground: string: "+ urls[0]);

                // get the url here...
                mDatabase = FirebaseDatabase.getInstance().getReference("/");
    //            mDatabase = FirebaseDatabase.getInstance().getReference("images");


             final DatabaseReference update = FirebaseDatabase.getInstance().getReference("/") ;
             update.child("update").addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                     Log.d("TAG", "onDataChange: update:"+ snapshot.getValue());
                     upDate = snapshot.getValue().toString();
                     Log.d("TAG", "onDataChange: isUpdate before: "+PublicMethods.isUpdateAvailable);
                     PublicMethods.isUpdateAvailable = (boolean)snapshot.getValue();
                     Log.d("TAG", "onDataChange: up: "+PublicMethods.isUpdateAvailable);
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError error) {

                 }
             });


                return upDate;
        }

        @Override
        protected void onPostExecute(String s) {

//            PublicMethods.isUpdateAvailable = true;

    //        for (int i = 0; i<2;i++){
    //            Log.d(TAG, "onPostExecute:"+category.get(i));
    //        }

//            contextx.startActivity( new Intent(contextx,
//                    MainActivity.class));
//


    //        super.onPostExecute(s);
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