package com.omeram.birthday.ui;

import com.omeram.birthday.R;
import com.omeram.birthday.vo.AgeCount;
import com.omeram.birthday.vo.AgePeriod;
import com.omeram.birthday.vo.AgeView;
import com.omeram.birthday.vo.ScreenView;

import java.util.*;

class ViewUtils {

    private static ScreenView yellowScreen = new ScreenView(R.drawable.android_elephant_popup, R.drawable.default_place_holder_yellow);
    private static ScreenView blueScreen = new ScreenView(R.drawable.android_pelican_popup, R.drawable.default_place_holder_blue);
    private static ScreenView greenScreen = new ScreenView(R.drawable.android_fox_popup, R.drawable.default_place_holder_green);

    static AgeView convertDateToView(Date date) {
        Calendar today = Calendar.getInstance();
        Calendar birthday = Calendar.getInstance();
        birthday.setTime(date);
        AgeView ageView = new AgeView();
        int years = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        if (years > 0) {
            ageView.setAgePeriod(AgePeriod.YEARS);
            ageView.setAgeCount(ageCountToAsset(years));
            return ageView;
        } else {
            int months = today.get(Calendar.MONTH) - birthday.get(Calendar.MONTH);
            ageView.setAgePeriod(AgePeriod.MONTHS);
            ageView.setAgeCount(ageCountToAsset(months));
            return ageView;
        }
    }

    private static AgeCount ageCountToAsset(int count) {
        return AgeCount.findByNumber(count);
    }

    static ScreenView getRandomScreen() {
        List<ScreenView> screens = new ArrayList<>();
        screens.add(yellowScreen);
        screens.add(blueScreen);
        screens.add(greenScreen);
        return screens.get(new Random().nextInt(screens.size()));
    }
}
