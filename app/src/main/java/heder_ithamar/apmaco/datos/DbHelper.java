package heder_ithamar.apmaco.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Heder_ithamar on 03/05/2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "apmaco.sqlite";
    private  static final int DB_SCHEME_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DataBaseManager.CREATE_TABLE);
        db.execSQL(DataBaseManager.CREATE_TABLE2);
        db.execSQL(DataBaseManager.CREATE_TABLE3);
        db.execSQL(DataBaseManager.CREATE_TABLE4);
        db.execSQL(DataBaseManager.CREATE_TABLE5);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
