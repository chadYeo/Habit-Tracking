package android.example.com.habittracking;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.example.com.habittracking.data.HabitDbHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import static android.example.com.habittracking.data.HabitContract.HabitEntry;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddhabitActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayData();
    }

    public void createDummyData() {
        mDbHelper = new HabitDbHelper(this);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "Dummy Name Test");
        values.put(HabitEntry.COLUMN_HABIT_DATE, "Dummy Date Test");
        values.put(HabitEntry.COLUMN_HABIT_NUMBER, 7);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    public void displayData() {
        mDbHelper = new HabitDbHelper(this);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_DATE,
                HabitEntry.COLUMN_HABIT_NUMBER
        };

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        TextView mListTextView = (TextView) findViewById(R.id.list_textView);

        try {
            mListTextView.setText(
                    "Total number of data: " + cursor.getCount() + "\n\n"
            );

            mListTextView.append(HabitEntry._ID + " - " + HabitEntry.COLUMN_HABIT_NAME + " - " +
                    HabitEntry.COLUMN_HABIT_DATE + " - " + HabitEntry.COLUMN_HABIT_NUMBER + "\n\n");

            int indexId = cursor.getColumnIndex(HabitEntry._ID);
            int indexName = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int indexDate = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DATE);
            int indexNumber= cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NUMBER);

            while (cursor.moveToNext()) {
                int currentId = cursor.getInt(indexId);
                String currentName = cursor.getString(indexName);
                String currentDate = cursor.getString(indexDate);
                int currentNumber = cursor.getInt(indexNumber);

                mListTextView.append(currentId + " - " + currentName + " - " + currentDate + " - " + currentNumber + "\n");
            }
        } finally {
            cursor.close();
        }
    }

    public void deleteData() {
        mDbHelper = new HabitDbHelper(this);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        db.delete(HabitEntry.TABLE_NAME, null, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.insert_dummy_data_item:
                createDummyData();
                displayData();
                return true;
            case R.id.delete_all_data_item:
                deleteData();
                displayData();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
