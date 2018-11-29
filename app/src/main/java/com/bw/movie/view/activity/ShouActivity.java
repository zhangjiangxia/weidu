package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.bw.movie.R;
import com.bw.movie.view.fragment.CinemaFragment;
import com.bw.movie.view.fragment.FilmFragment;
import com.bw.movie.view.fragment.MyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShouActivity extends AppCompatActivity {

    @BindView(R.id.main_framelayout)
    FrameLayout mainFramelayout;
    @BindView(R.id.show_imag1)
    RadioButton showImag1;
    @BindView(R.id.show_imag2)
    RadioButton showImag2;
    @BindView(R.id.show_imag3)
    RadioButton showImag3;
    private FilmFragment filmFragment;
    private CinemaFragment cinemaFragment;
    private MyFragment myFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou);
        ButterKnife.bind(this);
        changepage(new FilmFragment());

    }
    @OnClick({R.id.show_imag1, R.id.show_imag2, R.id.show_imag3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.show_imag1:
                if (filmFragment==null){
                    filmFragment = new FilmFragment();
                }
                changepage(filmFragment);

                break;
            case R.id.show_imag2:
                    cinemaFragment = new CinemaFragment();
                changepage(cinemaFragment);
                break;
            case R.id.show_imag3:

                if (myFragment==null){
                    myFragment = new MyFragment();
                }
                changepage(myFragment);
                break;
        }
    }
    private  void changepage(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, fragment).commit();
    }
}
