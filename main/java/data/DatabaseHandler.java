package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import model.Classes;
import utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CLASSES_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " ("
                + Util.KEY_ID + " INTEGER PRIMARY KEY, "
                + Util.KEY_NAME + " TEXT" +  " )";

        sqLiteDatabase.execSQL(CREATE_CLASSES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public void addClass(Classes class1) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        if (!comparison(class1.getName())) {
            contentValues.put(Util.KEY_NAME, class1.getName());

            database.insert(Util.TABLE_NAME, null, contentValues);
            database.close();
        }
    }

    public Classes getClass(int id) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(Util.TABLE_NAME, new String[] {Util.KEY_ID, Util.KEY_NAME},
                Util.KEY_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        assert cursor != null;
        return new Classes(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
    }
    public List<Classes> getAllClasses () {
        SQLiteDatabase database = this.getReadableDatabase();

        List<Classes> classesList = new ArrayList<>();

        String selectAllClasses = "Select * from " + Util.TABLE_NAME;

        Cursor cursor = database.rawQuery(selectAllClasses, null);

        if (cursor.moveToFirst()) {
            do {
                Classes classes = new Classes();
                classes.setId(Integer.parseInt(cursor.getString(0)));
                classes.setName(cursor.getString(1));
                classesList.add(classes);
            }   while (cursor.moveToNext());
        }
        return classesList;
    }
    public boolean comparison (String name) {
        List<Classes> classesList = getAllClasses();

        for (Classes classes : classesList) {
            if (classes.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
