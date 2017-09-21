package me.chriskyle.library.design.lceview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.chriskyle.library.design.R;

/**
 * Description :
 * <p/>
 * Created by Chris Kyle on 2017/1/5.
 */
public abstract class AbsLceView extends LinearLayout {

    private OnActionListener onActionListener;

    private TextView des;
    private TextView action;

    public AbsLceView(Context context) {
        super(context);

        initView();
    }

    public AbsLceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    public AbsLceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(getLayoutResourceId(), this);
        des = (TextView) findViewById(R.id.des);
        action = (TextView) findViewById(R.id.action);

        action.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onActionListener) {
                    onActionListener.onIceActionClick();
                }
            }
        });

        bindView();
    }

    protected abstract void bindView();

    protected int getLayoutResourceId() {
        return R.layout.lce_layout;
    }

    public void setDesText(String desText) {
        des.setText(desText);
    }

    public void setDesText(int desText) {
        des.setText(getContext().getString(desText));
    }

    public void setActionText(String actionText) {
        action.setVisibility(VISIBLE);
        action.setText(actionText);
    }

    public void displayAction() {
        action.setVisibility(VISIBLE);
    }

    private void hideActionBtn() {
        action.setVisibility(GONE);
    }

    public void setOnActionListener(OnActionListener onActionListener) {
        this.onActionListener = onActionListener;
    }

    public interface OnActionListener {

        void onIceActionClick();
    }
}
