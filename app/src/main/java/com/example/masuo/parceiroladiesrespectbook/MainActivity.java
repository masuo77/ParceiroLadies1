package com.example.masuo.parceiroladiesrespectbook;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity {

    private static final String LOG = "MainActivity";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase db;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.i(LOG, "Start2");


        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setText("On");

                Log.i(LOG, "Click");

                // TEST
                mDbHelper = new DatabaseHelper(MainActivity.this);

                Log.i(LOG, "Click2");

//                setDatabase();

                db = mDbHelper.getReadableDatabase();

//                try {
//                    mDbHelper.createEmptyDatabase();
//                } catch (IOException ioe) {
//                    throw new Error("Unable to create database");
//                }
//
//                db = mDbHelper.getReadableDatabase();

                Cursor c = db.rawQuery("SELECT * FROM Players;", null);
                if (c.moveToFirst()) {
                    do {
                        Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayerInformation.COL_JOIN_COMMENT)));
                    } while (c.moveToNext());
                }
                c.close();

            }

        });


//        try {
//            setDatabase();
//        }
//        catch (Exception e)
//        {
//            finish();
//        }


    }


    private void setDatabase() {
        mDbHelper = new DatabaseHelper(this);

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

        String[] from = {PlayerContract.PlayerInformation.COL_ID, PlayerContract.PlayerInformation.COL_NAME};

//        int[] to = {R.id.id, R.id.name};

        Log.i(LOG, from[0] + " " + from[1]);



    }


}
