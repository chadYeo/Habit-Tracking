package android.example.com.habittracking.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.example.com.habittracking.data.HabitContract.HabitEntry;

public class HabitDbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Habit.db";

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + HabitEntry.TABLE_NAME + " (" +
                        HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, " +
                        HabitEntry.COLUMN_HABIT_DATE + " TEXT NOT NULL, " +
                        HabitEntry.COLUMN_HABIT_NUMBER + " INTEGER NOT NULL)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //No upgrade at this time
    }
}
