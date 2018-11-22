package com.omeram.birthday.repo;

import com.omeram.birthday.AppExecutors;
import com.omeram.birthday.db.BirthdayDao;
import com.omeram.birthday.model.Birthday;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;

/**
 * Repository that handles Birthdays.
 */
@Singleton
public class BirthdayRepository {

    private final BirthdayDao birthdayDao;
    private final AppExecutors appExecutors;

    @Inject
    BirthdayRepository(AppExecutors appExecutors, BirthdayDao birthdayDao) {
        this.appExecutors = appExecutors;
        this.birthdayDao = birthdayDao;
    }

    public LiveData<Birthday> loadBirthday(int id) {
        return birthdayDao.loadBirthdayById(id);
    }

    public void saveBirthday(Birthday birthday) {
        appExecutors.diskIO().execute(() -> birthdayDao.insert(birthday));
    }
}
