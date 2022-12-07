package com.example.reminder_app;

import android.view.*;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.List;

public class ReminderListAdapter extends ListAdapter<ReminderTable, ReminderViewHolder> {

    public LiveData<List<ReminderTable>> reminders;

    public ReminderListAdapter(@NonNull DiffUtil.ItemCallback<ReminderTable> diffCallback, LiveData<List<ReminderTable>> remindersList) {
        super(diffCallback);
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
