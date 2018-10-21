package com.example.eslam.bigtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mako on 1/13/2017.
 */
public class dataFragmentMou extends Fragment {
    View view;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mountains, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager_mou);
        viewPager.setAdapter(new sliderAdapter(getChildFragmentManager()));
        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs_mou);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Mountains");
    }
    private class sliderAdapter extends FragmentPagerAdapter{

        final  String tabs[]={"Videos", "Photos" ,"News"};
        public sliderAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 2 :
                    NewsMou newsmou = new NewsMou();
                    return newsmou;
                case 1 :
                    PhotosMou photosmou = new PhotosMou();
                    return photosmou;
                case 0:
                    VideosMou videosmou = new VideosMou();
                    return videosmou ;
            }
            return null;
        }

        @Override
        public int getCount()
        {
            return 3;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }
}
