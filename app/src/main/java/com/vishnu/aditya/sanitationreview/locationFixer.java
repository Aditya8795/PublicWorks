package com.vishnu.aditya.sanitationreview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class LocationFixer extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_fixer);

        // Set a onclick listener for the list of locations
        this.findViewById(R.id.locationsList).setOnClickListener(new  View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent postLocationList = new Intent();
                postLocationList.setClass(v.getContext(), GPSlocation.class);
                v.getContext().startActivity(postLocationList);
            }
        });
        
        // set a listener for the EditText
        this.findViewById(R.id.approximateLocation).setOnItemClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.i("FIRST", "yes");
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    // if the enter key was pressed, then try logging in
                    fetchLocationsList(v);
                    Log.i("Returns", "True");
                    return;
                }

                return;
            }
        });

        // enables the activity icon as a 'home' button. required if "android:targetSdkVersion" > 14
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location_fixer, menu);
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

    public void fetchLocationsList(View v){
        EditText approxLocation = (EditText)findViewById(R.id.approximateLocation);

        // check if user has not entered any valid string.
        if(approxLocation.getText().toString().matches("")){
            // alert the user to enter the ID
            approxLocation.setError("Enter your location");
            return;
        }

        // This sends the location the user entered to the server for the list of similar locations
        new LocationListRetriever(this).execute(approxLocation.getText().toString());
    }
}
