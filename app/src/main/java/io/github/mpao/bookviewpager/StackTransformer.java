package io.github.mpao.bookviewpager;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

public class StackTransformer implements ViewPager.PageTransformer{

    @Override
    public void transformPage(View view, float position) {

        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            view.setAlpha(1);
            view.setTranslationX(0);

            Log.v("POSITION:", String.valueOf(position));
            /*if( position > -0.1){
                view.setScaleX(1f + position);
            }else
                view.setScaleX(1f - position);*/

        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            view.setAlpha(1 - position/2);
            // Counteract the default slide transition
            view.setTranslationX(pageWidth * -position);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }

    }

}
