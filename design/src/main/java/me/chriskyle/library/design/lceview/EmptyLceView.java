package me.chriskyle.library.design.lceview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import me.chriskyle.library.design.R;

/**
 * Description :
 * <p/>
 * Created by Chris Kyle on 2017/1/5.
 */
public final class EmptyLceView extends AbsLceView {

    public EmptyLceView(Context context) {
        super(context);
    }

    public EmptyLceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyLceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void bindView() {
    }
}
