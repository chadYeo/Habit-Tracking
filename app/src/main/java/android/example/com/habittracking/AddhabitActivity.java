package android.example.com.habittracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AddhabitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addhabit);
    }

    public void onCancelButtonClicked(View v) {
        Intent intent = new Intent(AddhabitActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
