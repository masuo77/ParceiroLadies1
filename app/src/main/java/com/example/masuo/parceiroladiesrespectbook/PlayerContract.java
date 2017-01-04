package com.example.masuo.parceiroladiesrespectbook;

import android.provider.BaseColumns;

/**
 * Created by Masuo on 2016/07/31.
 */
public class PlayerContract {

    public static final  int    DATABASE_VERSION   = 3;
    public static final  String ASSETS_DATABASE_NAME      = "ParceiroLadies.sqlite";
    public static final  String DATABASE_NAME      = "ParceiroLadies.db";
    private static final String TEXT_TYPE          = " TEXT";
    private static final String INTEGER_TYPE       = " INTEGER";
    private static final String PRIMARY_TYPE       = " PRIMARY KEY AUTOINCREMENT";
    private static final String COMMA_SEP          = ",";

    // テーブル名
    // 選手リスト
    public static final String PLAYERS_TABLE_NAME   = "players";
    // 入団選手、コメント
    public static final String JOININGS_TABLE_NAME  = "joinings";
    // 退団選手、コメント
    public static final String LEAVINGS_TABLE_NAME  = "leavings";
    // シーズンごとの選手リスト
    public static final String MEMBERS_TABLE_NAME   = "members";
    // シーズンごとの成績
    public static final String RANK_TABLE_NAME      = "rank";

    // 新加入マーク
    public static final String NEW_MEMBER_MARK     = "☆";

    // コントラクト・クラスを偶然実体化することからいくつかのことを防ぐ
    // 空コンストラクタが与えられる。
    public PlayerContract() {
    }

    // 選手
    // id name yomi yomi_j birthday height weight blood home career

    public static abstract class PlayersTable implements BaseColumns {
        public static final String COL_ID = "id";
        public static final String COL_NAME = "name";
        public static final String COL_YOMI = "yomi";
        public static final String COL_YOMI_J = "yomi_j";
        public static final String COL_BIRTHDAY = "birthday";
        public static final String COL_HEIGHT = "height";
        public static final String COL_WEIGHT = "weight";
        public static final String COL_BLOOD = "blood";
        public static final String COL_HOMETOWN = "home";
        public static final String COL_CAREER = "career";
    }

    //テーブル生成定義
    public static final String CREATE_PLAYERS_TABLE = "CREATE TABLE " +
            PLAYERS_TABLE_NAME + " (" +
            PlayersTable._ID                + INTEGER_TYPE + PRIMARY_TYPE + COMMA_SEP +
            PlayersTable.COL_ID             + INTEGER_TYPE  + COMMA_SEP +
            PlayersTable.COL_NAME           + TEXT_TYPE     + COMMA_SEP +
            PlayersTable.COL_YOMI           + TEXT_TYPE     + COMMA_SEP +
            PlayersTable.COL_YOMI_J         + TEXT_TYPE     + COMMA_SEP +
            PlayersTable.COL_BIRTHDAY       + TEXT_TYPE     + COMMA_SEP +
            PlayersTable.COL_HEIGHT         + INTEGER_TYPE  + COMMA_SEP +
            PlayersTable.COL_WEIGHT         + INTEGER_TYPE  + COMMA_SEP +
            PlayersTable.COL_BLOOD          + TEXT_TYPE     + COMMA_SEP +
            PlayersTable.COL_HOMETOWN       + TEXT_TYPE     + COMMA_SEP +
            PlayersTable.COL_CAREER         + TEXT_TYPE     + COMMA_SEP +
            " )";


    // 年度別
    // member_player_id	member_player_name	member_season	member_season_number	member_season_position	member_season_note

    public static abstract class MembersTable implements BaseColumns {
        public static final String COL_ID = "member_player_id";
        public static final String COL_NAME = "member_player_name";
        public static final String COL_SEASON = "member_season";
        public static final String COL_NUMBER = "member_season_number";
        public static final String COL_POSITION = "member_season_position";
        public static final String COL_NOTE = "member_season_note";
    }

    //テーブル生成定義
    public static final String CREATE_MEMBERS_TABLE = "CREATE TABLE " +
            MEMBERS_TABLE_NAME + " (" +
            MembersTable._ID          + INTEGER_TYPE + PRIMARY_TYPE + COMMA_SEP +
            MembersTable.COL_ID       + INTEGER_TYPE  + COMMA_SEP +
            MembersTable.COL_NAME     + TEXT_TYPE     + COMMA_SEP +
            MembersTable.COL_SEASON   + INTEGER_TYPE  + COMMA_SEP +
            MembersTable.COL_NUMBER   + INTEGER_TYPE  + COMMA_SEP +
            MembersTable.COL_POSITION + TEXT_TYPE     + COMMA_SEP +
            MembersTable.COL_NOTE     + TEXT_TYPE     + COMMA_SEP +
            " )";


    // 入団
    // joining_player_id	joining_player_name	joining_season	joining_announced_at	joining_comment	joining_note

    public static abstract class JoiningsTable implements BaseColumns {
        public static final String COL_ID = "joining_player_id";
        public static final String COL_NAME = "joining_player_name";
        public static final String COL_SEASON = "joining_season";
        public static final String COL_ANNOUNCED_AT = "joining_announced_at";
        public static final String COL_COMMENT = "joining_comment";
        public static final String COL_NOTE = "joining_note";
    }

    //テーブル生成定義
    public static final String CREATE_JOININGS_TABLE = "CREATE TABLE " +
            JOININGS_TABLE_NAME + " (" +
            JoiningsTable._ID          + INTEGER_TYPE + PRIMARY_TYPE + COMMA_SEP +
            JoiningsTable.COL_ID       + INTEGER_TYPE  + COMMA_SEP +
            JoiningsTable.COL_NAME     + TEXT_TYPE     + COMMA_SEP +
            JoiningsTable.COL_SEASON   + INTEGER_TYPE  + COMMA_SEP +
            JoiningsTable.COL_ANNOUNCED_AT + TEXT_TYPE     + COMMA_SEP +
            JoiningsTable.COL_COMMENT  + TEXT_TYPE     + COMMA_SEP +
            JoiningsTable.COL_NOTE     + TEXT_TYPE     + COMMA_SEP +
            " )";

    // 退団
    // leaving_player_id	leaving_player_name	leaving_season	leaving_announced_at	leaving_comment	leaving_note	after_leaving

    public static abstract class LeavingsTable implements BaseColumns {
        public static final String COL_ID = "leaving_player_id";
        public static final String COL_NAME = "leaving_player_name";
        public static final String COL_SEASON = "leaving_season";
        public static final String COL_ANNOUNCED_AT = "leaving_announced_at";
        public static final String COL_COMMENT = "leaving_comment";
        public static final String COL_NOTE = "leaving_note";
        public static final String COL_AFTER_LEAVING = "after_leaving";
    }

    //テーブル生成定義
    public static final String CREATE_LEAVINGS_TABLE = "CREATE TABLE " +
            LEAVINGS_TABLE_NAME + " (" +
            LeavingsTable._ID          + INTEGER_TYPE + PRIMARY_TYPE + COMMA_SEP +
            LeavingsTable.COL_ID       + INTEGER_TYPE  + COMMA_SEP +
            LeavingsTable.COL_NAME     + TEXT_TYPE     + COMMA_SEP +
            LeavingsTable.COL_SEASON   + INTEGER_TYPE  + COMMA_SEP +
            LeavingsTable.COL_ANNOUNCED_AT + TEXT_TYPE     + COMMA_SEP +
            LeavingsTable.COL_COMMENT  + TEXT_TYPE     + COMMA_SEP +
            LeavingsTable.COL_NOTE     + TEXT_TYPE     + COMMA_SEP +
            LeavingsTable.COL_AFTER_LEAVING + TEXT_TYPE     + COMMA_SEP +
            " )";

    // year league rank

    public static abstract class RankTable implements BaseColumns {
        public static final String COL_YEAR = "year";
        public static final String COL_LEAGUE = "league";
        public static final String COL_RANK = "rank";
    }

    //テーブル生成定義
    public static final String CREATE_RANK_TABLE = "CREATE TABLE " +
            RANK_TABLE_NAME + " (" +
            RankTable._ID          + INTEGER_TYPE + PRIMARY_TYPE + COMMA_SEP +
            RankTable.COL_YEAR     + INTEGER_TYPE  + COMMA_SEP +
            RankTable.COL_LEAGUE   + TEXT_TYPE     + COMMA_SEP +
            RankTable.COL_RANK     + TEXT_TYPE     + COMMA_SEP +
            " )";


    //取得データ定義
    public static abstract class PlayersInfoTable implements BaseColumns {
        public static final String COL_ID = MembersTable.COL_ID;
        public static final String COL_NAME = MembersTable.COL_NAME;
        public static final String COL_SEASON = MembersTable.COL_SEASON;
        public static final String COL_NUMBER = MembersTable.COL_NUMBER;
        public static final String COL_POSITION = MembersTable.COL_POSITION;
        public static final String COL_NOTE = MembersTable.COL_NOTE;

        public static final String COL_YOMI = PlayersTable.COL_YOMI;
        public static final String COL_YOMI_J = PlayersTable.COL_YOMI_J;
        public static final String COL_BIRTHDAY = PlayersTable.COL_BIRTHDAY;
        public static final String COL_HEIGHT = PlayersTable.COL_HEIGHT;
        public static final String COL_WEIGHT = PlayersTable.COL_WEIGHT;
        public static final String COL_BLOOD = PlayersTable.COL_BLOOD;
        public static final String COL_HOMETOWN = PlayersTable.COL_HOMETOWN;
        public static final String COL_CAREER = PlayersTable.COL_CAREER;

        public static final String COL_JOINING_SEASON = JoiningsTable.COL_SEASON;
        public static final String COL_JOINING_ANNOUNCED_AT = JoiningsTable.COL_ANNOUNCED_AT;
        public static final String COL_JOINING_COMMENT = JoiningsTable.COL_COMMENT;
        public static final String COL_JOINING_NOTE = JoiningsTable.COL_NOTE;

        public static final String COL_LEAVING_SEASON = LeavingsTable.COL_SEASON;
        public static final String COL_LEAVING_ANNOUNCED_AT = LeavingsTable.COL_ANNOUNCED_AT;
        public static final String COL_LEAVING_COMMENT = LeavingsTable.COL_COMMENT;
        public static final String COL_LEAVING_NOTE = LeavingsTable.COL_NOTE;
        public static final String COL_AFTER_LEAVING = LeavingsTable.COL_AFTER_LEAVING;

        public static final String COL_NEW_MEMBER = "is_new";
    }

    //public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

}
