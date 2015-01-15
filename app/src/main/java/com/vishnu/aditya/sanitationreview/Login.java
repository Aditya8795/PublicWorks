package com.vishnu.aditya.sanitationreview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // set a listener for the EditText
        ((EditText)this.findViewById(R.id.empId)).setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    // if the enter key was pressed, then try logging in
                    //vishnu eeeeeee
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
        if(employeeID.trim().length() == 0){
            // alert the user to enter the ID
            Toast.makeText(this,"Please enter a Employee ID",Toast.LENGTH_SHORT).show();
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
