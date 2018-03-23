package com.example.android.coinfliptoss;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.Random;

/**
 * Created by inser on 3/22/2018.
 */

class CoinHandler{

    private ImageView view,match;
    private TextView prediction,outcome,current,high,sequence;
    private RadioGroup radio;
    private AnimationDrawable coin;
    private StringBuilder sequenceBuilder;
    private String predictionString;


    private int highScore, currentScore;

    private Random random;
    private int predict,side;
    private boolean allow;


    CoinHandler(ImageView view, ImageView match, TextView prediction,
                TextView outcome, TextView current,TextView high,RadioGroup radio,TextView sequence)
    {

        this.view = view;
        this.match = match;
        this.prediction = prediction;
        this.outcome = outcome;
        this.current = current;
        this.high = high;
        this.radio = radio;
        this.sequence = sequence;
        sequenceBuilder = new StringBuilder();


        currentScore = highScore = side = 0;
        allow = true;
        random = new Random();

    }


    /* This function is used to create the rolling coin */
    void rollCoin(){

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
    void flipCoin(){

        TranslateAnimation flip = new TranslateAnimation(0,0,0,-100);
        flip.setDuration(1000);
        flip.setRepeatCount(1);
        flip.setStartOffset(0);
        flip.setRepeatMode(2);
        flip.setFillAfter(true);
        view.startAnimation(flip);
        flip.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation) {

                allow = false;
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {

                setCoin();
                setOutcome();
                prediction.setText("Tap to predict");
                radio.clearCheck();
                allow = true;


            }
        });

    }

    /* This function is used to set the value of the coin*/
    private void setCoin(){

        stopCoin();
        side = random.nextInt(2);
        if(side == 0){
            view.setBackgroundResource(R.drawable.heads);
            outcome.setText("Heads");
            predictionString = "H";

        }
        else{
            view.setBackgroundResource(R.drawable.tails);
            outcome.setText("Tails");
            predictionString = "T";
        }

    }

    /* This function is used to set the user's prediction*/
    void setPrediction(int p){

        predict = p;
        if(predict == 0){
            prediction.setText("You predicted Heads");

        }
        else{
            prediction.setText("You predicted Tails");
        }


    }

    /* This function is used to set the outcome of the prediction */
    private void setOutcome(){

        if(predict == side){
            sequenceBuilder.append(predictionString);
            currentScore++;
            if(currentScore > highScore){
                highScore = currentScore;
            }
            current.setText(Integer.toString(currentScore));
            high.setText(Integer.toString(highScore));
            match.setBackgroundResource(R.drawable.won);
            sequence.setText(sequenceBuilder.toString());

        }
        else{
            if(currentScore > highScore){
                highScore = currentScore;
            }
            sequenceBuilder.setLength(0);
            currentScore = 0;
            current.setText(Integer.toString(currentScore));
            high.setText(Integer.toString(highScore));
            match.setBackgroundResource(R.drawable.lost);
            sequence.setText(sequenceBuilder.toString());
        }
    }

    /* This function is used to disable some function while the coin animates */
    boolean getAllowance(){

        return allow;
    }



}
