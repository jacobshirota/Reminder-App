package com.example.reminder_app;

import androidx.annotation.NonNull;
import androidx.room.*;

import java.util.Date;

@Entity (tableName = "reminder_table")
public class ReminderTable {

    @PrimaryKey (autoGenerate = true)
    public int id;

    @NonNull
    public String title;
    public String description;
    @NonNull
    public String date;


}
