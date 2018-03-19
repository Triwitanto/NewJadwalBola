package jadwalbola.triwi.id.jadwalbola;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by TRW on 3/11/2018.
 */

public class BolaDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "db_belajar";
    private static final int DB_VERSION = 1;
    private static final String CREATE_QUERY = "create table "+BolaContract.BolaEntry.TABLE_NAME+
            "( "+BolaContract.BolaEntry.TANGGAL+" text,"+BolaContract.BolaEntry.JADWAL+
            " text,"+BolaContract.BolaEntry.TV+" text);";

    private static final String DROP_QUERY ="drop table if exists" +BolaContract.BolaEntry.TABLE_NAME+
            ";";

    public BolaDbHelper (Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
        Log.d("Database operations","Database created...");


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("Database operations","Table created...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_QUERY);
        Log.d("Database operations","Database updateed...");
    }

    public void putInformation (String tanggal,String jadwal, String tv, SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BolaContract.BolaEntry.TANGGAL,tanggal);
        contentValues.put(BolaContract.BolaEntry.JADWAL,jadwal);
        contentValues.put(BolaContract.BolaEntry.TV,tv);
        long l = db.insert(BolaContract.BolaEntry.TABLE_NAME, null, contentValues);
        Log.d("Database operations","One row inserted...");


    }

    public Cursor getInformation (SQLiteDatabase db)
    {

        String[] projection = {BolaContract.BolaEntry.TANGGAL,BolaContract.BolaEntry.JADWAL,BolaContract.BolaEntry.TV};
        Cursor cursor = db.query(BolaContract.BolaEntry.TABLE_NAME,projection,null,null,null,null,null);
        return cursor;
    }

}
