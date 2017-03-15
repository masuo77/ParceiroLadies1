package com.example.masuo.parceiroladiesrespectbook.ParceiroDB;

import android.provider.BaseColumns;

/**
 * Created by Masuo on 2016/07/31.
 */
public class PlayerContract {

    public static final int DATABASE_VERSION = 3;
    public static final String ASSETS_DATABASE_NAME = "ParceiroLadies.sqlite";
    public static final String DATABASE_NAME = "ParceiroLadies.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String PRIMARY_TYPE = " PRIMARY KEY AUTOINCREMENT";
    private static final String COMMA_SEP = ",";

    // テーブル名
    // 選手リスト
    public static final String PLAYERS_TABLE_NAME = "players";
    // 入団選手、コメント
    public static final String JOININGS_TABLE_NAME = "joinings";
    // 退団選手、コメント
    public static final String LEAVINGS_TABLE_NAME = "leavings";
    // シーズンごとの選手リスト
    public static final String MEMBERS_TABLE_NAME = "members";
    // シーズンごとの成績
    public static final String SEASON_TABLE_NAME = "seasons";

    // スタッフリスト
    public static final String STAFF_TABLE_NAME = "staff";
    // シーズンごとのスタッフリスト
    public static final String STAFF_SEASON_TABLE_NAME = "staff_seasons";


    // 新加入マーク
    public static final String NEW_MEMBER_MARK = "☆";

    // コントラクト・クラスを偶然実体化することからいくつかのことを防ぐ
    // 空コンストラクタが与えられる。
    public PlayerContract() {
    }

    // year league rank
    public static abstract class SeasonTable implements BaseColumns {
        public static final String COL_SEASON = "season";
        public static final String COL_LEAGUE = "league";
        public static final String COL_RANK = "rank";
        public static final String COL_SLOGAN = "slogan";
        public static final String COL_NUMBER_FONT = "number_font";
    }

//    //テーブル生成定義
//    public static final String CREATE_RANK_TABLE = "CREATE TABLE " +
//            SEASON_TABLE_NAME + " (" +
//            SeasonTable._ID + INTEGER_TYPE + PRIMARY_TYPE + COMMA_SEP +
//            SeasonTable.COL_SEASON + INTEGER_TYPE + COMMA_SEP +
//            SeasonTable.COL_LEAGUE + TEXT_TYPE + COMMA_SEP +
//            SeasonTable.COL_RANK + TEXT_TYPE + COMMA_SEP +
//            SeasonTable.COL_SLOGAN + TEXT_TYPE + COMMA_SEP +
//            SeasonTable.COL_NUMBER_FONT + TEXT_TYPE + COMMA_SEP +
//            " )";

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
        public static final String COL_FACE = "face";
    }

//    //テーブル生成定義
//    public static final String CREATE_PLAYERS_TABLE = "CREATE TABLE " +
//            PLAYERS_TABLE_NAME + " (" +
//            PlayersTable._ID + INTEGER_TYPE + PRIMARY_TYPE + COMMA_SEP +
//            PlayersTable.COL_ID + TEXT_TYPE + COMMA_SEP +
//            PlayersTable.COL_NAME + TEXT_TYPE + COMMA_SEP +
//            PlayersTable.COL_YOMI + TEXT_TYPE + COMMA_SEP +
//            PlayersTable.COL_YOMI_J + TEXT_TYPE + COMMA_SEP +
//            PlayersTable.COL_BIRTHDAY + TEXT_TYPE + COMMA_SEP +
//            PlayersTable.COL_HEIGHT + TEXT_TYPE + COMMA_SEP +
//            PlayersTable.COL_WEIGHT + TEXT_TYPE + COMMA_SEP +
//            PlayersTable.COL_BLOOD + TEXT_TYPE + COMMA_SEP +
//            PlayersTable.COL_HOMETOWN + TEXT_TYPE + COMMA_SEP +
//            PlayersTable.COL_CAREER + TEXT_TYPE + COMMA_SEP +
//            PlayersTable.COL_FACE + TEXT_TYPE + COMMA_SEP +
//            PlayersTable.COL_FACE2 + TEXT_TYPE + COMMA_SEP +
//            " )";

    // 年度別
    // member_player_id	member_player_name	member_season	member_season_number	member_season_position	member_season_note
    public static abstract class MembersTable implements BaseColumns {
        public static final String COL_ID = "member_player_id";
        public static final String COL_NAME = "member_player_name";
        public static final String COL_SEASON = "member_season";
        public static final String COL_NUMBER = "member_season_number";
        public static final String COL_POSITION = "member_season_position";
        public static final String COL_NOTE = "member_season_note";
        public static final String COL_JOINING_STATUS = "member_join_status";
        public static final String COL_LEAVING_STATUS = "member_leaving_status";
        public static final String COL_FACE = "member_face";
    }

//    //テーブル生成定義
//    public static final String CREATE_MEMBERS_TABLE = "CREATE TABLE " +
//            MEMBERS_TABLE_NAME + " (" +
//            MembersTable._ID + INTEGER_TYPE + PRIMARY_TYPE + COMMA_SEP +
//            MembersTable.COL_ID + TEXT_TYPE + COMMA_SEP +
//            MembersTable.COL_NAME + TEXT_TYPE + COMMA_SEP +
//            MembersTable.COL_SEASON + TEXT_TYPE + COMMA_SEP +
//            MembersTable.COL_NUMBER + TEXT_TYPE + COMMA_SEP +
//            MembersTable.COL_POSITION + TEXT_TYPE + COMMA_SEP +
//            MembersTable.COL_NOTE + TEXT_TYPE + COMMA_SEP +
//            MembersTable.COL_JOINING_STATUS + TEXT_TYPE + COMMA_SEP +
//            MembersTable.COL_LEAVING_STATUS + TEXT_TYPE + COMMA_SEP +
//            MembersTable.COL_FACE + TEXT_TYPE + COMMA_SEP +
//            " )";


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

//    //テーブル生成定義
//    public static final String CREATE_JOININGS_TABLE = "CREATE TABLE " +
//            JOININGS_TABLE_NAME + " (" +
//            JoiningsTable._ID + INTEGER_TYPE + PRIMARY_TYPE + COMMA_SEP +
//            JoiningsTable.COL_ID + TEXT_TYPE + COMMA_SEP +
//            JoiningsTable.COL_NAME + TEXT_TYPE + COMMA_SEP +
//            JoiningsTable.COL_SEASON + TEXT_TYPE + COMMA_SEP +
//            JoiningsTable.COL_ANNOUNCED_AT + TEXT_TYPE + COMMA_SEP +
//            JoiningsTable.COL_COMMENT + TEXT_TYPE + COMMA_SEP +
//            JoiningsTable.COL_NOTE + TEXT_TYPE + COMMA_SEP +
//            " )";

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

//    //テーブル生成定義
//    public static final String CREATE_LEAVINGS_TABLE = "CREATE TABLE " +
//            LEAVINGS_TABLE_NAME + " (" +
//            LeavingsTable._ID + INTEGER_TYPE + PRIMARY_TYPE + COMMA_SEP +
//            LeavingsTable.COL_ID + TEXT_TYPE + COMMA_SEP +
//            LeavingsTable.COL_NAME + TEXT_TYPE + COMMA_SEP +
//            LeavingsTable.COL_SEASON + TEXT_TYPE + COMMA_SEP +
//            LeavingsTable.COL_ANNOUNCED_AT + TEXT_TYPE + COMMA_SEP +
//            LeavingsTable.COL_COMMENT + TEXT_TYPE + COMMA_SEP +
//            LeavingsTable.COL_NOTE + TEXT_TYPE + COMMA_SEP +
//            LeavingsTable.COL_AFTER_LEAVING + TEXT_TYPE + COMMA_SEP +
//            " )";

    //全情報
    //取得データ定義
    public static abstract class PlayersInfoTable implements BaseColumns {
        public static final String COL_ID = MembersTable.COL_ID;
        public static final String COL_NAME = MembersTable.COL_NAME;
        public static final String COL_SEASON = MembersTable.COL_SEASON;
        public static final String COL_NUMBER = MembersTable.COL_NUMBER;
        public static final String COL_POSITION = MembersTable.COL_POSITION;
        public static final String COL_NOTE = MembersTable.COL_NOTE;
        public static final String COL_JOINING_STATUS = MembersTable.COL_JOINING_STATUS;
        public static final String COL_LEAVING_STATUS = MembersTable.COL_LEAVING_STATUS;
        public static final String COL_LATEST_FACE = MembersTable.COL_FACE;

        public static final String COL_YOMI = PlayersTable.COL_YOMI;
        public static final String COL_YOMI_J = PlayersTable.COL_YOMI_J;
        public static final String COL_BIRTHDAY = PlayersTable.COL_BIRTHDAY;
        public static final String COL_HEIGHT = PlayersTable.COL_HEIGHT;
        public static final String COL_WEIGHT = PlayersTable.COL_WEIGHT;
        public static final String COL_BLOOD = PlayersTable.COL_BLOOD;
        public static final String COL_HOMETOWN = PlayersTable.COL_HOMETOWN;
        public static final String COL_CAREER = PlayersTable.COL_CAREER;
        public static final String COL_FACE = PlayersTable.COL_FACE;

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

    // スタッフ
    // id name yomi yomi_j birthday height weight blood home career
    public static abstract class StaffTable implements BaseColumns {
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
        public static final String COL_FACE = "face";
    }

    // 年度別
    public static abstract class StaffSeasonTable implements BaseColumns {
        public static final String COL_ID = "staff_season_id";
        public static final String COL_NAME = "staff_season_name";
        public static final String COL_SEASON = "staff_season_year";
        public static final String COL_POST = "staff_season_post";
        public static final String COL_COMMENT = "staff_season_comment";
        public static final String COL_FACE = "staff_season_face";
    }

    //全情報
    //取得データ定義
    public static abstract class StaffInfoTable implements BaseColumns {
        public static final String COL_ID = StaffSeasonTable.COL_ID;
        public static final String COL_NAME = StaffSeasonTable.COL_NAME;
        public static final String COL_SEASON = StaffSeasonTable.COL_SEASON;
        public static final String COL_POST = StaffSeasonTable.COL_POST;
        public static final String COL_COMMENT = StaffSeasonTable.COL_COMMENT;
        public static final String COL_LATEST_FACE = StaffSeasonTable.COL_FACE;

        public static final String COL_YOMI = StaffTable.COL_YOMI;
        public static final String COL_YOMI_J = StaffTable.COL_YOMI_J;
        public static final String COL_BIRTHDAY = StaffTable.COL_BIRTHDAY;
        public static final String COL_HEIGHT = StaffTable.COL_HEIGHT;
        public static final String COL_WEIGHT = StaffTable.COL_WEIGHT;
        public static final String COL_BLOOD = StaffTable.COL_BLOOD;
        public static final String COL_HOMETOWN = StaffTable.COL_HOMETOWN;
        public static final String COL_CAREER = StaffTable.COL_CAREER;
        public static final String COL_FACE = StaffTable.COL_FACE;
    }


    //public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

}
