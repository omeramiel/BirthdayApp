package com.omeram.birthday.vo;

import com.omeram.birthday.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

import static com.omeram.birthday.vo.AgePeriod.MONTHS;
import static com.omeram.birthday.vo.AgePeriod.YEARS;

@Retention(RetentionPolicy.SOURCE)
@IntDef({MONTHS, YEARS})
public @interface AgePeriod {
     int YEARS = R.string.years;
     int MONTHS = R.string.months;
}