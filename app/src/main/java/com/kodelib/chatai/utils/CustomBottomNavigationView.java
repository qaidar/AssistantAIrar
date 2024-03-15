package com.kodelib.chatai.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kodelib.chatai.R;

public class CustomBottomNavigationView extends BottomNavigationView {

    private Paint itemPaint;
    private Shader itemShader;
    private int selectedItemPosition = 0;

    public CustomBottomNavigationView(Context context) {
        super(context);
        init();
    }

    public CustomBottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomBottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        itemPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        itemPaint.setStyle(Paint.Style.FILL);
        itemShader = null;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (itemShader != null) {
            canvas.drawRect(getItemLeft(selectedItemPosition), 0, getItemRight(selectedItemPosition), getHeight(), itemPaint);
        }
    }

    private int getItemLeft(int position) {
        return position * getItemWidth();
    }

    private int getItemRight(int position) {
        return (position + 1) * getItemWidth();
    }

    private int getItemWidth() {
        return getWidth() / getMenu().size();
    }

    public void applyItemGradientColor(int position, int colorStart, int colorEnd) {
        selectedItemPosition = position;
        itemShader = new LinearGradient(0, 0, getWidth(), getHeight(),
                colorStart, colorEnd, Shader.TileMode.CLAMP);
        itemPaint.setShader(itemShader);
        invalidate();
    }

    public void resetItemGradientColor() {
        selectedItemPosition = 0;
        itemShader = null;
        itemPaint.setShader(null);
        invalidate();
    }
}
