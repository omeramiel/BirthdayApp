package com.omeram.birthday.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.omeram.birthday.R;
import com.omeram.birthday.viewmodel.BirthdayViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class DetailsFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.nameText)
    TextInputEditText nameText;

    @BindView(R.id.dateText)
    TextView dateText;

    @BindView(R.id.selectDateButton)
    Button selectDateButton;

    @BindView(R.id.addPictureButton)
    Button addPictureButton;

    @BindView(R.id.showBirthdayButton)
    Button showBirthdayButton;

    private BirthdayViewModel mViewModel;

    static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, container, false);
        ButterKnife.bind(this, view);
        enableBirthdayButton();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(BirthdayViewModel.class);
    }

    @OnTextChanged(value = R.id.nameText, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void nameTextChanged(CharSequence text) {
        enableBirthdayButton();
    }


    @OnTextChanged(value = R.id.dateText, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void dateTextChanged(CharSequence text) {
        enableBirthdayButton();
    }

    private void enableBirthdayButton() {
        showBirthdayButton.setEnabled(!nameText.getText().toString().isEmpty() && !dateText.getText().toString().isEmpty());
    }

    @OnClick(R.id.selectDateButton)
    void onSelectDateButtonClicked() {
        DialogFragment birthdayPicker = new BirthdayPickerFragment();
        birthdayPicker.setTargetFragment(this, 0);
        birthdayPicker.show(getActivity().getSupportFragmentManager(), BirthdayPickerFragment.class.getSimpleName());
    }

    @OnClick(R.id.addPictureButton)
    void onAddPictureButtonClicked() {
    }

    @OnClick(R.id.showBirthdayButton)
    void onShowBirthdayButtonClicked() {
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        dateText.setText(dateFormat.format(calendar.getTime()));
    }

}
