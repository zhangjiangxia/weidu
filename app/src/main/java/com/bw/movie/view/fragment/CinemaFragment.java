package com.bw.movie.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.view.activity.MapActivity;
import com.bw.movie.view.adapter.CinemaFragmentPageAdapte;
import com.bw.movie.view.fragment2.NearbyFragment;
import com.bw.movie.view.fragment2.ReaommendFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class CinemaFragment extends Fragment implements View.OnClickListener{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView hbalocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cinema, container, false);
        ButterKnife.bind(getActivity());
        tabLayout = view.findViewById(R.id.hba_cinema_tablayout);
        viewPager = view.findViewById(R.id.hba_cinema_viewpager);
        hbalocation = view.findViewById(R.id.hba_location);
        hbalocation.setOnClickListener(this);

        List<Fragment> fragments=new ArrayList<>();
        fragments.add(new ReaommendFragment());
        fragments.add(new NearbyFragment());
        CinemaFragmentPageAdapte myFragmentPageAdapte = new CinemaFragmentPageAdapte(getChildFragmentManager(), fragments);
        viewPager.setAdapter(myFragmentPageAdapte);
        myFragmentPageAdapte.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
        return  view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hba_location:
                Intent  intent=new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
                break;
        }
    }
}
