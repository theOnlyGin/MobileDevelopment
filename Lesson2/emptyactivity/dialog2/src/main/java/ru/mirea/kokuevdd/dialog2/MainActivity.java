package ru.mirea.kokuevdd.dialog2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    static TextView currentDateTime;


    Calendar dateAndTime=Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentDateTime = findViewById(R.id.tvDateTime);
        setInitialDateTime();
    }
    public static class MyDateDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        public MyDateDialogFragment()
        {
        }
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar cal=Calendar.getInstance();
            int year=cal.get(Calendar.YEAR);
            int month=cal.get(Calendar.MONTH);
            int day=cal.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            showSetDate(year,monthOfYear,dayOfMonth);
        }
    }
    static public void showSetDate(int year,int month,int day) {
        currentDateTime.setText(year+"/+"+month+"/"+day);
    }


    public void setDate(View v) {
        MyDateDialogFragment datepicker=new MyDateDialogFragment();
        datepicker.show(getSupportFragmentManager(), "showDate");
    }


    public void setTime(View v) {

        MyTimeDialogFragment timeDialogFragment = new MyTimeDialogFragment();
        timeDialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void setProgress(View v){
        MyProgressDialogFragment progressDialogFragment = new MyProgressDialogFragment();
        progressDialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    private void setInitialDateTime() {

        currentDateTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }



}