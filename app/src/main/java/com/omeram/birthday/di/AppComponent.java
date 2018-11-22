package com.omeram.birthday.di;

import android.app.Application;

import com.omeram.birthday.BirthdayApp;
import com.omeram.birthday.viewmodel.BirthdayViewModel;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class,})
public interface AppComponent {

    void inject(BirthdayApp birthdayApp);

    void inject(BirthdayViewModel birthdayViewModel);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
