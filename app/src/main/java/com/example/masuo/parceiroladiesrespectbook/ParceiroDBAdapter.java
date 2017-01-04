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

    public Cursor getAllPlayers()
    {
//        Cursor c = mDb.rawQuery("SELECT * FROM Players;", null);
//        Cursor c = mDb.rawQuery("SELECT * FROM " + PlayerContract.PLAYERS_TABLE_NAME + ";", null);

//        String q = "SELECT * FROM member,players,leaving WHERE member.year=2016 AND member.id=players.id;";
//        String q = "SELECT * FROM member,players,leaving WHERE member_id=players.id AND member_year=2016;";

//        String q = "SELECT"
//                + " id, name, yomi, yomi_j, birthday, height, weight, blood, home, career,"
//                + " joinings.season, joinings.announced_at, joinings.comment, joinings.note,"
//                + " leavings.season, leavings.announced_at, leavings.comment, leavings.note, leavings.after_leaving,"
//                + " case"
//                + " when joinings.season='2016' then '☆'"
//                + " else ''"
//                + " end as is_new"
//                + " FROM (SELECT * FROM members WHERE members.season='2016') as season_members"
//                + " left join players on players.id = season_members.players_id"
//                + " left join joinings on joinings.players_id = season_members.players_id and joinings.season = '2016'"
//                + " left join leavings on leavings.players_id = season_members.players_id and leavings.season = '2016'"
//                + ";";

//        String q =
//                "SELECT "
//                + "*,  "
//                + "case "
//                + "when joining_season = 2016 then '☆' "
//                + "else '' "
//                + "end as is_new "
//                + "FROM (SELECT * FROM members WHERE member_season = 2016) "
//                + "left join players on id = member_player_id "
//                + "left join joinings on joining_player_id = member_player_id and joining_season = 2016 "
//                + "left join leavings on leaving_player_id = member_player_id and leaving_season = 2016 "
//                + ";";

        String q =
                "SELECT " +
                    "*," + " " +
                    "case" + " " +
                    "when " + PlayerContract.JoiningsTable.COL_SEASON + "= 2016 then '☆'" + " " +
                    "else ''" + " " +
                    "end as " + PlayerContract.PlayersInfoTable.COL_NEW_MEMBER + " " +
                "FROM (SELECT * FROM " + PlayerContract.MEMBERS_TABLE_NAME + " " +
                    "WHERE " + PlayerContract.MembersTable.COL_SEASON + "= 2016)" + " " +
                "left join " + PlayerContract.PLAYERS_TABLE_NAME + " " +
                    "on " + PlayerContract.PlayersTable.COL_ID + "=" + PlayerContract.MembersTable.COL_ID + " " +
                "left join " + PlayerContract.JOININGS_TABLE_NAME + " " +
                    "on " + PlayerContract.JoiningsTable.COL_ID + " = " + PlayerContract.MembersTable.COL_ID + " " +
                        "and " + PlayerContract.JoiningsTable.COL_SEASON + "= 2016" + " " +
                "left join "+ PlayerContract.LEAVINGS_TABLE_NAME + " " +
                    "on " + PlayerContract.LeavingsTable.COL_ID + "= " + PlayerContract.MembersTable.COL_ID + " " +
                        "and " + PlayerContract.LeavingsTable.COL_SEASON + "= 2016" + " " +
                ";";


//        SELECT
//        id, name, yomi, yomi_j, birthday, height, weight, blood, home, career,
//        joinings.season, joinings.announced_at, joinings.comment, joinings.note,
//        leavings.season, leavings.announced_at, leavings.comment, leavings.note, leavings.after_leaving,
//        case
//                when joinings.season='2016' then '☆'
//        else ''
//        end as is_new
//        FROM (SELECT * FROM members WHERE members.season='2016') as season_members
//        inner join players on players.id = season_members.players_id
//        left join joinings on joinings.players_id = season_members.players_id and joinings.season = '2016'
//        left join leavings on leavings.players_id = season_members.players_id and leavings.season = '2016'


        Cursor c = mDb.rawQuery(q, null);

//        public static final String PLAYERS_TABLE_NAME   = "players";
//
//        // 退団選手、コメント
//        public static final String LEAVING_TABLE_NAME   = "leaving";
//        // シーズンごとの選手リスト
//        public static final String MEMBER_TABLE_NAME    = "member";


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











}
