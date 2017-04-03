package com.example.masuo.parceiroladiesrespectbook.ParceiroDB;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    public ParceiroDBAdapter open() {
        mDb = mDbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }


//    static String seasonMemberQuery =
//            "SELECT " +
//                    "*," + " " +
//                    "case" + " " +
//                    "when " + PlayerContract.JoiningsTable.COL_SEASON + "= %1$s then '☆'" + " " +
//                    "else ''" + " " +
//                    "end as " + PlayerContract.PlayersInfoTable.COL_NEW_MEMBER + " " +
//                    "FROM (SELECT * FROM " + PlayerContract.MEMBERS_TABLE_NAME + " " +
//                    "WHERE " + PlayerContract.MembersTable.COL_SEASON + "= %1$s)" + " " +
//                    "left join " + PlayerContract.PLAYERS_TABLE_NAME + " " +
//                    "on " + PlayerContract.PlayersTable.COL_ID + "=" + PlayerContract.MembersTable.COL_ID + " " +
//                    "left join " + PlayerContract.JOININGS_TABLE_NAME + " " +
//                    "on " + PlayerContract.JoiningsTable.COL_ID + " = " + PlayerContract.MembersTable.COL_ID + " " +
//                    "and " + PlayerContract.JoiningsTable.COL_SEASON + "= %1$s" + " " +
//                    "left join "+ PlayerContract.LEAVINGS_TABLE_NAME + " " +
//                    "on " + PlayerContract.LeavingsTable.COL_ID + "= " + PlayerContract.MembersTable.COL_ID + " " +
//                    "and " + PlayerContract.LeavingsTable.COL_SEASON + "= %1$s" + " " +
//                    "%2$s;";

//    static String seasonMemberQuery =
//            "SELECT " +
//                    "*," + " " +
//                    "case" + " " +
//                    "when " + PlayerContract.JoiningsTable.COL_SEASON + "= %1$d then '☆'" + " " +
//                    "else ''" + " " +
//                    "end as " + PlayerContract.PlayersInfoTable.COL_NEW_MEMBER + " " +
//                    "FROM (SELECT * FROM " + PlayerContract.MEMBERS_TABLE_NAME + " " +
//                    "WHERE " + PlayerContract.MembersTable.COL_SEASON + "= %1$d)" + " " +
//                    "left join " + PlayerContract.PLAYERS_TABLE_NAME + " " +
//                    "on " + PlayerContract.PlayersTable.COL_ID + "=" + PlayerContract.MembersTable.COL_ID + " " +
//                    "left join " + PlayerContract.JOININGS_TABLE_NAME + " " +
//                    "on " + PlayerContract.JoiningsTable.COL_ID + " = " + PlayerContract.MembersTable.COL_ID + " " +
//                    "left join " + PlayerContract.LEAVINGS_TABLE_NAME + " " +
//                    "on " + PlayerContract.LeavingsTable.COL_ID + "= " + PlayerContract.MembersTable.COL_ID + " " +
//                    "%2$s;";

    static String seasonMemberQuery =
            "SELECT " +
                    "*," + " " +
                    "case" + " " +
                    "when " + PlayerContract.JoiningsTable.COL_SEASON + "='%1$s' then '☆'" + " " +
                    "else ''" + " " +
                    "end as " + PlayerContract.PlayersInfoTable.COL_NEW_MEMBER + " " +
                    "FROM (SELECT * FROM " + PlayerContract.MEMBERS_TABLE_NAME + " " +
                    "WHERE " + PlayerContract.MembersTable.COL_SEASON + "='%1$s')" + " " +
                    "left join " + PlayerContract.PLAYERS_TABLE_NAME + " " +
                    "on " + PlayerContract.PlayersTable.COL_ID + "=" + PlayerContract.MembersTable.COL_ID + " " +
                    "left join " + PlayerContract.JOININGS_TABLE_NAME + " " +
                    "on " + PlayerContract.JoiningsTable.COL_ID + " = " + PlayerContract.MembersTable.COL_ID + " " +
                    "left join " + PlayerContract.LEAVINGS_TABLE_NAME + " " +
                    "on " + PlayerContract.LeavingsTable.COL_ID + "= " + PlayerContract.MembersTable.COL_ID + " " +
                    "%2$s;";

    static String where_id = "where " + PlayerContract.PlayersTable.COL_ID + "='%s'";

    public Cursor getAllPlayers(String year) {
        String q = String.format(seasonMemberQuery, year, "");

        Cursor c = mDb.rawQuery(q, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor getPlayer(String year, String id) {
        String q = String.format(seasonMemberQuery, year, String.format(where_id, id));

        Log.i(LOG, q);

        Cursor c = mDb.rawQuery(q, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor getSeasonMembers() {
        Cursor c = mDb.rawQuery("SELECT * FROM " + PlayerContract.PLAYERS_TABLE_NAME + ";", null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }


    public Cursor getAllSeasonList() {
        String q = "SELECT * FROM " + PlayerContract.SEASON_TABLE_NAME +
                " ORDER BY " + PlayerContract.SeasonTable.COL_SEASON + " DESC;";

        Log.i(LOG, q);

        Cursor c = mDb.rawQuery(q, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor getOneSeasonData(String year) {
        Cursor c = mDb.rawQuery("SELECT * FROM " + PlayerContract.SEASON_TABLE_NAME +
                " WHERE " + PlayerContract.SeasonTable.COL_SEASON + "='" + year + "';", null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }


//    SELECT
//    *
//    FROM (SELECT * FROM season_staff WHERE staff_season_year = '2016')
//    left join staff on id = staff_season_id

    static String seasonStaffQuery =
            "SELECT " +
                    "*" + " " +
                    "FROM (SELECT * FROM " + PlayerContract.STAFF_SEASON_TABLE_NAME + " " +
                    "WHERE " + PlayerContract.StaffSeasonTable.COL_SEASON + "='%1$s')" + " " +
                    "left join " + PlayerContract.STAFF_TABLE_NAME + " " +
                    "on " + PlayerContract.StaffTable.COL_ID + "=" + PlayerContract.StaffSeasonTable.COL_ID + " " +
                    "%2$s;";

    static String where_staff_id = "where " + PlayerContract.StaffTable.COL_ID + "='%s'";

    public Cursor getAllStaff(String year) {
        String q = String.format(seasonStaffQuery, year, "");

        Cursor c = mDb.rawQuery(q, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor getStaff(String year, String id) {
        String q = String.format(seasonStaffQuery, year, String.format(where_staff_id, id));

        Log.i(LOG, q);

        Cursor c = mDb.rawQuery(q, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

}
