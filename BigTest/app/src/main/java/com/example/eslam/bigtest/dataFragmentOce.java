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
public class dataFragmentOce extends Fragment {
    View view;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.oceans, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager_oce);
        viewPager.setAdapter(new sliderAdapter(getChildFragmentManager()));
        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs_oce);
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
        getActivity().setTitle("Oceans");
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
                    NewsOce newsOce = new NewsOce();
                    return newsOce;
                case 1 :
                    PhotosOce photosOce = new PhotosOce();
                    return photosOce;
                case 0:
                    VideosOce videosOce = new VideosOce();
                    return videosOce ;
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
