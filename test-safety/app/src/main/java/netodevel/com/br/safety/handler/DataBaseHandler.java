package netodevel.com.br.safety.handler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author NetoDevel
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "safety.db";
    public static final String TABLE = "user";
    public static final String ID = "id";
    public static final String LOGGED = "logged";
    public static final String NAME = "name";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE+" ("
                + ID + " integer primary key autoincrement,"
                + LOGGED + " text,"
                + NAME + " text"
                +");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
