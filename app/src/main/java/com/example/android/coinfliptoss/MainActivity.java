package com.example.android.coinfliptoss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity{

    private GestureDetector gestureDetector;

    private ImageView empty, arrow;
    private ArrowHandler arrowHandler;

    private ImageView coin;
    private CoinHandler coinHandler;

    private int outcome;


    /* This function implements all basic functionality on the creation of the app*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onDetectGesture();
    }

    /* This function is used to detect gestures on the screen */
    private void onDetectGesture(){
        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        gestureDetector = new GestureDetector(this, customGestureDetector);

        /* Detect gestures on the arrow icon
         * Highly important to use an empty image view because onFling is not recognised on animation
         * with this method */

        empty = (ImageView) findViewById(R.id.empty_image_view);
        empty.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

    }

    /* The internal class used to detect gestures */
    class CustomGestureDetector  extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            coinHandler = new CoinHandler(coin);
            coinHandler.rollCoin();
            coinHandler.flipCoin();
            arrowHandler.stopArrow();

            return true;
        }

    }

    /* This function implements all function after the user interface is loaded */
    @Override
    protected void onStart(){
        super.onStart();
        init();
    }

    /* This function initialises process*/
    private void init(){

        empty = (ImageView) findViewById(R.id.empty_image_view);
        arrow = (ImageView) findViewById(R.id.arrow_image_view);
        coin = (ImageView) findViewById(R.id.coin_image_view);

        arrowHandler = new ArrowHandler(arrow);
        arrowHandler.animateArrow();

    }

    /* This function restarts process*/
    private void restart(){

        arrowHandler.animateArrow();
    }













    public void onRestart(View view){

        restart();
    }



}
