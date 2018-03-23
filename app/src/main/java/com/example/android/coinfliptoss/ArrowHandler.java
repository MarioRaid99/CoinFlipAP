package com.example.android.coinfliptoss;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by inser on 3/22/2018.
 */

class ArrowHandler {

    private AnimationDrawable arrow;
    private ImageView view;
    private TextView slide;

    ArrowHandler(ImageView view, TextView slide){
        this.view = view;
        this.slide = slide;
    }

    /* This function is used to create the moving arrow to indicate swipe movement */
    void animateArrow(){

        slide.setText("Slide to roll");
        view.setBackgroundDrawable(null);
        view.setBackgroundResource(R.drawable.ic_arrow_slide);
        arrow = (AnimationDrawable) view.getBackground();
        arrow.start();
    }

    /* This function is used to stop the moving arrow */
    void stopArrow(){

        arrow.stop();
        view.setBackgroundDrawable(null);

    }


}
