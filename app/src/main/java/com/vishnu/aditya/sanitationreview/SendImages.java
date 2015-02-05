package com.vishnu.aditya.sanitationreview;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aditya on 2/6/2015.
 */
public class SendImages extends AsyncTask<String,Void,Void>{
    @Override
    protected Void doInBackground(String... params) {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://3fd25e81.ngrok.com/saveimages.php");

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<>(2);
            nameValuePairs.add(new BasicNameValuePair("time",Long.toString(System.currentTimeMillis())));
            nameValuePairs.add(new BasicNameValuePair("image1", params[0]));
            nameValuePairs.add(new BasicNameValuePair("image2", params[1]));
            nameValuePairs.add(new BasicNameValuePair("image3", params[2]));
            nameValuePairs.add(new BasicNameValuePair("image4", params[3]));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            Log.i("image1",params[0]);
            Log.i("image2",params[1]);
            Log.i("image3",params[2]);
            Log.i("image4",params[3]);

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

            Log.i("response", EntityUtils.toString(response.getEntity()));

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        return null;
    }
}
