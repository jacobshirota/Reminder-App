package com.example.reminder_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;


public class ReminderActivity extends AppCompatActivity {

    private ReminderViewModel mReminderViewModel;
    private LiveData<ArrayList<ReminderTable>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_reminder);

        mReminderViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);
        list = mReminderViewModel.getAllReminders();


        TextView title = findViewById(R.id.title_view),
                date = findViewById(R.id.date_view),
                time = findViewById(R.id.time_view),
                desc = findViewById(R.id.description_view);


        final ImageButton exit_button = findViewById(R.id.exit_button); //FINISH AL OF THESE CLICK LISTENERS LATER
        exit_button.setOnClickListener(v -> {
            finish();
        });
        final ImageButton edit_button = findViewById(R.id.edit_button);
        edit_button.setOnClickListener(v -> {
            Intent intent = new Intent(ReminderActivity.this, AddActivity.class);
            startActivity(intent);
        });
        final ImageButton delete_button = findViewById(R.id.delete_button);
        delete_button.setOnClickListener(v -> {//OTHER THINGS NEED TO BE DONE HERE
            Intent intent = new Intent(ReminderActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });


    }



    public void goToAddActivity(View v) {
        Intent i = new Intent(ReminderActivity.this, AddActivity.class);
        startActivity(i);
    }

}
