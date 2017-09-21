package me.chriskyle.library.design.bottomsheet;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import me.chriskyle.library.design.R;

/**
 * Description :
 * <p/>
 * Created by Chris Kyle on 2017/3/01.
 */
public class BottomSheet extends Dialog {

    public interface OnBottomSheetItemSelectedListener {

        boolean onBottomSheetItemSelected(int position, int id);
    }

    public interface OnActionClickListener {
        boolean onActionClick(View view);
    }

    private OnBottomSheetItemSelectedListener onBottomSheetItemSelectedListener;
    private OnActionClickListener onActionClickListener;

    protected GridView gridView;
    protected TextView header;
    protected TextView footer;

    public BottomSheet(Context context) {
        super(context, R.style.BottomSheet);
        init();
    }

    public void setHeader(String header) {
        if (TextUtils.isEmpty(header)) {
            this.header.setVisibility(View.INVISIBLE);
        } else {
            this.header.setVisibility(View.VISIBLE);
        }
        this.header.setText(header);
    }

    public void setFooter(String footer) {
        if (TextUtils.isEmpty(footer)) {
            this.footer.setVisibility(View.INVISIBLE);
        } else {
            this.footer.setVisibility(View.VISIBLE);
        }
        this.footer.setText(footer);
    }

    public void hideHeader() {
        header.setVisibility(View.GONE);
    }

    public void hideFooter() {
        footer.setVisibility(View.GONE);
    }

    public void setNumColumns(int cols) {
        gridView.setNumColumns(cols);
    }

    protected int getLayoutId() {
        return R.layout.bottom_sheet;
    }

    private void init() {
        setCancelable(true);

        setContentView(getLayoutId());
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);

        gridView = (GridView) findViewById(R.id.bottom_sheet_body_gv);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onBottomSheetItemSelectedListener != null) {
                    if (onBottomSheetItemSelectedListener.onBottomSheetItemSelected(position, (int) id)) {
                        dismiss();
                    }
                } else {
                    dismiss();
                }
            }
        });

        header = (TextView) findViewById(R.id.bottom_sheet_header_title);

        footer = (TextView) findViewById(R.id.bottom_sheet_footer_action);
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onActionClickListener != null) {
                    if (!onActionClickListener.onActionClick(v)) {
                        dismiss();
                    }
                } else {
                    dismiss();
                }
            }
        });
    }

    public void setAdapter(ListAdapter adapter) {
        gridView.setAdapter(adapter);
    }

    public GridView getGridView() {
        return gridView;
    }

    public void setOnViewItemSelectedListener(OnBottomSheetItemSelectedListener mOnBottomSheetItemSelectedListener) {
        this.onBottomSheetItemSelectedListener = mOnBottomSheetItemSelectedListener;
    }

    public void setOnButtonClickedListener(OnActionClickListener mOnActionClickListener) {
        this.onActionClickListener = mOnActionClickListener;
    }
}
