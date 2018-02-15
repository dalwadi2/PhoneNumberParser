package com.apptechasia.phonenumberparser;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String swissNumberStr = "+919998457472";
                        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                        try {
                            Phonenumber.PhoneNumber swissNumberProto = phoneUtil.parse(swissNumberStr, "US");
                            Log.w(TAG, "onCreate: " + swissNumberProto.toString());
                            Log.w(TAG, "getExtension: " + swissNumberProto.getExtension());
                            Log.w(TAG, "getRawInput: " + swissNumberProto.getRawInput());
                            Log.w(TAG, "getCountryCode: " + swissNumberProto.getCountryCode());
                            Log.w(TAG, "getNationalNumber: " + swissNumberProto.getNationalNumber());
                            Log.w(TAG, "getNumberOfLeadingZeros: " + swissNumberProto.getNumberOfLeadingZeros());
                            Log.w(TAG, "getPreferredDomesticCarrierCode: " + swissNumberProto.getPreferredDomesticCarrierCode());
                            Log.w(TAG, "getCountryCodeSource: " + swissNumberProto.getCountryCodeSource().name());
                            boolean isValid = phoneUtil.isValidNumber(swissNumberProto); // returns true
                            Log.w(TAG, "number Valid ?: " + isValid);
                            Log.e(TAG, "For more Info Go here: https://github.com/googlei18n/libphonenumber");

                        } catch (NumberParseException e) {
                            System.err.println("NumberParseException was thrown: " + e.toString());
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
