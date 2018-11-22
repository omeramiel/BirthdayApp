package com.omeram.birthday.viewmodel;

import android.app.Application;
import android.net.Uri;

import com.omeram.birthday.di.AppInjector;
import com.omeram.birthday.model.Birthday;
import com.omeram.birthday.repo.BirthdayRepository;

import java.util.Date;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class BirthdayViewModel extends AndroidViewModel {

    @Inject
    BirthdayRepository mRepository;

    private static final int BIRTHDAY_ID = 0;
    private MutableLiveData<Birthday> mBirthday = new MutableLiveData<>();

    @Inject
    public BirthdayViewModel(@NonNull Application application) {
        super(application);
        AppInjector.getApplicationComponent().inject(this);
        mRepository.loadBirthday(BIRTHDAY_ID).observeForever(birthday -> mBirthday.setValue(birthday));
    }

    public void setBirthdayDetails(String name, Date date, Uri pictureUri) {
        Birthday birthday = new Birthday(BIRTHDAY_ID, name, date);
        if (pictureUri != null) {
            birthday.setPicturePath(pictureUri.getPath());
        }
        mRepository.saveBirthday(birthday);
    }

    public LiveData<Birthday> loadRecentBirthday() {
        return mBirthday;
    }
}
