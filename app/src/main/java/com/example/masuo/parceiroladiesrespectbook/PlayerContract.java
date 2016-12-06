package com.example.masuo.parceiroladiesrespectbook;

import android.provider.BaseColumns;

/**
 * Created by Masuo on 2016/07/31.
 */
public class PlayerContract {

    public static final  int    DATABASE_VERSION   = 1;
    public static final  String ASSETS_DATABASE_NAME      = "ParceiroLadies.sqlite";
    public static final  String DATABASE_NAME      = "ParceiroLadies.db";
    private static final String TEXT_TYPE          = " TEXT";
    private static final String INTEGER_TYPE       = " INTEGER";
    private static final String PRIMARY_TYPE       = " PRIMARY KEY AUTOINCREMENT";
    private static final String COMMA_SEP          = ",";

    // テーブル名
    public static final String TABLE_NAME          = "players";

    // コントラクト・クラスを偶然実体化することからいくつかのことを防ぐ
    // 空コンストラクタが与えられる。
    public PlayerContract() {
    }

    // id	name	yomi	yomi_j	birthday	height	weight	blood	home	career	join	join_comment	leaving	leaving_state	leaving_comment	note

    public static abstract class PlayerInformation implements BaseColumns {

        public static final String COL_ID = "id";
        public static final String COL_NAME = "name";
        public static final String COL_NAMAE_ALPHA = "yomi";
        public static final String COL_NAMAE_KANA = "yomi_j";
        public static final String COL_BIRTHDAY = "birthday";
        public static final String COL_HEIGHT = "height";
        public static final String COL_WEIGHT = "weight";
        public static final String COL_BLOOD_TYPE = "blood";
        public static final String COL_HOMETOWN = "home";
        public static final String COL_CAREER = "career";
        public static final String COL_JOIN = "join";
        public static final String COL_JOIN_COMMENT = "join_comment";
        public static final String COL_LEAVING = "leaving";
        public static final String COL_LEAVING_STATE = "leaving_state";
        public static final String COL_LEAVING_COMMENT = "leaving_comment";
        public static final String COL_NOTE = "note";
    }

    //テーブル生成定義
    public static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + " (" +
            PlayerInformation._ID                + INTEGER_TYPE + PRIMARY_TYPE + COMMA_SEP +
            PlayerInformation.COL_ID             + INTEGER_TYPE  + COMMA_SEP +
            PlayerInformation.COL_NAME           + TEXT_TYPE     + COMMA_SEP +
            PlayerInformation.COL_NAMAE_ALPHA    + TEXT_TYPE     + COMMA_SEP +
            PlayerInformation.COL_NAMAE_KANA     + TEXT_TYPE     + COMMA_SEP +
            PlayerInformation.COL_BIRTHDAY       + TEXT_TYPE     + COMMA_SEP +
            PlayerInformation.COL_HEIGHT         + TEXT_TYPE     + COMMA_SEP +
            PlayerInformation.COL_WEIGHT         + TEXT_TYPE     + COMMA_SEP +
            PlayerInformation.COL_BLOOD_TYPE     + TEXT_TYPE     + COMMA_SEP +
            PlayerInformation.COL_HOMETOWN       + TEXT_TYPE     + COMMA_SEP +
            PlayerInformation.COL_CAREER         + TEXT_TYPE     + COMMA_SEP +
            PlayerInformation.COL_JOIN           + TEXT_TYPE     + COMMA_SEP +
            PlayerInformation.COL_JOIN_COMMENT   + TEXT_TYPE     + COMMA_SEP +
            PlayerInformation.COL_LEAVING        + TEXT_TYPE     + COMMA_SEP +
            PlayerInformation.COL_LEAVING_STATE  + TEXT_TYPE     + COMMA_SEP +
            PlayerInformation.COL_LEAVING_COMMENT + TEXT_TYPE    + COMMA_SEP +
            PlayerInformation.COL_NOTE           + TEXT_TYPE     + COMMA_SEP +
            " )";

    //public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;



}
