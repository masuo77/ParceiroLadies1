package com.example.masuo.parceiroladiesrespectbook;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.R.style.Theme_Holo_Dialog;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity
        implements  TestFragment.OnFragmentInteractionListener, SeasonListFragment.OnFragmentInteractionListener,
        PlayerListFragment.OnFragmentInteractionListener
{

    private static final String LOG = "MainActivity";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase db;

    private Button button;
    private Button buttonTest;
    private Button buttonDate;

    @Override
    public void onFragmentInteraction() {
        // TODO Auto-generated method stub
//        Toast.makeText(this, "Click in Fragment", Toast.LENGTH_SHORT).show();

//        PlayerListFragment fragment = new PlayerListFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container, fragment.newInstance("2015", ""));
//        transaction.commit();

        SeasonListFragment fragment = new SeasonListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(String year) {
        // TODO Auto-generated method stub
//        Toast.makeText(this, "Click in Fragment", Toast.LENGTH_SHORT).show();

        PlayerListFragment fragment = new PlayerListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment.newInstance(year, ""));
        transaction.addToBackStack(null);
        transaction.commit();


    }

    @Override
    public void onFragmentInteractionPlayerList(String year, int id) {
        // TODO Auto-generated method stub
//        Toast.makeText(this, "Click in Fragment", Toast.LENGTH_SHORT).show();

        PlayerInfoFragment fragment = new PlayerInfoFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment.newInstance(year, id));
        transaction.addToBackStack(null);
        transaction.commit();

    }

//    @Override
//    public void itemClicked(View v, int year)
//    {
//        Toast.makeText(this, "Click form ClickListner", Toast.LENGTH_SHORT).show();
//
//        PlayerListFragment fragment = new PlayerListFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container, fragment.newInstance("2016", ""));
//        transaction.addToBackStack(null);
//        transaction.commit();
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.i(LOG, "Start2");

        TestFragment fragment = new TestFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();


//        buttonDate = (Button)findViewById(R.id.buttonDate);
//        buttonDate.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v) {
//                Log.i(LOG, "Click Test");
//                buttonDate.setText("実行");
//
//                showDatePickerDialog(-1, "");
//            }
//        });
//
//        buttonTest = (Button)findViewById(R.id.buttonTest);
//        buttonTest.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v) {
//                buttonTest.setText("実行");
//                Log.i(LOG, "Click Test");
//                startActivity(new Intent(MainActivity.this, PlayerListRecyclerViewActivity.class));
//
//            }
//        });
//
//        button = (Button)findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                button.setText("On");
//                Log.i(LOG, "Click");
//                startActivity(new Intent(MainActivity.this, PlayerListActivity.class));
////                // TEST
////                mDbHelper = new DatabaseHelper(MainActivity.this);
////
////                Log.i(LOG, "Click2");
////
//////                setDatabase();
////
////                db = mDbHelper.getReadableDatabase();
////
//////                try {
//////                    mDbHelper.createEmptyDatabase();
//////                } catch (IOException ioe) {
//////                    throw new Error("Unable to create database");
//////                }
//////
//////                db = mDbHelper.getReadableDatabase();
////
////                Cursor c = db.rawQuery("SELECT * FROM Players;", null);
////                if (c.moveToFirst()) {
////                    do {
////                        Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayerInformation.COL_NAME)));
////                        Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayerInformation.COL_BIRTHDAY)));
////                        Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayerInformation.COL_HOMETOWN)));
////                        Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayerInformation.COL_JOIN_COMMENT)));
////                    } while (c.moveToNext());
////                }
////                c.close();
//
//            }
//
//        });


//        try {
//            setDatabase();
//        }
//        catch (Exception e)
//        {
//            finish();
//        }


    }


    private void setDatabase() {
        mDbHelper = new DatabaseHelper(this, PlayerContract.DATABASE_NAME, PlayerContract.DATABASE_VERSION, PlayerContract.ASSETS_DATABASE_NAME);

//        try {
//            mDbHelper.createEmptyDatabase();
//        } catch (IOException ioe) {
//            throw new Error("Unable to create database");
//        }
//
        Log.i(LOG, "SQLiteDatabase");

        // DBを得る
        db = mDbHelper.getReadableDatabase();

        Cursor c = null;
        try {
            c = db.query("member", null, null, null, null, null, null);
        }
        catch (Exception e)
        {
            return;
        }

        Log.i(LOG, c.toString());
        Log.i(LOG, Integer.toString(c.getCount()));
        Log.i(LOG, c.getColumnName(0) + " " + c.getColumnName(1));

        String[] from = {PlayerContract.PlayersTable.COL_ID, PlayerContract.PlayersTable.COL_NAME};

//        int[] to = {R.id.id, R.id.name};

        Log.i(LOG, from[0] + " " + from[1]);



    }

    private Context ctx = this;
    DateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
    int callerId = -1;
    //  public static final String DATE_FORMAT = "yyyy/MM/dd";
    public static final String DATE_FORMAT = "EEE, MMM d, yyyy";

    /**
     * Method used to show date picker dialog
     *
     * @param callerId
     * @param dateText
     */
    public void showDatePickerDialog(int callerId, String dateText) {
        this.callerId = callerId;
        Date date = null;

        try {
            if (dateText.equals(""))
                date = new Date();
            else
                date = dateFormatter.parse(dateText);
        } catch (Exception exp) {
            // In case of expense initializa date with new Date
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // calendar month 0-11
        int day = calendar.get(Calendar.DATE);
        // date picker initialization
//        DatePickerDialog datePicker = new DatePickerDialog(ctx, android.R.style.Theme_Holo_Dialog,new DatePickerDialog.OnDateSetListener() {
        DatePickerDialog datePicker = new DatePickerDialog(ctx,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

//                handleOnDateSet(year, month, day);
            }
        }, year, month, day);

//        datePicker.getDatePicker().setCalendarViewShown(false);
//
//        int day_id = Resources.getSystem().getIdentifier("day", "id", "android");
//        int month_id = Resources.getSystem().getIdentifier("month", "id", "android");
//        datePicker.getDatePicker().findViewById(day_id).setVisibility(View.GONE);
//        datePicker.getDatePicker().findViewById(month_id).setVisibility(View.GONE);

        datePicker.setTitle("My date picker");
        datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ok", datePicker);
        datePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Cancel button clicked
            }
        });
        datePicker.show();
    }

}
