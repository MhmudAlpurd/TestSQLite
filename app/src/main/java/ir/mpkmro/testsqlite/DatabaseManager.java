package ir.mpkmro.testsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper {

    public static final String DatabaseName = "myinfo.db";
    public static final int Version = 1;
    public static final String TableName = "tbl_person";
    public static final String dID = "id";
    public static final String dName = "name";
    public static final String dFamily = "family";


    public DatabaseManager(@Nullable Context cnt) {
        super(cnt, DatabaseName, null, Version);
    }


    @Override
    public void onCreate(SQLiteDatabase cdb) {
        String cQuery = "CREATE TABLE " + TableName + " ( " + dID + " INTEGER PRIMARY KEY, " + dName
                + " TEXT, " + dFamily + " TEXT );";
        cdb.execSQL(cQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertperson(Person iprs) {

        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues icv = new ContentValues();
        icv.put(dID, iprs.pID);
        icv.put(dName, iprs.pName);
        icv.put(dFamily, iprs.pFamily);

        idb.insert(TableName, null, icv);
        idb.close();
    }


    public Person getPerson(String gID) {
        Person gPrs = new Person();
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "SELECT * FROM " + TableName + " WHERE " + dID + "=" + gID;
        Cursor gCur = gdb.rawQuery(gQuery, null);
        if (gCur.moveToFirst()) {
            gPrs.pName = gCur.getString(1);
            gPrs.pFamily = gCur.getString(2);

        }
        return gPrs;


    }

    public void updatePerson(Person uprs) {

        SQLiteDatabase udb = this.getWritableDatabase();
        ContentValues ucv = new ContentValues();
        ucv.put(dName, uprs.pName);
        ucv.put(dFamily, uprs.pFamily);
        udb.update(TableName, ucv, dID + " = ?", new String[]{String.valueOf(uprs.pID)});
    }

    public boolean deletePerson(Person dprs) {
        SQLiteDatabase ddb = this.getWritableDatabase();
        long dResult = ddb.delete(TableName, dID + "=?", new String[]{String.valueOf(dprs.pID)});


        if (dResult == 0 ){
            return false;
        }else{
            return true;
        }
    }


}
