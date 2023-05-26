package ru.mirea.kokuevdd.dialog2;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MyProgressDialogFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("loading");

        return pd;
    }
}