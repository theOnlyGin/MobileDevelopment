package ru.mirea.kokuevdd.dialog2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MyDateDialogFragment extends DialogFragment  implements DatePickerDialog.OnDateSetListener{
    public MyDateDialogFragment()
    {
    }
    TextView currentDateTime;
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

    public void showSetDate(int year,int month,int day) {
        currentDateTime.setText(year+"/+"+month+"/"+day);
    }
}