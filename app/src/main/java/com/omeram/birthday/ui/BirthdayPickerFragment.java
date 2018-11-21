package com.omeram.birthday.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class BirthdayPickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog, disable future dates and return it
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (DetailsFragment) getTargetFragment(), year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        return datePickerDialog;
    }

}
