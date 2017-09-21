package me.chriskyle.library.support.bottomnavigation;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.chriskyle.library.support.R;

/**
 * Description :
 * <p/>
 * Created by Chris Kyle on 2017/4/4.
 */
public class NonLabelBottomNavigationTab extends BottomNavigationTab {

    public NonLabelBottomNavigationTab(Context context) {
        super(context);
    }

    public NonLabelBottomNavigationTab(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NonLabelBottomNavigationTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NonLabelBottomNavigationTab(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    void init() {
        paddingTopActive = 0;
        paddingTopInActive = 0;

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.non_label_bottom_navigation_item, this, true);
        containerView = view.findViewById(R.id.fixed_bottom_navigation_container);
        labelView = (TextView) view.findViewById(R.id.fixed_bottom_navigation_title);
        iconView = (ImageView) view.findViewById(R.id.fixed_bottom_navigation_icon);
        badgeView = (TextView) view.findViewById(R.id.fixed_bottom_navigation_badge);

        super.init();
    }
}
