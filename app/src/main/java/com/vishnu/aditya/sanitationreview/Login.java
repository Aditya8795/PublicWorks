package com.vishnu.aditya.sanitationreview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Login extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //customizing xml TODO FIX THIS the notification bar is covered by the action bar
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
        this.findViewById(R.id.empId).setOnKeyListener(new View.OnKeyListener() {

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
        String employeeID = empId.getText().toString();

        // check if user has not entered any valid string.
        if(employeeID.matches("")){
            // alert the user to enter the ID
            empId.setError("Enter Volunteer's ID");
            return;
        }

        // This sends the employee ID to the server for verification
        new EmployeeSender(this).execute(employeeID);
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

}
