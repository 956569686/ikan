package me.chriskyle.library.design.lceview;

import android.content.Context;
import android.util.AttributeSet;

import me.chriskyle.library.design.R;

/**
 * Description :
 * <p/>
 * Created by Chris Kyle on 2017/3/20.
 */
public final class LoadingLceView extends AbsLceView {

    public LoadingLceView(Context context) {
        super(context);
    }

    public LoadingLceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingLceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void bindView() {
        setDesText(getResources().getString(R.string.lce_loading_des));
    }
}
