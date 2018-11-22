package com.omeram.birthday.db;


import com.omeram.birthday.model.Birthday;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Birthday.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class BirthdayDb extends RoomDatabase {

    abstract public BirthdayDao birthdayDao();

}
