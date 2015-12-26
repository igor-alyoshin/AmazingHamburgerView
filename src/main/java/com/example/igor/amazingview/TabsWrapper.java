package com.example.igor.amazingview;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class TabsWrapper {

    private final View mainTab;
    private final List<AmazingViewHolder> bottomTabs;
    private final List<AmazingViewHolder> topTabs;

    protected TabsWrapper(View mainTab) {
        this.mainTab = mainTab;
        this.bottomTabs = new ArrayList<>();
        this.topTabs = new ArrayList<>();
    }

    public TabsWrapper addBottomTab(AmazingViewHolder holder) {
        createTab(holder);
        bottomTabs.add(holder);
        return this;
    }

    public TabsWrapper addTopTab(AmazingViewHolder holder) {
        createTab(holder);
        topTabs.add(holder);
        return this;
    }

    private void createTab(AmazingViewHolder holder) {
        FrameLayout.LayoutParams fp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fp.gravity = Gravity.BOTTOM;
        holder.itemView.setLayoutParams(fp);
        holder.itemView.setVisibility(View.INVISIBLE);
        ((ViewGroup) mainTab.getParent()).addView(holder.itemView);
    }

    List<AmazingViewHolder> getBottomTabs() {
        return bottomTabs;
    }

    List<AmazingViewHolder> getTopTabs() {
        return topTabs;
    }

    View getMainTab() {
        return mainTab;
    }
}
