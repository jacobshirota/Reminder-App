package com.example.reminder_app;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ListIterator;

public class ReminderViewModel extends AndroidViewModel {

    private ArrayList<String[]> ReminderTable;


    public ArrayList<String[]> getAll() {
        return ReminderTable;
    }

    public void delete(String[] reminder) {
        ReminderTable.remove(reminder);
    }
    public void insert(String[] reminder) {
        ReminderTable.add(0, reminder);
    }

    public ArrayList<String[]> getDemo() {
        ArrayList<String[]> f = new ArrayList<String[]>();
        f.add(new String[]{"Demo reminder 2", "12/7/22", "2:5", "Demo description 2"});
        return f;
    }


    public ReminderViewModel (Application application) {
        super(application);
        ReminderTable = new ArrayList<String[]>();
        ReminderTable.add(new String[]{"Demo reminder 1", "12/1/22", "10:44", "Demo description"});

    }

}
