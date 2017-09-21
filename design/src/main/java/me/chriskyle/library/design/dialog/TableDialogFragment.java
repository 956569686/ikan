package me.chriskyle.library.design.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import me.chriskyle.library.design.R;

/**
 * Description :
 * <p/>
 * Created by Chris Kyle on 2017/2/14.
 */
public final class TableDialogFragment extends SimpleDialogFragment {

    public static final class ActionDialogBuilder extends SimpleDialogBuilder<TableDialogFragment> {

        public ActionDialogBuilder() {
            super();
        }

        public TableDialogFragment create() {
            TableDialogFragment fragment = new TableDialogFragment();
            fragment.setArguments(data);
            fragment.setOnDialogActionClickListener(actionClickListener);

            return fragment;
        }
    }

    public TableDialogFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_dialog_action, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView vActionAbove = (TextView) view.findViewById(R.id.action_above);
        String actionAboveText = getArguments().getString(K_ACTION_ABOVE);
        if (!TextUtils.isEmpty(actionAboveText)) {
            vActionAbove.setVisibility(View.VISIBLE);
            vActionAbove.setText(actionAboveText);
            bindActionListener(vActionAbove);
        } else {
            vActionAbove.setVisibility(View.GONE);
        }

        TextView vActionMiddle = (TextView) view.findViewById(R.id.action_middle);
        String actionMiddleText = getArguments().getString(K_ACTION_MIDDLE);
        View middleDivider = view.findViewById(R.id.middle_divider);
        if (!TextUtils.isEmpty(actionMiddleText)) {
            vActionMiddle.setVisibility(View.VISIBLE);
            vActionMiddle.setText(actionMiddleText);
            bindActionListener(vActionMiddle);
            middleDivider.setVisibility(View.VISIBLE);
        } else {
            vActionMiddle.setVisibility(View.GONE);
            middleDivider.setVisibility(View.GONE);
        }

        TextView vActionBelow = (TextView) view.findViewById(R.id.action_below);
        String actionBelowText = getArguments().getString(K_ACTION_BELOW);
        View belowDivider = view.findViewById(R.id.below_divider);
        if (!TextUtils.isEmpty(actionBelowText)) {
            vActionBelow.setVisibility(View.VISIBLE);
            vActionBelow.setText(actionBelowText);
            bindActionListener(vActionBelow);
            belowDivider.setVisibility(View.VISIBLE);
        } else {
            vActionBelow.setVisibility(View.GONE);
            belowDivider.setVisibility(View.GONE);
        }
    }
}
