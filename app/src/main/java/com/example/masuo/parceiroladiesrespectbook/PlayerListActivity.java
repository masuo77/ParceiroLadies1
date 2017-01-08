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

//    private DatabaseHelper mDbHelper;
//    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        // レイアウトからリストビューを取得
        ListView listView = (ListView)findViewById(R.id.player_listview);

        // リストビューに表示する要素を設定

        // TEST
//        mDbHelper = new DatabaseHelper(this, PlayerContract.DATABASE_NAME, PlayerContract.DATABASE_VERSION, PlayerContract.ASSETS_DATABASE_NAME);
        ParceiroDBAdapter parceiroDBAdapter = new ParceiroDBAdapter(this);


        Log.i(LOG, "Click2");

        parceiroDBAdapter.open();

//        Cursor c = db.rawQuery("SELECT * FROM Players;", null);
        Cursor c = parceiroDBAdapter.getAllPlayers();

        String[] strs = c.getColumnNames();
        for(int s = 0; s < strs.length; s++){
            Log.v("Column:", strs[s] + "["+c.getString(s)+"]");
        }

        ArrayList<PlayerListItem> listItems = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
//                PlayerListItem item = new PlayerListItem(R.mipmap.ic_launcher,
//                        c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NAME)),
//                        c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_COMMENT))
//                );
//
                PlayerListItem item = new PlayerListItem(R.mipmap.ic_launcher,
                        c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NAME)),
                        c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NUMBER)),
                        c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_POSITION))
                );

                listItems.add(item);

                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NAME)));
                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_BIRTHDAY)));
                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_HOMETOWN)));
                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NUMBER)));
                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NEW_MEMBER)));

                if (!c.isNull(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_COMMENT))) {
                    Log.v("Player j ", c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_COMMENT)));
                }
                if (!c.isNull(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LEAVING_COMMENT))) {
                    Log.v("Player l ", c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LEAVING_COMMENT)));
                }

//                Log.v("Player", c.getString(c.getColumnIndex("leavings.comment")));
//                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayersTable.COL_COMMENT)));
//                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.MemberInformation.COL_NUMBER)));
//                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.MemberInformation.COL_POSITION)));
//                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.MemberInformation.COL_YEAR)));
//                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.MemberInformation.COL_NOTE)));
//                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayerInformation.COL_NOTE)));
//                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayerInformation.COL_HEIGHT)));
            } while (c.moveToNext());

            // アダプタの取得した情報を渡す
            PlayerListViewAdapter adapter = new PlayerListViewAdapter(this, R.layout.player_list, listItems );

            // 情報をセットするアダプタを渡す
            // 後はListViewでスクロールなどやってくれる。
            listView.setAdapter(adapter);



        }
        c.close();



        parceiroDBAdapter.close();
    }
}
