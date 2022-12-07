package com.example.reminder_app;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

public class ReminderViewModel extends AndroidViewModel {

    private ReminderDao mReminderDao;
    private final LiveData<List<ReminderTable>> mAllReminders;

    LiveData<List<ReminderTable>> getAllReminders() {

        mAll
        mSectionLive[0].addSource(mAllReminders, new Observer<List<ReminderTable>>() {
            @Override
            public void onChanged(@Nullable List<ReminderTable> sectionList) {
                if(sectionList == null || sectionList.isEmpty()) {
                    // Fetch data from API
                    mSectionLive[0] = mReminderDao.getAll();
                }else{
                    mSectionLive[0].removeSource(mAllReminders);
                    mSectionLive[0].setValue(sectionList);
                }
            }
        });
        return mSectionLive[0];
        //return mAllReminders;
    }
    //public void deleteAll() {mReminderDao.deleteAll();}
    public void insert(ReminderTable reminder) {mReminderDao.insert(reminder);}


    public ReminderViewModel (Application application) {
        super(application);
        mReminderDao = ReminderRoomDatabase.getDatabase(application).reminderDao();
        mAllReminders = mReminderDao.getAll();
    }

}
