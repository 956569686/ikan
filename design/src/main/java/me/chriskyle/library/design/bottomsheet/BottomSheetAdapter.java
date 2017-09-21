package me.chriskyle.library.design.bottomsheet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.chriskyle.library.design.R;

/**
 * Description :
 * <p/>
 * Created by Chris Kyle on 2017/3/01.
 */
public class BottomSheetAdapter extends BaseAdapter {

    private Context context;
    private List<BottomSheetItem> items;

    public BottomSheetAdapter(Context context, List<BottomSheetItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public BottomSheetItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_item, parent, false);
        ImageView vIcon = (ImageView) view.findViewById(R.id.sheet_item_icon);
        TextView vLabel = (TextView) view.findViewById(R.id.sheet_item_title);

        BottomSheetItem item = getItem(position);

        vIcon.setVisibility(View.VISIBLE);
        vIcon.setBackgroundResource(item.getIcon());
        vLabel.setText(item.getLabel());

        return view;
    }
}
