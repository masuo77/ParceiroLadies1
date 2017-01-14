package com.example.masuo.parceiroladiesrespectbook;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Masuo on 2016/12/24.
 */

public class ParceiroDBAdapter {
    private static final String LOG = "ParceiroDBAdapter";

    private final Context mContext;
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;


    public ParceiroDBAdapter(Context context) {

        mContext = context;

        mDbHelper = new DatabaseHelper(mContext, PlayerContract.DATABASE_NAME, PlayerContract.DATABASE_VERSION, PlayerContract.ASSETS_DATABASE_NAME);

    }

    public ParceiroDBAdapter open()
    {
        mDb = mDbHelper.getReadableDatabase();
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public Cursor getAllPlayers(String year)
    {
        String q =
                "SELECT " +
                    "*," + " " +
                    "case" + " " +
                    "when " + PlayerContract.JoiningsTable.COL_SEASON + "=" + year + " " + "then '☆'" + " " +
                    "else ''" + " " +
                    "end as " + PlayerContract.PlayersInfoTable.COL_NEW_MEMBER + " " +
                "FROM (SELECT * FROM " + PlayerContract.MEMBERS_TABLE_NAME + " " +
                    "WHERE " + PlayerContract.MembersTable.COL_SEASON + "=" + year + ")" + " " +
                "left join " + PlayerContract.PLAYERS_TABLE_NAME + " " +
                    "on " + PlayerContract.PlayersTable.COL_ID + "=" + PlayerContract.MembersTable.COL_ID + " " +
                "left join " + PlayerContract.JOININGS_TABLE_NAME + " " +
                    "on " + PlayerContract.JoiningsTable.COL_ID + " = " + PlayerContract.MembersTable.COL_ID + " " +
                        "and " + PlayerContract.JoiningsTable.COL_SEASON + "=" + year + " " +
                "left join "+ PlayerContract.LEAVINGS_TABLE_NAME + " " +
                    "on " + PlayerContract.LeavingsTable.COL_ID + "= " + PlayerContract.MembersTable.COL_ID + " " +
                        "and " + PlayerContract.LeavingsTable.COL_SEASON + "= " + year + " " +
                ";";


        Cursor c = mDb.rawQuery(q, null);

        if (c != null)
        {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor getSeasonMembers()
    {
        Cursor c = mDb.rawQuery("SELECT * FROM " + PlayerContract.PLAYERS_TABLE_NAME + ";", null);

        if (c != null)
        {
            c.moveToFirst();
        }

        return c;
    }


    public Cursor getSeasonList()
    {
        Cursor c = mDb.rawQuery("SELECT * FROM " + PlayerContract.SEASON_TABLE_NAME + ";", null);

        if (c != null)
        {
            c.moveToFirst();
        }

        return c;



    }










}
