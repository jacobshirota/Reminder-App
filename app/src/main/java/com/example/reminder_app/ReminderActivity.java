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

import java.util.List;


public class ReminderActivity extends AppCompatActivity {

    private ReminderViewModel mReminderViewModel;
    private LiveData<List<ReminderTable>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_reminder);

        mReminderViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);
        Toast.makeText(this, mReminderViewModel.getAllReminders() != null?"rargdata":"rargnull", Toast.LENGTH_LONG).show();
        list = mReminderViewModel.getAllReminders();

        ReminderTable current = list.getValue().get(0);


        TextView title = findViewById(R.id.title_view),
                date = findViewById(R.id.date_view),
                time = findViewById(R.id.time_view),
                desc = findViewById(R.id.description_view);

        Toast.makeText(this, list.getValue()==null?"losnull":"losdata", Toast.LENGTH_LONG).show();
        //title.setText(list.getValue().get(0).title);


        final ImageButton exit_button = findViewById(R.id.exit_button); //FINISH AL OF THESE CLICK LISTENERS LATER
        exit_button.setOnClickListener(v -> {
            Intent intent = new Intent(ReminderActivity.this, MainActivity.class);
            startActivity(intent);
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
