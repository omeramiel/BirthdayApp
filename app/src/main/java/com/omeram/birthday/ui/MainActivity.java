package com.omeram.birthday.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.omeram.birthday.R;
import com.omeram.birthday.viewmodel.BirthdayViewModel;

public class MainActivity extends AppCompatActivity {

    private BirthdayViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mViewModel = ViewModelProviders.of(this).get(BirthdayViewModel.class);
        if (savedInstanceState == null) {
            navigateToDetails();
        }
    }

    public void navigateToDetails() {
        navigateToFragment(DetailsFragment.newInstance());
    }

    public void navigateToBirthday() {
        navigateToFragment(BirthdayFragment.newInstance());
    }

    private void navigateToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow();
    }

}
