package com.example.reminder_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
//import android.view.View;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

//import java.util.List;

public class AddActivity extends AppCompatActivity{

    public static final String EXTRA_DATE = "com.example.android.remindertablesql.DATE";//Used for data access
    public static final String EXTRA_TITLE = "com.example.android.remindertablesql.TITLE";
    public static final String EXTRA_DESC = "com.example.android.remindertablesql.DESC";
    public static final String EXTRA_TIME = "com.example.android.remindertablesql.TIME";

    private static final Calendar c = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        EditText mEditTitle = findViewById(R.id.edit_title),
                mEditDesc = findViewById(R.id.edit_description);

        final Button date = findViewById(R.id.date_button),
                time = findViewById(R.id.time_button),
                save = findViewById(R.id.save_button);

        date.setText((c.get(Calendar.MONTH)+1) + "/" + c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.YEAR));
        time.setText(c.get(Calendar.HOUR_OF_DAY) + ":" + (c.get(Calendar.MINUTE)>9?c.get(Calendar.MINUTE):"0"+c.get(Calendar.MINUTE)));

        date.setOnClickListener(this::showDatePickerDialog);
        time.setOnClickListener(this::showTimePickerDialog);

        save.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

                String titleString = "[Empty]",
                        descString = "";
                if (!TextUtils.isEmpty(mEditTitle.getText())) {
                    titleString = mEditTitle.getText().toString();
                }
                if (!TextUtils.isEmpty(mEditDesc.getText())) {
                    descString = mEditDesc.getText().toString();
                }

                replyIntent.putExtra(EXTRA_DATE, date.getText());
                replyIntent.putExtra(EXTRA_TIME, time.getText());
                replyIntent.putExtra(EXTRA_TITLE, titleString);
                replyIntent.putExtra(EXTRA_DESC, descString);
                setResult(RESULT_OK, replyIntent);

            finish();
        });


    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }



    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog d = new DatePickerDialog(requireContext(), this, year, month, day);
            d.getDatePicker().setMinDate(c.getTimeInMillis());

            return d;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            Button b = getActivity().findViewById(R.id.date_button);
            b.setText((month+1) + "/" + day + "/" + year);
        }
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            Button b = getActivity().findViewById(R.id.time_button);
            b.setText(hourOfDay + ":" + (minute>9?minute:"0"+minute));
        }
    }



    public void goToReminderActivity(View v) {
        Intent i = new Intent(AddActivity.this, ReminderActivity.class);
        startActivity(i);
    }


}
