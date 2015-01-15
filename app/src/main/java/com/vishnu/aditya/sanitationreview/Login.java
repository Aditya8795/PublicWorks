package com.vishnu.aditya.sanitationreview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class Login extends ActionBarActivity {


    String employeeID=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //customizing xml
        Button button = (Button) findViewById(R.id.button);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        int buttonWidth = width/3;
        button.setWidth(buttonWidth);
        EditText editText = (EditText) findViewById(R.id.empId);
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int etwidth = width/2;
        editText.setWidth(etwidth);
        // set a listener for the EditText
        ((EditText)this.findViewById(R.id.empId)).setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    // if the enter key was pressed, then try logging in
                    attemptLogin(v);
                    return true;
                }

                return false;
            }
        });
    }

    public void attemptLogin(View v){
        // Take the employee ID entered by the user
        EditText empId = (EditText)findViewById(R.id.empId);
        employeeID = empId.getText().toString();

        // check if user has not entered any valid string.
        if(employeeID.matches("")){
            // alert the user to enter the ID
            empId.setError("Enter Volunteer's ID");
            //Toast.makeText(this,"Please enter a Employee ID",Toast.LENGTH_SHORT).show();
            return;
        }
        Log.i("TEctcnhc","not tarted");
        // This sends the employee ID to the server for verification
        new TestAsynch().execute();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class TestAsynch extends AsyncTask<Void, Integer, String>
    {

        String baseurl = "http://5313ee71.ngrok.com/admin-panel-sanitation/checkDatabase.php/?key="+employeeID;
        String readfromwebpage=null;

        protected String doInBackground(Void...arg0) {
            URL url = null;
            Log.i("inide", "doinbackground");
            try {
                url = new URL(baseurl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            URLConnection con = null;
            try {
                con = url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream in = null;
            try {
                in = con.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[8192];
            int len = 0;
            try {
                while ((len = in.read(buf)) != -1)
                {
                    baos.write(buf, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                readfromwebpage= new String(baos.toByteArray(), encoding);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return null;
        }


        protected void onPostExecute(String result) {
        Log.i(readfromwebpage, "done");
            if(readfromwebpage.equals("1"))
            {
                Toast.makeText(getApplicationContext(),"Login Successful", Toast.LENGTH_SHORT).show();
            }
            else
            {

                Toast.makeText(getApplicationContext(),"Login UnSuccessful", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
