package inc.fimp;

/***
 * Developers: Tanav Sharma
 *             Alay Lad
 *             Hennok Tadesse
 *
 * Team Name: The A Team
 * Project Name: FIMP
 * Prof Name: Haki Sharifi
 * Course Code: CENG 319
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class JoystickDB extends SQLiteOpenHelper {

    public static final String COLUMN_SERVO1 = "servo1";
    public static final String COLUMN_SERVO2 = "servo2";
    public static final String COLUMN_SERVO3 = "servo3";
    public static final String COLUMN_SERVO4 = "servo4";
    public static final String TABLE_NAME = "joystick";

    private static final String DATABASE_NAME = "JoystickDB";
    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_CREATE =
            "CREATE TABLE " + TABLE_NAME +
                    "(" + COLUMN_SERVO1 + " INT," +
                    COLUMN_SERVO2 + " INT," +
                    COLUMN_SERVO3 + " INT," +
                    COLUMN_SERVO4 + " INT);";

    public JoystickDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database Operations", "Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(DATABASE_CREATE);
        Log.d("Database Operations", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public void putInformation(SQLiteDatabase db, int servo1, int servo2, int servo3, int servo4){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SERVO1, servo1);
        contentValues.put(COLUMN_SERVO2, servo2);
        contentValues.put(COLUMN_SERVO3, servo3);
        contentValues.put(COLUMN_SERVO4, servo4);

        db.insert(TABLE_NAME, null, contentValues);
        Log.d("Database Operations", "one raw inserted");
    }

    public Cursor getInformation(SQLiteDatabase db){
        Cursor cursor;
        String[] columnNames = {
                COLUMN_SERVO1,
                COLUMN_SERVO2,
                COLUMN_SERVO3,
                COLUMN_SERVO4};

        cursor = db.query(TABLE_NAME, columnNames, null, null, null, null, null);
        return cursor;
    }

}
