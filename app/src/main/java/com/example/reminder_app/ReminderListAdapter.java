package com.example.reminder_app;

import android.view.*;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReminderListAdapter extends RecyclerView.Adapter<ReminderViewHolder>{

    public ArrayList<String[]> reminders;

    public ReminderListAdapter(ArrayList<String[]> remindersList) {
        reminders = remindersList;
    }


    @Override
    public ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ReminderViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ReminderViewHolder holder, int position) {
        String[] current = reminders.get(position);
        holder.bind(current[0], current[1], current[2], current[3]);
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }


}
