package com.example.masuo.parceiroladiesrespectbook;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Masuo on 2016/07/31.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static String LOG = "DatabaseHelper";

//    private SQLiteDatabase mDatabase;
    private final Context mContext;
    private final File mDatabaseFile;
    private  boolean mCopyFromAssets = false;

    // プリセットデータベースの利用法：
    // assetsにデータベースを格納し、ビルドする。
    // 実行時にassetからデフォルトのデータベースパスへコピーして使用する。

    // データベースが所定の場所にある場合、そのまま使用する。
    // データベースが所定の場所に無い場合、assetsのデータベースからコピーする。
    // データベースが所定の場所に無く、assetsのデータベースもない場合、エラー終了。

    // SQLiteOpenHelperでは onCreate/onUpgradeメソッドのOverrideは必須

    public DatabaseHelper(Context context) {
        super(context, PlayerContract.DATABASE_NAME, null, PlayerContract.DATABASE_VERSION);

        // Databaseのpathを得る
        mContext = context;
        mDatabaseFile = mContext.getDatabasePath(PlayerContract.DATABASE_NAME);
        Log.i(LOG, mDatabaseFile.toString());
    }

    public void checkDataBaseExist()
    {
        Log.i(LOG, "checkDataBaseExist");

        databaseExists();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase db = super.getReadableDatabase();

        Log.i(LOG, "Version=" + db.getVersion());

        if (mCopyFromAssets) {
            try {
                // assetsに格納したデータベースをデフォルトのデータベースパスに作成したデータベースへコピーする
                db = copyDatabaseFromAssets(db, mContext, mDatabaseFile, PlayerContract.ASSETS_DATABASE_NAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return db;
    }

    // 初回作成時
    // getReadableDatabaseで新規DBの場合に呼び出される。
    @Override
    public void onCreate(SQLiteDatabase db) {
        // データベース作成
        mCopyFromAssets = true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(LOG, "onUpgrade" + " " + Integer.toString(oldVersion) + ":" + Integer.toString(newVersion));
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);

        Log.i(LOG, "onDowngrade" + " " + Integer.toString(oldVersion) + ":" + Integer.toString(newVersion));

        // データベースは存在するが、最新ではない

        Log.i(LOG, Integer.toString(oldVersion) + ":" + Integer.toString(newVersion));

        // 削除し、データベースが存在しないことを示す
        // journalも削除
        // API Level 16以降
        SQLiteDatabase.deleteDatabase(mDatabaseFile);
    }

    /**
     * assets に格納したデータベースをデフォルトのデータベースパスに作成したデータベースにコピーする
     */
    private SQLiteDatabase copyDatabaseFromAssets(SQLiteDatabase db, Context context, File dbfile, String assetsname) throws IOException{

        Log.i(LOG, "Copy from " + assetsname);

        InputStream in = null;
        try {
            // asset 内のデータベースファイルにアクセス
            in = context.getAssets().open(assetsname);
        }
        catch (IOException e)
        {
            throw new Error("Error database in assets");
        }

        Log.i(LOG, "Copy start " + dbfile.getAbsolutePath());
        Log.i(LOG, "Copy folder " + dbfile.getParent());

        db.close();

        // フォルダがない場合は作成
        File checkDirectory = new File(dbfile.getParent());
        if (! checkDirectory.exists()) {
            checkDirectory.mkdir();
            Log.i(LOG, "Create folder");
        }

        // デフォルトのデータベースパスに作成した空のDB
        OutputStream out = new FileOutputStream(dbfile.getAbsoluteFile());

        Log.i(LOG, "Copy start " + dbfile.getAbsolutePath());

        // データベースをコピーする
        byte[] buffer = new byte[1024];
        int size;
        while ((size = in.read(buffer)) > 0) {
            Log.i(LOG, "size=" + Integer.toString(size));
            out.write(buffer, 0, size);
        }

        // Close the streams
        out.flush();
        out.close();
        in.close();

        Log.i(LOG, "Databaseコピー終了");

        return super.getReadableDatabase();
    }

    /**
     * 再コピーを防止するために、すでにデータベースがあるかどうか判定する
     *
     * データベースバージョンが古い場合、存在している古いデータベースを削除する。
     * アプリではデータベースを編集しないことが前提。
     *
     * @return 存在している場合 {@code true}
     */
    private boolean databaseExists() {
        String databasePath = mDatabaseFile.getAbsolutePath();

        SQLiteDatabase tempDb = null;
        try {
            // データベースの存在確認
            tempDb = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            // データベースはまだ存在していない
            Log.e(LOG, "SQLiteAbortException:Databaseは存在していない" + " " + e.toString());
        }
        catch (Exception ex){
            Log.e(LOG, "Exception:Databaseは存在していない" + " " + ex.toString());
        }

        if (tempDb == null) {
            // データベースはまだ存在していない
            Log.i(LOG, "Databaseは存在していない");
        }
        else
        {
            Log.i(LOG, "Databaseが存在している");
        }

        return (tempDb != null);
    }

    /**
     * asset に格納したデータベースをコピーするための空のデータベースを作成する
     *
     * データベースが所定の場所にある場合、そのまま使用する。
     * データベースが所定の場所に無い場合、空のデータベースを作成する。
     * 空のデータベースにassetsのデータベースをコピーする。
     * データベースが所定の場所に無く、assetsのデータベースもない場合、エラー終了。
     *
     */
//    public void createEmptyDatabase() throws IOException {
//
//        // アプリデータベースの存在判定
//        boolean databaseExist = checkDatabaseExists();
//
//        if (databaseExist) {
//            // すでにデータベースは作成されている
//            // そのまま使用する
//
//            // データベースは作成済み
//            Log.i(LOG, "Databaseは作成済み");
//            String databasePath = mDatabaseFile.getAbsolutePath();
//            SQLiteDatabase newDb = null;
//            try {
//                newDb = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
//            } catch (SQLiteException e) {
//            }
//
//            if (newDb != null) {
//                Log.i(LOG, "Version=" + Integer.toString(newDb.getVersion()));
//                newDb.close();
//            }
//
//        } else {
//
//            Log.i(LOG, "Databaseは存在しない");
//
//            // 空のデータベース作成
//            // 空を作って、そのファイルへのコピーではうまく動作しない
//            // そのデータベースをハンドルしてしまうせいか？
//            // これは必要ない
//            //getReadableDatabase();
//
//            try {
//                // assetsに格納したデータベースをデフォルトのデータベースパスに作成したデータベースへコピーする
//                copyDatabaseFromAssets(mContext, mDatabaseFile, PlayerContract.ASSETS_DATABASE_NAME);
//
//                SQLiteDatabase newDb = null;
//                try {
//                    newDb = SQLiteDatabase.openDatabase(mDatabaseFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
//                } catch (SQLiteException e) {
//                }
//
//                if (newDb != null) {
//                    newDb.setVersion(PlayerContract.DATABASE_VERSION);
//                    newDb.close();
//                }
//
//            } catch (IOException e) {
//                throw new Error("Error copying database");
//            }
//        }
//    }

    /**
     * 再コピーを防止するために、すでにデータベースがあるかどうか判定する
     *
     * データベースバージョンが古い場合、存在している古いデータベースを削除する。
     * アプリではデータベースを編集しないことが前提。
     *
     * @return 存在している場合 {@code true}
     */
//    private boolean checkDatabaseExists() {
//        String databasePath = mDatabaseFile.getAbsolutePath();
//
//        SQLiteDatabase tempDb = null;
//        try {
//            // データベースの存在確認
//            tempDb = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READONLY);
//        } catch (SQLiteException e) {
//            // データベースはまだ存在していない
//            Log.e(LOG, "SQLiteAbortException:Databaseは存在していない" + " " + e.toString());
//        }
//        catch (Exception ex){
//            Log.e(LOG, "Exception:Databaseは存在していない" + " " + ex.toString());
//        }
//
//        if (tempDb == null) {
//            // データベースはまだ存在していない
//            Log.i(LOG, "Databaseは存在していない");
//            return false;
//        }
//
//        // アプリのデータベースは存在している
//
//        int oldVersion = tempDb.getVersion();
//        int newVersion = PlayerContract.DATABASE_VERSION;
//
//        tempDb.close();
//
//        if (oldVersion == newVersion) {
//            // データベースは存在していて最新
//            Log.i(LOG, "Databaseは最新バージョン"+ " " + Integer.toString(oldVersion) + ":" + Integer.toString(newVersion));
//            return true;
//        }
//
//        // データベースは存在するが、最新ではない
//
//        Log.i(LOG, Integer.toString(oldVersion) + ":" + Integer.toString(newVersion));
//
//        // 削除し、データベースが存在しないことを示す
//        // journalも削除
//        // API Level 16以降
//        SQLiteDatabase.deleteDatabase(mDatabaseFile);
//
////        File f = new File(dbPath);
////        f.delete();
//        return false;
//    }

//    public SQLiteDatabase openDataBase() throws SQLiteException {
//        return getReadableDatabase();
//    }



//    public Cursor fetchCountriesByName(String inputText) throws SQLException {
//        Log.w(TAG, inputText);
//        Cursor mCursor = null;
//        if (inputText == null  ||  inputText.length () == 0)  {
//            mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
//                            KEY_CODE, KEY_NAME, KEY_CONTINENT, KEY_REGION},
//                    null, null, null, null, null);
//
//        }
//        else {
//            mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ROWID,
//                            KEY_CODE, KEY_NAME, KEY_CONTINENT, KEY_REGION},
//                    KEY_NAME + " like '%" + inputText + "%'", null,
//                    null, null, null, null);
//        }
//        if (mCursor != null) {
//            mCursor.moveToFirst();
//        }
//        return mCursor;
//
//    }

//    @Override
//    public synchronized void close() {
//        if(mDatabase != null)
//            mDatabase.close();
//
//        super.close();
//    }
}

