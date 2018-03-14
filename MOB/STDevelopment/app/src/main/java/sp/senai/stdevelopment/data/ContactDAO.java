package sp.senai.stdevelopment.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import sp.senai.stdevelopment.model.Contact;

/**
 * Created by Lucas Viana on 3/13/2018.
 */

public class ContactDAO extends SQLiteOpenHelper {

    private static final String dbName = "contactdb", tableName = "contact";
    private static final int version = 1;

    public ContactDAO(Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + tableName + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "message TEXT NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS contact";
        sqLiteDatabase.execSQL(sql);
    }

    public void insert(Contact obj) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        //
        cv.put("name", obj.getName());
        cv.put("message", obj.getMessage());
        //
        db.insert(tableName, null, cv);
    }

    public void delete(Long id) {
        SQLiteDatabase db = getWritableDatabase();
        //
        db.delete(tableName, "id = ?", new String[] { id.toString() });
    }

    public void update(Contact obj) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        //
        cv.put("name", obj.getName()D);
        cv.put("message", obj.getMessage());
        //
        db.update(tableName, cv, "id = ?", new String[] { obj.getId().toString() });
    }

    public List<Contact> searchAll() {
        String sql = "SELECT id, name, message FROM " + tableName;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<Contact> result = new ArrayList<>();
        //
        while(c.moveToNext()) {
            result.add(new Contact(c.getLong(0), c.getString(1), c.getString(2)));
        }
        //
        return result;
    }
}
