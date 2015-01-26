package com.vishnu.aditya.sanitationreview;

import android.app.ProgressDialog;
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

public class EmployeeSender extends AsyncTask<String, Void, Boolean> {

    String BASE_URL = "http://sanitation.net76.net/checkuser.php?key=";
    // read and write 8kB ( 8192 bytes ) blocks at once. The number is fairly arbitrary,
    // but for performance reasons it makes sense to use a multiple of 512 bytes when writing a file,
    // and preferably a multiple of the disks cluster size. 8kB is a reasonable buffer size for most purposes.
    int BUFFER_SIZE = 8192;

    private Context context;
    ProgressDialog progress;

    // The constructor of the class, used to fetch the context of the view which call's it
    public EmployeeSender(Context loginContext) {
        this.context = loginContext;
    }

    // Start login animation
    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setMessage("Verifying with server");
        progress.setCancelable(false);
        progress.show();
    }

    @Override
    protected Boolean doInBackground(String... empID) {

        // Ensure URL is of proper form
        try {
            URL url = new URL(BASE_URL+empID[0]);
            Log.i("the empId",empID[0]);

            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();

            String encoding = connection.getContentEncoding();
            if (encoding == null) encoding = "UTF-8";

            ByteArrayOutputStream outputByteByByte = new ByteArrayOutputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int len;
            try {
                // Read the inputStream using the buffer
                while ((len = inputStream.read(buffer)) != -1) {
                    // write what you get to the outputByteByByte variable
                    outputByteByByte.write(buffer, 0, len);
                }

                String serverResponse = new String(outputByteByByte.toByteArray(), encoding);
                Log.i("the server response",serverResponse);

                if (serverResponse.contains("1")) {
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
        super.onPostExecute(result);
        try{
            // Stop loading animation
            progress.dismiss();
        }
        catch(IllegalArgumentException e){
            Log.i(e.toString(),"Mobile State changed");
            return;
        }

        // In case no internet
        if(result == null){
            Toast.makeText(context,"Please ensure you have internet and try again",Toast.LENGTH_SHORT).show();
            return;
        }
        if(result == Boolean.TRUE){
            Intent postLoginIntent = new Intent();
            postLoginIntent.setClass(context,LocationFixer.class);
            context.startActivity(postLoginIntent);
        }
        else{
            Toast.makeText(context,"Sorry no volunteer corresponding to this ID exists",Toast.LENGTH_SHORT).show();
        }
    }

}