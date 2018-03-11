package scalendar.com.worldskills.lsviana.scalendar.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import scalendar.com.worldskills.lsviana.scalendar.model.Student;

/**
 * Created by Lucas Viana on 2/21/2018.
 */

public class StudentDAO extends SQLiteOpenHelper {

    public StudentDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public StudentDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE student (" +
                "id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "address TEXT NOT NULL," +
                "telephone TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "classification REAL NOT NULL" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS student";
        db.execSQL(sql);
    }

    public void insert(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = new ContentValues();
        //
        data.put("name", student.getName());
        data.put("address", student.getAddress());
        data.put("email", student.getEmail());
        data.put("telephone", student.getTelephone());
        data.put("classification", student.getClassification());
        //
        db.insert("student", null, data);
    }

    public List<Student> searchAll(Integer id) {
        String sql = "SELECT id, name, address, email, telephone, classification FROM student";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<Student> result = new ArrayList<>();
        //
        while(c.moveToNext()) {
            result.add(new Student(
                    c.getLong(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getString(4),
                    c.getInt(4)
                    )
            );
        }
        //
        return result;
    }

}