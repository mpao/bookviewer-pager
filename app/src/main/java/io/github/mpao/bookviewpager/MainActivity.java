package io.github.mpao.bookviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class MainActivity extends FragmentActivity {

    private static final int NUM_PAGES = 5;
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int margin = getResources().getDisplayMetrics().widthPixels / 15;
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setPageTransformer(true, new StackTransformer());
        mPager.setPageMargin(margin);
        mPager.setPageMarginDrawable(R.drawable.shadow);

        PagerAdapter pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);


    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class SlidePagerAdapter extends FragmentStatePagerAdapter {

        public SlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new Slide();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

    }

}
