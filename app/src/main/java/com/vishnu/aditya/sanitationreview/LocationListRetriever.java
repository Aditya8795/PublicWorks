package com.vishnu.aditya.sanitationreview;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class LocationListRetriever extends AsyncTask<String,Void,String[]>{

    String BASE_URL = "http://5313ee71.ngrok.com/admin-panel-sanitation/getLocations.php/?key=";
    public Activity activity;

    public LocationListRetriever(LocationFixer locationFixer) {
        this.activity = locationFixer;
    }

    @Override
    protected String[] doInBackground(String... approxLocation) {
        // Ensure URL is of proper form
        try {
            URL url = new URL(BASE_URL+approxLocation[0]);
            Log.i("the approx location", approxLocation[0]);

            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();

            String encoding = connection.getContentEncoding();
            if (encoding == null) encoding = "UTF-8";

            ByteArrayOutputStream outputByteByByte = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int len;
            try {
                // Read the inputStream using the buffer
                while ((len = inputStream.read(buffer)) != -1) {
                    // write what you get to the outputByteByByte variable
                    outputByteByByte.write(buffer, 0, len);
                }

                String serverResponse = new String(outputByteByByte.toByteArray(), encoding);
                Log.i("the server response",serverResponse);

                return serverResponse.split("#");

            } catch (IOException e) {
                Log.i("IOException", "buffer to outputByteByByte");
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            Log.i("MalformedURLException", "URL not in proper format");
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("IOException", "connection with server");
            e.printStackTrace();
        }

        return new String[0];
    }

    @Override
    protected void onPostExecute(String[] result){
        Log.i(result[1],result[3]);

        ListView list = (ListView)this.activity.findViewById(R.id.locationsList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity.getApplicationContext(), R.layout.custom_textview, result);
        list.setAdapter(adapter);

    }
}
