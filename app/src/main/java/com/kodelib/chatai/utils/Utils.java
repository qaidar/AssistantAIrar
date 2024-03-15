package com.kodelib.chatai.utils;

import android.app.Activity;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class Utils {
    public static boolean isStart = true;
    public static boolean isPrompt = false;

    public static void animate(ViewGroup parent, ViewGroup layout, boolean show) {
        TransitionManager.beginDelayedTransition(parent);
        layout.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    public static void hideStatus(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }



}
