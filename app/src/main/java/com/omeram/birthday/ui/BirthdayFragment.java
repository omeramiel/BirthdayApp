package com.omeram.birthday.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.omeram.birthday.R;
import com.omeram.birthday.model.Birthday;
import com.omeram.birthday.viewmodel.BirthdayViewModel;
import com.omeram.birthday.vo.AgeView;
import com.omeram.birthday.vo.ScreenView;

/**
 * A fragment representing the birthday
 */
public class BirthdayFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.background)
    ImageView background;

    @BindView(R.id.picture)
    ImageView picture;

    @BindView(R.id.nameText)
    TextView nameText;

    @BindView(R.id.ageText)
    TextView ageText;

    @BindView(R.id.ageImage)
    ImageView ageImage;

    private BirthdayViewModel birthdayViewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BirthdayFragment() {
    }

    public static BirthdayFragment newInstance() {
        return new BirthdayFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.birthday_fragment, container, false);
        ButterKnife.bind(this, view);
        ((MainActivity)getActivity()).setCloseIcon(toolbar);
        randomizeScreen();
        return view;
    }

    private void randomizeScreen() {
        ScreenView screenView = ViewUtils.getRandomScreen();
        background.setImageResource(screenView.getBackground());
        picture.setImageResource(screenView.getDefaultPicture());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        birthdayViewModel = ViewModelProviders.of(getActivity()).get(BirthdayViewModel.class);
        birthdayViewModel.loadRecentBirthday().observe(this, this::updateUi);
    }

    private void updateUi(Birthday birthday) {
        if (birthday != null) {
            AgeView ageView = ViewUtils.convertDateToView(birthday.getDate());
            if (ageView != null) {
                ageText.setText(getString((R.string.baby_age), getString(ageView.getAgePeriod())));
                ageImage.setImageResource(ageView.getAgeCount().getResource());
            }
            nameText.setText(getString((R.string.baby_name), birthday.getName()));
            if (birthday.getPictureUri() != null) {
                picture.setImageURI(birthday.getPictureUri());
            }
        }
    }

}
