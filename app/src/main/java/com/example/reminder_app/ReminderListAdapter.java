package com.example.reminder_app;

import android.view.*;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReminderListAdapter extends RecyclerView.Adapter<ReminderViewHolder> {

    public LiveData<ArrayList<ReminderTable>> reminders;

    public ReminderListAdapter(@NonNull DiffUtil.ItemCallback<ReminderTable> diffCallback, LiveData<ArrayList<ReminderTable>> remindersList) {
        //super(diffCallback);
        reminders = remindersList;
    }


    @Override
    public ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ReminderViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ReminderViewHolder holder, int position) {
        ReminderTable current = reminders.getValue().get(position);
        holder.bind(current.title, current.date);
    }

    @Override
    public int getItemCount() {
        if (reminders.getValue() != null) {
            return reminders.getValue().size();
        }
        return 0;
    }

    public ReminderTable getItem(int pos) {
        return reminders.getValue().get(pos);
    }


    static class ItemsDiff extends DiffUtil.ItemCallback<ReminderTable> {

        @Override
        public boolean areItemsTheSame(@NonNull ReminderTable oldItem, @NonNull ReminderTable newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ReminderTable oldItem, @NonNull ReminderTable newItem) {
            return oldItem.title.equals(newItem.title);
        }
    }
}
