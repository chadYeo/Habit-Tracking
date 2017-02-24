package android.example.com.habittracking;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.example.com.habittracking.data.HabitDbHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.example.com.habittracking.data.HabitContract.HabitEntry;

public class AddhabitActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mDateEditText;
    private EditText mNumberEditText;

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addhabit);
    }

    public void onSaveButtonClicked(View v) {
        mNameEditText = (EditText) findViewById(R.id.habitName_editText);
        mDateEditText = (EditText) findViewById(R.id.habitDate_editText);
        mNumberEditText = (EditText) findViewById(R.id.habirNumber_editText);

        String name = mNameEditText.getText().toString().trim();
        String date = mDateEditText.getText().toString().trim();
        String numberStr = mNumberEditText.getText().toString().trim();
        int number = Integer.parseInt(numberStr);

        mDbHelper = new HabitDbHelper(this);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, name);
        values.put(HabitEntry.COLUMN_HABIT_DATE, date);
        values.put(HabitEntry.COLUMN_HABIT_NUMBER, number);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving habit", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Habit is saved with id: " + newRowId, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void onCancelButtonClicked(View v) {
        Intent intent = new Intent(AddhabitActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
