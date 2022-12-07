package com.example.reminder_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.*;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ReminderViewModel mReminderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReminderViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);
        Toast.makeText(this, String.valueOf(mReminderViewModel.getAll().size()), Toast.LENGTH_LONG).show();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ReminderListAdapter adapter = new ReminderListAdapter(mReminderViewModel.getAll());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));






        final FloatingActionButton button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivityForResult(intent, 1);
        });

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mReminderViewModel.insert(mReminderViewModel.getDemo().get(0));
        RecyclerView r = findViewById(R.id.recyclerview);
        r.getAdapter().notifyDataSetChanged();
    }


    public void goToReminderActivity(View v) {
        Intent i = new Intent(MainActivity.this, ReminderActivity.class);
        Intent data = new Intent();
/*
        TextView pp = v.findViewById(R.id.recycler_title);
        data.putExtra("TITLE", pp.getText());
        pp = v.findViewById(R.id.recycler_date);
        data.putExtra("DATE", pp.getText());
        pp = v.findViewById(R.id.recycler_time);
        data.putExtra("TIME", pp.getText());
        pp = v.findViewById(R.id.recycler_desc);
        data.putExtra("DESC", pp.getText());
        i.putExtras(data);*/
        startActivity(i);
    }

}