package com.omeram.birthday.db;

import android.net.Uri;
import androidx.room.TypeConverter;

import java.util.Date;

public class Converters {

    @TypeConverter
    public Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }

    @TypeConverter
    public String fromUri(Uri uri) {
        return uri == null ? null : uri.getPath();
    }

    @TypeConverter
    public Uri toUri(String path) {
        if (path == null) {
            return null;
        } else {
            return Uri.parse(path);
        }
    }
}