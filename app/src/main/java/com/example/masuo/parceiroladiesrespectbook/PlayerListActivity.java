package com.example.masuo.parceiroladiesrespectbook;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class PlayerListActivity extends AppCompatActivity {

    private static final String LOG = "PlayerListActivity";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        // レイアウトからリストビューを取得
        ListView listView = (ListView)findViewById(R.id.player_listview);

        // リストビューに表示する要素を設定

        // TEST
        mDbHelper = new DatabaseHelper(this);

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
        ArrayList<PlayerListItem> listItems = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                PlayerListItem item = new PlayerListItem(R.mipmap.ic_launcher, c.getString(c.getColumnIndex(PlayerContract.PlayerInformation.COL_NAME)));
                listItems.add(item);

                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayerInformation.COL_NAME)));
                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayerInformation.COL_BIRTHDAY)));
                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayerInformation.COL_HOMETOWN)));
                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayerInformation.COL_JOIN_COMMENT)));
            } while (c.moveToNext());

            PlayerListViewAdapter adapter = new PlayerListViewAdapter(this, R.layout.player_list, listItems );
            listView.setAdapter(adapter);



        }
        c.close();



    }
}
