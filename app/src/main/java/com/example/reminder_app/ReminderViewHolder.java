package com.example.reminder_app;

import android.content.Intent;
import android.view.*;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class ReminderViewHolder extends RecyclerView.ViewHolder {

    public final TextView reminderTitleView,
                            reminderDateView,
                            reminderTimeView,
                            reminderDesc;

    private ReminderViewHolder(View itemView) {
        super(itemView);
        reminderTitleView = itemView.findViewById(R.id.recycler_title);
        reminderDateView = itemView.findViewById(R.id.recycler_date);
        reminderTimeView = itemView.findViewById(R.id.recycler_time);
        reminderDesc = itemView.findViewById(R.id.recycler_desc);
    }

    public void bind(String title, String date, String time, String desc) {
        reminderDesc.setText(desc);
        reminderTitleView.setText(title);
        reminderDateView.setText(date);
        reminderTimeView.setText(time);
    }

    static ReminderViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ReminderViewHolder(view);
    }

}

