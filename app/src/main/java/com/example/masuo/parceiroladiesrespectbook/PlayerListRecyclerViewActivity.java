package com.example.masuo.parceiroladiesrespectbook;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;

public class PlayerListRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list_recycler_view);

        // TEST
//        mDbHelper = new DatabaseHelper(this, PlayerContract.DATABASE_NAME, PlayerContract.DATABASE_VERSION, PlayerContract.ASSETS_DATABASE_NAME);
        ParceiroDBAdapter parceiroDBAdapter = new ParceiroDBAdapter(this);

        parceiroDBAdapter.open();

        Cursor c = parceiroDBAdapter.getAllPlayers();

//        String[] strs = c.getColumnNames();
//        for(int s = 0; s < strs.length; s++){
//            Log.v("Column:", strs[s] + "["+c.getString(s)+"]");
//        }

        ArrayList<PlayerListItem> listItems = new ArrayList<>();

        final List<String> nameList = new ArrayList<>();


        if (c.moveToFirst()) {
            do {
                PlayerListItem item = new PlayerListItem(R.mipmap.ic_launcher,
                        c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NAME)),
                        c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NUMBER)),
                        c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_POSITION))
                );

                List<String> infoList = new ArrayList<>();

                if (!TextUtils.isEmpty(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NOTE)))) {
                    infoList.add(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NOTE)));
                }
                if (!TextUtils.isEmpty(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_COMMENT)))) {
                    infoList.add("新加入");
                }
                if (!TextUtils.isEmpty(c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LEAVING_COMMENT)))) {
                    infoList.add("退団");
                }

                int i = 0;
                for (String s:infoList
                     ) {
                    if (!TextUtils.isEmpty(s))
                    {
                        switch (i) {
                            case 0 :
                                item.setNote(s);
                                break;
                            case 1 :
                                item.setJoin(s);
                                break;
                            case 2:
                            default:
                                item.setLeaving(s);
                                break;
                        }
                        i++;
                    }
                }


                listItems.add(item);

                nameList.add(item.getName());

//                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NAME)));
//                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_BIRTHDAY)));
//                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_HOMETOWN)));
//                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NUMBER)));
//                Log.v("Player", c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_NEW_MEMBER)));
//
//                if (!c.isNull(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_COMMENT))) {
//                    Log.v("Player j ", c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_JOINING_COMMENT)));
//                }
//                if (!c.isNull(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LEAVING_COMMENT))) {
//                    Log.v("Player l ", c.getString(c.getColumnIndex(PlayerContract.PlayersInfoTable.COL_LEAVING_COMMENT)));
//                }

            } while (c.moveToNext());

            // アダプタの取得した情報を渡す
//            PlayerListViewAdapter adapter = new PlayerListViewAdapter(this, R.layout.player_list, listItems );

            // 情報をセットするアダプタを渡す
            // 後はListViewでスクロールなどやってくれる。
//            listView.setAdapter(adapter);

        }
        c.close();
        parceiroDBAdapter.close();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this));

        RecyclerAdapter adapter = new RecyclerAdapter(PlayerListRecyclerViewActivity.this, listItems);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerAdapter.onItemClickListener() {
            @Override
            public void onClick(View view, int position, String name) {
                Toast.makeText(PlayerListRecyclerViewActivity.this, Integer.toString(position) + " " + name, Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnImageButtonInfoClickListener(new RecyclerAdapter.onImageButtonInfoClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(PlayerListRecyclerViewActivity.this, "Click " + Integer.toString(position), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
