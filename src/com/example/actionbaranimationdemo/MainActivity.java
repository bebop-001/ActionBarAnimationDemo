package com.example.actionbaranimationdemo;
/*
 * Simple application to demonstrate how to animate an actionbar icon.
 * ref: https://developer.android.com/reference/android/view/animation/Animation.html
 * ref: https://developer.android.com/reference/android/view/animation/AnimationUtils.html
 */

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends Activity {
    private static final String logTag = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }
    private static long animationDuration = 1500;
    private static boolean isAnimating = false;
    Animation iconAnimater;
    private void startAnimation(final ActionBar actionBar) {
        /*
         *  found this code at http://stackoverflow.com/questions
         *      /25377239/animating-the-application-icon-or-logo-in-the-actionbar
         *  Can't find it in docs but android.R.id.home seems to be the
         *  actionbar icon.
         */
        actionBar.setIcon(R.drawable.clock3);
        iconAnimater = AnimationUtils.loadAnimation(this, R.anim.icon_rotate);
        iconAnimater.setDuration(animationDuration);
        findViewById(android.R.id.home).startAnimation(iconAnimater);
        isAnimating = true;
        iconAnimater.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                stopAnimation(actionBar);
                isAnimating = false;
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
    private void stopAnimation(ActionBar actionBar) {
        if (isAnimating)
            iconAnimater.cancel();

        AnimationUtils.makeOutAnimation(this, false);
        actionBar.setIcon(R.drawable.ic_launcher);
    }
    public void buttonClick(View view) {
        int id = view.getId();
        ActionBar actionBar = getActionBar();
        if (id == R.id.button1) {
            startAnimation(actionBar);
            Log.i(logTag, "onClick:" + ((Button) findViewById(id)).getText());
        }
        else if (id == R.id.button2) {
            stopAnimation(actionBar);
            Log.i(logTag, "onClick:" + ((Button) findViewById(id)).getText());
        }
        else
            Log.i(logTag, "Unrecognized button.");
    }
}
