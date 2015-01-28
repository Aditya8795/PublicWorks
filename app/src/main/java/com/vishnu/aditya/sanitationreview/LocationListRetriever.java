package com.vishnu.aditya.sanitationreview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    String EXTRA_LOCATION = "com.vishnu.aditya.sanitationreview.LOCATION";
    // read and write 8kB ( 8192 bytes ) blocks at once. The number is fairly arbitrary,
    // but for performance reasons it makes sense to use a multiple of 512 bytes when writing a file,
    // and preferably a multiple of the disks cluster size. 8kB is a reasonable buffer size for most purposes.
    int BUFFER_SIZE = 8192;
    public Activity activity;
    ProgressDialog progress;
    public LocationListRetriever(LocationFixer locationFixer) { this.activity = locationFixer; }

    // Start login animation
    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(activity);
        progress.setMessage("Fetching similar locations");
        progress.setCancelable(false);
        progress.show();
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
            byte[] byteBuffer = new byte[BUFFER_SIZE];
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
    protected void onPostExecute(final String[] locations){
        super.onPostExecute(locations);
        // Stop loading animation
        progress.dismiss();

        // Put the locations we fetched into the ListView
        ListView listOfLocations = (ListView)this.activity.findViewById(R.id.locationsList);
        ArrayAdapter<String> locationsAdapter = new ArrayAdapter<>(activity.getApplicationContext(), R.layout.location_textview_listviewrow, locations);
        listOfLocations.setAdapter(locationsAdapter);
        listOfLocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Send them to the next activity
                Intent intent = new Intent(activity,GPSLocation.class);
                intent.putExtra(EXTRA_LOCATION, locations[position]);
                activity.startActivity(intent);
            }
        });
    }
}
