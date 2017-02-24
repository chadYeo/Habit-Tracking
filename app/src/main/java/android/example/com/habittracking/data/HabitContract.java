package android.example.com.habittracking.data;


import android.provider.BaseColumns;

public class HabitContract {

    private HabitContract() {
        //An empty private constructor makes sure that the class is not going to be initialised.
    }

    public static class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "habit";
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_DATE = "date";
        public static final String COLUMN_HABIT_NUMBER = "number";
    }
}
