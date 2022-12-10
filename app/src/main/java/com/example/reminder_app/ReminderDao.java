package com.example.reminder_app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ReminderDao {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insert(ReminderTable... reminder);

    @Delete
    void delete(ReminderTable reminder);

    //Not a necessary feature
    @Query("DELETE FROM reminder_table")
    void deleteAll();


    @Query("SELECT * FROM reminder_table")
    LiveData<List<ReminderTable>> getAll();
}