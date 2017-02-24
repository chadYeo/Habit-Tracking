package android.example.com.habittracking;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.example.com.habittracking.data.HabitContract;
import android.example.com.habittracking.data.HabitDbHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddHabitActivity extends AppCompatActivity {

    private EditText mNameEditText;

    private EditText mMMEditText;
    private EditText mDDEditText;
    private EditText mYYYYEditText;

    private EditText mNumberEditText;

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addHabit);
    }

    public void onSaveButtonClicked(View v) {

        mNameEditText = (EditText) findViewById(R.id.habitName_editText);

        mMMEditText = (EditText) findViewById(R.id.month_editText);
        mDDEditText = (EditText) findViewById(R.id.date_editText);
        mYYYYEditText = (EditText) findViewById(R.id.year_editText);

        mNumberEditText = (EditText) findViewById(R.id.habirNumber_editText);

        String name = mNameEditText.getText().toString().trim();

        String mm = mMMEditText.getText().toString().trim();
        String dd = mDDEditText.getText().toString().trim();
        String yyyy = mYYYYEditText.getText().toString().trim();
        String date = mm + "/" + dd + "/" + yyyy;

        String numberStr = mNumberEditText.getText().toString().trim();
        int number = Integer.parseInt(numberStr);

        mDbHelper = new HabitDbHelper(this);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, name);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_DATE, date);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NUMBER, number);

        long newRowId = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving habit", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Habit is saved with id: " + newRowId, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void onCancelButtonClicked(View v) {
        Intent intent = new Intent(AddHabitActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
