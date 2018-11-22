package com.omeram.birthday.db;


import com.omeram.birthday.model.Birthday;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface BirthdayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Birthday birthday);

    @Query("SELECT * FROM Birthday")
    LiveData<List<Birthday>> loadBirthdays();

    @Query("SELECT * FROM Birthday WHERE `id` = :id")
    LiveData<Birthday> loadBirthdayById(int id);

}
