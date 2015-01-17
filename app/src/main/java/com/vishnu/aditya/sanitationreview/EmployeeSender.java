package com.vishnu.aditya.sanitationreview;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * Created by Aditya on 1/15/2015.*vishnu
 */
public class EmployeeSender extends AsyncTask<String, Void, Boolean> {

    String url = "http://5313ee71.ngrok.com/admin-panel-sanitation/checkDatabase.php/?key=";
    private Context context;

    public EmployeeSender(Context loginContext) {
        this.context = loginContext;
    }

    @Override
    protected Boolean doInBackground(String... empID) {

        // Ensure URL is of proper form
        try {
            URL tested_url = new URL(url+empID[0]);
            Log.i("the empId",empID[0]);

            URLConnection connection = tested_url.openConnection();
            InputStream inputStream = connection.getInputStream();

            String encoding = connection.getContentEncoding();
            if (encoding == null) encoding = "UTF-8";
            else encoding = encoding;

            ByteArrayOutputStream outputByteByByte = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int len = 0;
            try {
                // Read the inputStream using the buffer
                while ((len = inputStream.read(buffer)) != -1) {
                    // write what you get to the outputByteByByte variable
                    outputByteByByte.write(buffer, 0, len);
                }

                String serverResponse = new String(outputByteByByte.toByteArray(), encoding);
                Log.i("the server response",serverResponse);

                if (serverResponse.equals("1")) {
                    return Boolean.TRUE;
                } else {
                    return Boolean.FALSE;
                }

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

        return null;

    }

    @Override
    protected void onPostExecute(Boolean result){
        if(result == Boolean.TRUE){
            Intent postLoginIntent = new Intent();
            postLoginIntent.setClass(context,locationFixer.class);
            context.startActivity(postLoginIntent);
        }
        else{
            Toast.makeText(context,"Sorry no volunteer corresponding to this ID exists",Toast.LENGTH_SHORT).show();
        }
    }

}