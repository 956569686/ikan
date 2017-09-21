package me.chriskyle.library.design.bottomsheet;

/**
 * Description :
 * <p/>
 * Created by Chris Kyle on 2017/3/01.
 */
public class BottomSheetItem {

    private int id;
    private int icon;

    private String label = null;
    private boolean selected = false;
    private boolean enable = true;

    public int getId() {
        return id;
    }

    public BottomSheetItem setId(int id) {
        this.id = id;
        return this;
    }

    public boolean isEnable() {
        return enable;
    }

    public BottomSheetItem setEnable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public int getIcon() {
        return icon;
    }

    public BottomSheetItem setIcon(int icon) {
        this.icon = icon;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public BottomSheetItem setLabel(String label) {
        this.label = label;
        return this;
    }

    public boolean isSelected() {
        return selected;
    }

    public BottomSheetItem setSelected(boolean selected) {
        this.selected = selected;
        return this;
    }
}

