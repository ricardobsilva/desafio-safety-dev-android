package netodevel.com.br.safety.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import netodevel.com.br.safety.handler.DataBaseHandler;

/**
 * @author NetoDevel
 */
public class UserDataBaseController {

    private SQLiteDatabase dataBase;
    private DataBaseHandler dataBaseHandler;

    public UserDataBaseController(Context context) {
        dataBaseHandler = new DataBaseHandler(context);
    }

    public void save(String userName) {
        ContentValues values;
        dataBase = dataBaseHandler.getWritableDatabase();
        values = new ContentValues();
        values.put(DataBaseHandler.LOGGED, true);
        values.put(DataBaseHandler.NAME, userName);
        dataBase.insert(DataBaseHandler.TABLE, null , values);
        dataBase.close();
    }

    public boolean validateLogged() {
        boolean logged = false;
        String sql = "SELECT * FROM " + DataBaseHandler.TABLE;
        dataBase = dataBaseHandler.getWritableDatabase();
        Cursor cursor = dataBase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            logged = true;
        }
        cursor.close();
        return logged;
    }

}
