package com.emt.fatri.machinemonitormvp.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.emt.fatri.machinemonitormvp.R;
import com.emt.fatri.machinemonitormvp.ui.areas.AreasFragment;
import com.emt.fatri.machinemonitormvp.ui.message.MessageFragment;
import com.emt.fatri.machinemonitormvp.ui.settings.MySettingsFragment;
import com.emt.fatri.machinemonitormvp.ui.usercontrol.UserControlFragment;
import com.emt.fatri.machinemonitormvp.view.AlphaTabsIndicator;
import com.emt.fatri.machinemonitormvp.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private AlphaTabsIndicator mAlphaTabsIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NoScrollViewPager mViewPager = findViewById(R.id.mViewPager);
        MyAdapter mainAdapter = new MyAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mainAdapter);
        mViewPager.addOnPageChangeListener(mainAdapter);

        mAlphaTabsIndicator = findViewById(R.id.alphaIndicator);
        mAlphaTabsIndicator.setViewPager(mViewPager);

        mAlphaTabsIndicator.getTabView(0).showPoint();
        mAlphaTabsIndicator.getTabView(1).showNumber(2);
        mAlphaTabsIndicator.getTabView(2).showPoint();
        mAlphaTabsIndicator.getTabView(3).showPoint();

    }

    private class MyAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

        private List<Fragment> fragments = new ArrayList<>();

        public MyAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(new AreasFragment());
            fragments.add(new MessageFragment());
            fragments.add(new UserControlFragment());
            fragments.add(new MySettingsFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (0 == position) {
                mAlphaTabsIndicator.getTabView(0).showNumber(mAlphaTabsIndicator.getTabView(0).getBadgeNumber() - 1);
            } else if (2 == position) {
                mAlphaTabsIndicator.getCurrentItemView().removeShow();
            } else if (3 == position) {
                mAlphaTabsIndicator.removeAllBadge();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
