package me.chriskyle.library.toolkit.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FontUtil {

    private static Typeface mTypeface;

    public static View applyFontByInflate(Context context, int id, ViewGroup root, boolean attachToRoot) {
        View view = LayoutInflater.from(context).inflate(id, root, attachToRoot);
        if ((view instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                applyFont(context, viewGroup.getChildAt(i));
            }
        } else if ((view instanceof TextView)) {
            ((TextView) view).setTypeface(getDefaultTypeface(context));
        }
        return view;
    }

    private static void applyFont(Context context, View view) {
        if ((view instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                applyFont(context, viewGroup.getChildAt(i));
            }
        } else if ((view instanceof TextView)) {
            ((TextView) view).setTypeface(getDefaultTypeface(context));
        }
    }

    public static Typeface getDefaultTypeface(Context context) {
        if (mTypeface == null) {
            AssetManager assets = context.getAssets();
            try {
                mTypeface = Typeface.createFromAsset(assets, "fonts/LanTingJH.ttf");
            } catch (Throwable tr) {
            }
        }
        return mTypeface;
    }
}
