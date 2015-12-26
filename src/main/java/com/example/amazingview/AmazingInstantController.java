package com.example.amazingview;

import android.graphics.drawable.TransitionDrawable;
import android.view.View;

public class AmazingInstantController extends AmazingAbstractController {

    public AmazingInstantController(AmazingHamburgerView amazingHamburgerView) {
        super(amazingHamburgerView);
    }

    @Override
    public void changeBackgroundAlpha() {
        TransitionDrawable transitionDrawable = (TransitionDrawable) amazingHamburgerView.getBackground();
        transitionDrawable.reverseTransition(0);
    }

    @Override
    public void changeMainTab() {
        View mainTab = tabsWrapper.getMainTab();
        float startRotation = mainTab.getRotation();
        tabsWrapper.getMainTab().setRotation(startRotation == AmazingHamburgerView.CLOSE_MENU_MAIN_TAB_ROTATION ?
                AmazingHamburgerView.OPEN_MENU_MAIN_TAB_ROTATION :
                AmazingHamburgerView.CLOSE_MENU_MAIN_TAB_ROTATION);
    }

    @Override
    public void showItem(View v, int x, int y) {
        v.setTranslationX(x);
        v.setTranslationY(y);
        v.setAlpha(1);
        v.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideItem(View v, int x, int y) {
        v.setTranslationX(x);
        v.setTranslationY(y);
        v.setAlpha(0);
        v.setVisibility(View.INVISIBLE);
    }
}
