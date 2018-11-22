package com.omeram.birthday.di;

import android.app.Application;

import com.omeram.birthday.db.BirthdayDao;
import com.omeram.birthday.db.BirthdayDb;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
class AppModule {

    @Singleton
    @Provides
    BirthdayDb provideDb(Application app) {
        return Room.databaseBuilder(app, BirthdayDb.class,"birthday.db").build();
    }

    @Singleton
    @Provides
    BirthdayDao providePhotoDao(BirthdayDb db) {
        return db.birthdayDao();
    }

}
