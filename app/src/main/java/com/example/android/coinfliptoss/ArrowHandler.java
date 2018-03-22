package com.example.android.coinfliptoss;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

/**
 * Created by inser on 3/22/2018.
 */

public class ArrowHandler {

    private AnimationDrawable arrow;
    private ImageView view;

    public ArrowHandler(ImageView view){
        this.view = view;
    }

    /* This function is used to create the moving arrow to indicate swipe movement */
    public void animateArrow(){

        view.setBackgroundDrawable(null);
        view.setBackgroundResource(R.drawable.ic_arrow_slide);
        arrow = (AnimationDrawable) view.getBackground();
        arrow.start();
    }

    /* This function is used to stop the moving arrow */
    public void stopArrow(){

        arrow.stop();
        view.setBackgroundDrawable(null);

    }


}
