package me.chriskyle.library.design.lceview;

import android.content.Context;
import android.util.AttributeSet;

import me.chriskyle.library.design.R;

/**
 * Description :
 * <p/>
 * Created by Chris Kyle on 2017/1/5.
 */
public final class ErrorLceView extends AbsLceView {

    public ErrorLceView(Context context) {
        super(context);
    }

    public ErrorLceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ErrorLceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void bindView() {
        setDesText(getResources().getString(R.string.lce_error_des));
        setActionText(getResources().getString(R.string.lce_error_action_des));

        displayAction();
    }
}
