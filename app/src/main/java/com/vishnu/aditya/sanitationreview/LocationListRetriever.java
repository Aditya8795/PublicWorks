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

    String BASE_URL = "http://sanitation.net76.net/autocomplete.php?key=";
    public Activity activity;

    public LocationListRetriever(LocationFixer locationFixer) {
        this.activity = locationFixer;
    }

    @Override
    protected String[] doInBackground(String... approxLocation) {
        try {
            // Ensure URL is of proper form
            URL url = new URL(BASE_URL+approxLocation[0]);
            Log.i("the approx location", approxLocation[0]);

            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();

            String encoding = connection.getContentEncoding();
            if (encoding == null) encoding = "UTF-8";

            ByteArrayOutputStream outputByteByByte = new ByteArrayOutputStream();
            byte[] byteBuffer = new byte[8192];
            int len;
            try {
                // Read the inputStream using the buffer
                while ((len = inputStream.read(byteBuffer)) != -1) {
                    // write what you get to the outputByteByByte variable
                    outputByteByByte.write(byteBuffer, 0, len);
                    Log.i("buffer", byteBuffer.toString());

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
        ListView listOfLocations = (ListView)this.activity.findViewById(R.id.locationsList);
        ArrayAdapter<String> locationsAdapter = new ArrayAdapter<>(activity.getApplicationContext(), R.layout.custom_textview, result);
        listOfLocations.setAdapter(locationsAdapter);
    }
}
