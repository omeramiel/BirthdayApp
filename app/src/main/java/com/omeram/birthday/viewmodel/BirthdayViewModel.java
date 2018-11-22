package com.omeram.birthday.viewmodel;

import android.app.Application;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import com.omeram.birthday.di.AppInjector;
import com.omeram.birthday.model.Birthday;
import com.omeram.birthday.repo.BirthdayRepository;

import javax.inject.Inject;
import java.util.Date;

public class BirthdayViewModel extends AndroidViewModel {

    @Inject
    BirthdayRepository mRepository;

    private static final int BIRTHDAY_ID = 0;
    private MediatorLiveData<Birthday> mBirthday = new MediatorLiveData<>();
    private Uri mPictureUri;

    @Inject
    public BirthdayViewModel(@NonNull Application application) {
        super(application);
        AppInjector.getApplicationComponent().inject(this);
        LiveData<Birthday> dbResource = mRepository.loadBirthday(BIRTHDAY_ID);
        mBirthday.addSource(dbResource, birthday -> {
            mBirthday.removeSource(dbResource);
            mBirthday.setValue(birthday);
        });
    }

    public void setBirthdayDetails(String name, Date date) {
        Birthday birthday = new Birthday(BIRTHDAY_ID, name, date);
        if (mPictureUri != null) {
            birthday.setPictureUri(mPictureUri);
        } else {
            birthday.setPictureUri(null);
        }
        mRepository.saveBirthday(birthday);
        mBirthday.setValue(birthday);
    }

    public void setBirthdayPicture(Uri pictureUri) {
        mPictureUri = pictureUri;
    }

    public LiveData<Birthday> loadRecentBirthday() {
        return mBirthday;
    }
}
