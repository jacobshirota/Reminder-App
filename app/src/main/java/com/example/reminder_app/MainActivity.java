package com.example.reminder_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.*;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ReminderViewModel mReminderViewModel;

    public static String currentTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReminderViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ReminderListAdapter adapter = new ReminderListAdapter(new ReminderListAdapter.ItemsDiff(), mReminderViewModel.getAllReminders());
        Toast.makeText(this, mReminderViewModel.getAllReminders().getValue()==null?"thisnull":"thisdata", Toast.LENGTH_LONG).show();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



        final Observer<List<ReminderTable>> dataObserver = new Observer<List<ReminderTable>>() {
            @Override
            public void onChanged(List<ReminderTable> reminderTables) {

                Toast.makeText(getApplicationContext(), String.valueOf(adapter.getItemCount()), Toast.LENGTH_LONG).show();
                for (ReminderTable r : reminderTables) {
                    mReminderViewModel.insert(r);
                }
                recyclerView.draw(new Canvas());
            }
        };

        mReminderViewModel.getAllReminders().observe(this, dataObserver);




        final FloatingActionButton button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivityForResult(intent, 1);
        });

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) { //REMOVE HARD CODING LATER
            ReminderTable reminder = new ReminderTable();
            reminder.date = data.getStringExtra(AddActivity.EXTRA_DATE);
            reminder.title = data.getStringExtra(AddActivity.EXTRA_TITLE);
            reminder.description = data.getStringExtra(AddActivity.EXTRA_DESC);

            mReminderViewModel.insert(reminder);
            findViewById(R.id.recyclerview).draw(new Canvas());

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Unable to save reminder",
                    Toast.LENGTH_LONG).show();
        }
    }


    public void goToReminderActivity(View v) {



        Intent i = new Intent(MainActivity.this, ReminderActivity.class);
        startActivity(i);
    }

}