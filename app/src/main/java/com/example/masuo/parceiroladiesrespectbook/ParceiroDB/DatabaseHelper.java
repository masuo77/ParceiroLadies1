package com.example.masuo.parceiroladiesrespectbook.ParceiroDB;

import android.content.Context;
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

    private final Context mContext;
    private final File mDatabaseFile;
    private boolean mCopyFromAssets = false;

    private final String mAssetDatabaseName;


    // プリセットデータベースの利用法：
    // assetsにデータベースを格納し、ビルドする。
    // 実行時にassetからデフォルトのデータベースパスへコピーして使用する。

    // データベースが所定の場所にある場合、そのまま使用する。
    // データベースが所定の場所に無い場合、assetsのデータベースからコピーする。
    // データベースが所定の場所に無く、assetsのデータベースもない場合、エラー終了。

    // SQLiteOpenHelperでは onCreate/onUpgradeメソッドのOverrideは必須

    public DatabaseHelper(Context context, String databaseName, int databaseVersion, String assetDatabaseName) {
        super(context, databaseName, null, databaseVersion);

        // Databaseのpathを得る
        mContext = context;
        mDatabaseFile = mContext.getDatabasePath(databaseName);
        mAssetDatabaseName = assetDatabaseName;
        Log.i(LOG, mDatabaseFile.toString());
    }

    public void checkDataBaseExist() {
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
                db = copyDatabaseFromAssets(db, mContext, mDatabaseFile, mAssetDatabaseName);
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


        // データベースは存在するが、最新ではない

        Log.i(LOG, Integer.toString(oldVersion) + ":" + Integer.toString(newVersion));

        // 削除し、データベースが存在しないことを示す
        // journalも削除
        // API Level 16以降
        //SQLiteDatabase.deleteDatabase(mDatabaseFile);

        Log.i(LOG, "Delete " + mDatabaseFile.getPath());

        mCopyFromAssets = true;
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        super.onDowngrade(db, oldVersion, newVersion);

        Log.i(LOG, "onDowngrade" + " " + Integer.toString(oldVersion) + ":" + Integer.toString(newVersion));

        // データベースは存在するが、最新ではない

        Log.i(LOG, Integer.toString(oldVersion) + ":" + Integer.toString(newVersion));

        // 削除し、データベースが存在しないことを示す
        // journalも削除
        // API Level 16以降
//        SQLiteDatabase.deleteDatabase(mDatabaseFile);

        mCopyFromAssets = true;
    }

    /**
     * assets に格納したデータベースをデフォルトのデータベースパスに作成したデータベースにコピーする
     */
    private SQLiteDatabase copyDatabaseFromAssets(SQLiteDatabase db, Context context, File dbfile, String assetsname) throws IOException {

        Log.i(LOG, "Copy from " + assetsname);

        InputStream in = null;
        try {
            // asset 内のデータベースファイルにアクセス
            in = context.getAssets().open(assetsname);
        } catch (IOException e) {
            throw new Error("Error database in assets");
        }

        Log.i(LOG, "Copy start " + dbfile.getAbsolutePath());
        Log.i(LOG, "Copy folder " + dbfile.getParent());

        db.close();

        // フォルダがない場合は作成
        File checkDirectory = new File(dbfile.getParent());
        if (!checkDirectory.exists()) {
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
     * <p>
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
        } catch (Exception ex) {
            Log.e(LOG, "Exception:Databaseは存在していない" + " " + ex.toString());
        }

        if (tempDb == null) {
            // データベースはまだ存在していない
            Log.i(LOG, "Databaseは存在していない");
        } else {
            Log.i(LOG, "Databaseが存在している");
        }

        return (tempDb != null);
    }

}

