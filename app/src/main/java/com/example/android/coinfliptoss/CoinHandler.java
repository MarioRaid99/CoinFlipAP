package com.example.android.coinfliptoss;

import android.graphics.drawable.AnimationDrawable;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by inser on 3/22/2018.
 */

public class CoinHandler {

    private ImageView view;
    private AnimationDrawable coin;
    private TranslateAnimation flip;
    private Random random;
    private int outcome;

    public CoinHandler(ImageView view){

        this.view = view;
        outcome = 0;
        random = new Random();

    }


    /* This function is used to create the rolling coin */
    public void rollCoin(){

        view.setImageDrawable(null);
        view.setBackgroundResource(R.drawable.ic_coin_roll);
        coin = (AnimationDrawable) view.getBackground();
        coin.start();
    }

    /* This function is used to stop the rolling coin */
    private void stopCoin(){

        coin.stop();
        view.setBackgroundDrawable(null);
    }


    /* This function is used to simulate a rolling coin moving in the air*/
    public void flipCoin(){

        flip = new TranslateAnimation(0,0,0,-200);
        flip.setDuration(1000);
        flip.setRepeatCount(1);
        flip.setStartOffset(0);
        flip.setRepeatMode(2);
        flip.setFillAfter(true);
        view.startAnimation(flip);
        flip.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {

                setCoin();


            }
        });

    }

    /* This function is used to set the value of the coin*/
    private void setCoin(){

        stopCoin();
        outcome = random.nextInt(2);
        if(outcome == 0){
            view.setBackgroundResource(R.drawable.heads);
        }
        else{
            view.setBackgroundResource(R.drawable.tails);
        }

    }

    public int getCoin(){

        return outcome;
    }


}
