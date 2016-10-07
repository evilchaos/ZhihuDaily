package com.example.liujiachao.zhihudaily.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.liujiachao.zhihudaily.R;

/**
 * Created by liujiachao on 2016/8/31.
 */
public class MenuTextView extends TextView {

    private Context context;
    private boolean subscribed = false;
    private OnMenuHandleListener onMenuHandleListener;

    public MenuTextView(Context context) {
        super(context);
        init(context);
    }

    public MenuTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MenuTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MenuTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        Drawable drawable = subscribed ? context.getResources().getDrawable(R.drawable.menu_arrow) :
                context.getResources().getDrawable(R.drawable.menu_follow);
        setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        int padding = dp2px(context,15);
        int paddingRight = dp2px(context, 40);
        setPadding(padding, padding, paddingRight, padding);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int ev = event.getAction();
        switch (ev){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (onMenuHandleListener != null) {
                    if (subscribed) {
                        onMenuHandleListener.onHandleEvent();
                        //该日报还没有订阅，且点击了MenuTextView中的图片
                    } else if ((event.getX() > getWidth() - getPaddingRight() - getCompoundDrawables()[2].getIntrinsicWidth())
                            && (event.getX() < getWidth() - getPaddingRight())) {
                        onMenuHandleListener.onRedrawMenu();
                        Drawable drawable = context.getResources().getDrawable(R.drawable.menu_arrow);
                        setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
                        //setSubscribed(true);
                    } else {
                        onMenuHandleListener.onHandleEvent();
                    }
                }

                break;
        }
        return true;
    }

    public void setSubs(boolean subscribed) {
        this.subscribed = subscribed;
        Drawable drawable = subscribed ? context.getResources().getDrawable(R.drawable.menu_arrow) :
                context.getResources().getDrawable(R.drawable.menu_follow);
        setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
    }

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public void setOnMenuHandleListener(OnMenuHandleListener listener) {
        this.onMenuHandleListener = listener;
    }

    public interface OnMenuHandleListener {

        void onRedrawMenu();
        void onHandleEvent();
    }
}
