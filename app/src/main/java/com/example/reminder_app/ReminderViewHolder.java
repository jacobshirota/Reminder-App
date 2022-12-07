package com.example.reminder_app;

import android.content.Intent;
import android.view.*;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class ReminderViewHolder extends RecyclerView.ViewHolder {

    private final TextView reminderTitleView,
                            reminderDateView;

    private ReminderViewHolder(View itemView) {
        super(itemView);
        reminderTitleView = itemView.findViewById(R.id.recycler_title);
        reminderDateView = itemView.findViewById(R.id.recycler_date);
    }

    public void bind(String title, String date) {
        reminderTitleView.setText(title);
        //reminderTitleView.setTextSize(28);
        reminderDateView.setText(date);
    }

    static ReminderViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ReminderViewHolder(view);
    }

}

