package com.example.igor.amazingview;

import android.graphics.Point;
import android.view.View;
import com.example.igor.amazingview.AmazingViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class AmazingAbstractController {

    protected final AmazingHamburgerView amazingHamburgerView;
    protected final AmazingHamburgerView.TabsWrapper tabsWrapper;

    public AmazingAbstractController(AmazingHamburgerView amazingHamburgerView) {
        this.amazingHamburgerView = amazingHamburgerView;
        this.tabsWrapper = amazingHamburgerView.getTabsWrapper();
    }

    public abstract void changeBackgroundAlpha();

    public abstract void changeMainTab();

    public abstract void showItem(View v, int x, int y);

    public abstract void hideItem(View v, int x, int y);

    public void hideAll() {
        for (AmazingViewHolder h : tabsWrapper.getTopTabs()) {
            hideItem(h.getItemView(), 0, 0);
        }

        for (AmazingViewHolder h : tabsWrapper.getBottomTabs()) {
            hideItem(h.getItemView(), 0, 0);
        }
    }

    public void showAll() {
        List<Point> topPositions = new ArrayList<>();
        List<Point> bottomPositions = new ArrayList<>();
        int y = calculateLinePositions(tabsWrapper.getBottomTabs(), bottomPositions, tabsWrapper.getMainTab().getWidth() + amazingHamburgerView.getHorizontalSpacing(), -amazingHamburgerView.getVerticalSpacing());
        calculateLinePositions(tabsWrapper.getTopTabs(), topPositions, amazingHamburgerView.getHorizontalSpacing(), -y - 2 * amazingHamburgerView.getVerticalSpacing());

        for (AmazingViewHolder h : tabsWrapper.getTopTabs()) {
            Point position = topPositions.get(tabsWrapper.getTopTabs().indexOf(h));
            showItem(h.getItemView(), position.x, position.y);
        }
        for (AmazingViewHolder h : tabsWrapper.getBottomTabs()) {
            Point position = bottomPositions.get(tabsWrapper.getBottomTabs().indexOf(h));
            showItem(h.getItemView(), position.x, position.y);
        }
    }

    protected int calculateLinePositions(List<AmazingViewHolder> views, List<Point> positions, int x, int y) {
        List<AmazingViewHolder> copyViews = new ArrayList<>(views);
        int startY = y;
        int startX = x;
        while (copyViews.size() > 0) {
            View item = copyViews.get(0).getItemView();
            if (x + item.getWidth() <= getContainerWidth()) {
                positions.add(new Point(x, startY));
                x += item.getWidth() + amazingHamburgerView.getHorizontalSpacing();
                if (y < item.getHeight())
                    y = item.getHeight();
                copyViews.remove(0);
            } else {
                break;
            }
        }
        fillParentByWidth(positions, getContainerWidth() - x, startX > amazingHamburgerView.getHorizontalSpacing());
        return y;
    }

    private void fillParentByWidth(List<Point> positions, int additionalOffset, boolean firstLine) {
        if (firstLine) {
            for (int i = 0; i < positions.size(); i++) {
                float partOfOffset = (i + 1) / (float) positions.size();
                positions.get(i).x += additionalOffset * partOfOffset;
            }
        } else {
            for (int i = 1; i < positions.size(); i++) {
                float partOfOffset = i / ((float) positions.size() - 1);
                positions.get(i).x += additionalOffset * partOfOffset;
            }
        }
    }

    private int getContainerWidth() {
        return amazingHamburgerView.getWidth() - amazingHamburgerView.getPaddingLeft() - amazingHamburgerView.getPaddingRight();
    }
}
