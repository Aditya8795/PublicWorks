package com.vishnu.aditya.sanitationreview;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Aditya on 1/15/2015.
 */
public class EmployeeSender extends AsyncTask<String, Void, Boolean> {
    String url = "localhost/admin-panel-sanitation/checkDatabase.php";

    public EmployeeSender(Login login) {

    }

    @Override
    protected Boolean doInBackground(String... empID) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        
        try {
            HttpResponse response = httpclient.execute(httpget);
            if(response != null) {
                String line = "";
                InputStream inputstream = response.getEntity().getContent();
                line = convertStreamToString(inputstream);
                Log.i("Response from server",line);
                if(line == "0"){
                    return Boolean.FALSE;
                }
                else if(line == "1"){
                    return  Boolean.TRUE;
                }
            } else {
                Log.i("Error","Unable to complete your request");
            }
        } catch (ClientProtocolException e) {
            Log.i("Error","ClientProtocolException");
        } catch (IOException e) {
            Log.i("Error","IOException");
        } catch (Exception e) {
            Log.i("Error","God knows what!");
        }

        return null;
    }

    private String convertStreamToString(InputStream inputstream) {
        String line = "";
        StringBuilder total = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
        try {
            while ((line = reader.readLine()) != null) {
                total.append(line);
            }
        } catch (Exception e) {
            Log.i("Error","Converting stream to string");
        }
        return total.toString();
    }
}
