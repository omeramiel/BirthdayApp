package com.omeram.birthday.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.omeram.birthday.R;
import com.omeram.birthday.model.Birthday;
import com.omeram.birthday.viewmodel.BirthdayViewModel;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static android.app.Activity.RESULT_OK;

public class DetailsFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private static final int REQUEST_TAKE_PICTURE = 0;
    private static final int REQUEST_CHOOSE_FROM_GALLERY = 1;

    private static final String PICTURE_FILENAME = "baby_picture.jpeg";

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
    private Uri mImageUri;
    private Date mDate;

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
        mViewModel.loadRecentBirthday().observe(this, this::updateUi);
    }

    @OnTextChanged(value = R.id.nameText, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void nameTextChanged() {
        enableBirthdayButton();
    }

    @OnTextChanged(value = R.id.dateText, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void dateTextChanged() {
        enableBirthdayButton();
    }

    @OnClick(R.id.selectDateButton)
    void onSelectDateButtonClicked() {
        DialogFragment birthdayPicker = new BirthdayPickerFragment();
        birthdayPicker.setTargetFragment(this, 0);
        birthdayPicker.show(getActivity().getSupportFragmentManager(), BirthdayPickerFragment.class.getSimpleName());
    }

    @OnClick(R.id.addPictureButton)
    void onAddPictureButtonClicked() {
        new MaterialDialog.Builder(getContext())
                .title(R.string.select_source)
                .items("Take Picture", "Gallery")
                .itemsCallback((dialog, itemView, which, text) -> {
                    switch (which) {
                        case REQUEST_TAKE_PICTURE:
                            capturePictureFromCamera();
                            break;

                        case REQUEST_CHOOSE_FROM_GALLERY:
                            pickPictureFromGalley();
                            break;
                    }
                })
                .show();
    }

    @OnClick(R.id.showBirthdayButton)
    void onShowBirthdayButtonClicked() {
        mViewModel.setBirthdayDetails(nameText.getText().toString(), mDate, mImageUri);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
        updateDateUi(calendar.getTime());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CHOOSE_FROM_GALLERY:
                if (resultCode == RESULT_OK) {
                    mImageUri = data.getData();
                }
                break;
        }
    }

    private void updateUi(Birthday birthday) {
        if (birthday != null) {
            nameText.setText(birthday.getName());
            updateDateUi(birthday.getDate());
        }
    }

    private void updateDateUi(Date date) {
        mDate = date;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        dateText.setText(dateFormat.format(date));
    }

    private void enableBirthdayButton() {
        // Enable/disable birthday button according to UI changes
        showBirthdayButton.setEnabled(!nameText.getText().toString().isEmpty() && !dateText.getText().toString().isEmpty());
    }

    private void capturePictureFromCamera() {
        File dir = getContext().getFilesDir();
        File output = new File(dir, PICTURE_FILENAME);
        mImageUri = FileProvider.getUriForFile(getContext(), getContext().getPackageName() + ".provider", output);
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePicture.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
        startActivityForResult(takePicture, REQUEST_TAKE_PICTURE);
    }

    private void pickPictureFromGalley() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, REQUEST_CHOOSE_FROM_GALLERY);
    }

}
