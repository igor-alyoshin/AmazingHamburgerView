package com.example.amazingview;

import android.graphics.drawable.TransitionDrawable;
import android.util.Log;
import android.view.View;
import rx.functions.Action0;

public class AmazingAnimateController extends AmazingAbstractController {

    public AmazingAnimateController(AmazingHamburgerView amazingHamburgerView) {
        super(amazingHamburgerView);
    }

    @Override
    public void changeBackgroundAlpha() {
        TransitionDrawable transitionDrawable = (TransitionDrawable) amazingHamburgerView.getBackground();
        transitionDrawable.reverseTransition(AmazingHamburgerView.DURATION);
    }

    @Override
    public void changeMainTab() {
        View mainTab = tabsWrapper.getMainTab();
        float startRotation = mainTab.getRotation();
        tabsWrapper.getMainTab().animate()
                .rotation(startRotation == AmazingHamburgerView.CLOSE_MENU_MAIN_TAB_ROTATION ?
                        AmazingHamburgerView.OPEN_MENU_MAIN_TAB_ROTATION :
                        AmazingHamburgerView.CLOSE_MENU_MAIN_TAB_ROTATION)
                .setDuration(AmazingHamburgerView.DURATION);
    }

    @Override
    public void showItem(View v, int x, int y) {
        AnimateObservable.animate(v)
                .visibilityFrom(View.VISIBLE)
                .alphaFrom(0)
                .alphaTo(1)
                .enabledFrom(false)
                .enabledTo(true)
                .translateTo(x, y)
                .duration(AmazingHamburgerView.DURATION).subscribe();
    }

    @Override
    public void hideItem(View v, int x, int y) {
        AnimateObservable.animate(v)
                .visibilityTo(View.INVISIBLE)
                .alphaFrom(1)
                .alphaTo(0)
                .enabledFrom(false)
                .enabledTo(true)
                .translateTo(x, y)
                .duration(AmazingHamburgerView.DURATION).subscribe();
    }
}
