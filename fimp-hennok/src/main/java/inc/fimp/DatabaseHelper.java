package inc.fimp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tanav on 11/8/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME = "users";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";

    private static final String TABLE_CREATE = "create table users (id integer primary key not null , "
            + "name text not null, "
            + "uname text not null, "
            + "email text not null, "
            + "pass text not null);";



    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insertUser(UserInformation ui){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        /*String query = "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();*/

        //values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, ui.getName());
        values.put(COLUMN_UNAME, ui.getUname());
        values.put(COLUMN_PASS, ui.getPass());
        values.put(COLUMN_EMAIL, ui.getEmail());

        db.insert(TABLE_NAME, null, values);
    }

    public String  searchPass(String uname){
        db = this.getReadableDatabase();
        String query = "SELECT UNAME, PASS FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        String u, p;
        p = "not found";

        if(cursor.moveToFirst()){
            do{
                u = cursor.getString(0);
                p = cursor.getString(1);

                if(u.equals(uname)){
                    p = cursor.getString(1);
                    break;
                }

            }while(cursor.moveToNext());
        }
        return p;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
