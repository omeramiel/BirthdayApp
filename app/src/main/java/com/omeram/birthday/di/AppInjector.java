package com.omeram.birthday.di;


import com.omeram.birthday.BirthdayApp;

public class AppInjector {

    private static AppComponent applicationComponent;

    private AppInjector() {}

    public static void init(BirthdayApp birthdayApp) {
        applicationComponent = DaggerAppComponent.builder()
                .application(birthdayApp)
                .build();

        applicationComponent.inject(birthdayApp);
    }

    public static AppComponent getApplicationComponent() {
        return applicationComponent;
    }

}
