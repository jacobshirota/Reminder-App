package com.example.reminder_app;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

public class ReminderViewModel extends AndroidViewModel {

    private ReminderDao mReminderDao;
    private final LiveData<ArrayList<ReminderTable>> mAllReminders;

    LiveData<ArrayList<ReminderTable>> getAllReminders() {
        return mAllReminders;
    }

    //public void deleteAll() {mReminderDao.deleteAll();}
    public void insert(ReminderTable reminder) {mReminderDao.insert(reminder);}


    public ReminderViewModel (Application application) {
        super(application);
        mReminderDao = ReminderRoomDatabase.getDatabase(application).reminderDao();
        LiveData<List<ReminderTable>> temp;
        temp = mReminderDao.getAll();
        mAllReminders = Transformations.map(temp, r -> new ArrayList<ReminderTable>(r));
    }

}
