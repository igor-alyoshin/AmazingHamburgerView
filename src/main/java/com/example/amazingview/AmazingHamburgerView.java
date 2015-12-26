package com.example.amazingview;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.example.igor.R;
import rx.functions.Action1;

import java.util.ArrayList;
import java.util.List;

public class AmazingHamburgerView extends FrameLayout {

    final static float CLOSE_MENU_MAIN_TAB_ROTATION = 0;
    final static float OPEN_MENU_MAIN_TAB_ROTATION = 90;
    final static float OPEN_MENU_BACKGROUND_ALPHA = 0.8f;
    final static int DURATION = 250;

    private TabsWrapper tabsWrapper;
    private int verticalSpacing = 0;
    private int horizontalSpacing = 0;

    public AmazingHamburgerView(Context context) {
        super(context);
    }

    public AmazingHamburgerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AmazingHamburgerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AmazingHamburgerView);
        init(a);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AmazingHamburgerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.AmazingHamburgerView, defStyleAttr, defStyleRes);
        init(a);
    }

    private void init(TypedArray a) {
        verticalSpacing = a.getDimensionPixelOffset(R.styleable.AmazingHamburgerView_verticalSpacing, 0);
        horizontalSpacing = a.getDimensionPixelOffset(R.styleable.AmazingHamburgerView_horizontalSpacing, 0);
        a.recycle();
    }

    public TabsWrapper addMainTab(int resId) {
        ColorDrawable fromDrawable = new ColorDrawable(0);
        ColorDrawable toDrawable = new ColorDrawable((((int) (255 * OPEN_MENU_BACKGROUND_ALPHA)) << 24));
        ColorDrawable[] color = {fromDrawable, toDrawable};
        TransitionDrawable transitionDrawable = new TransitionDrawable(color);
        if (Build.VERSION.SDK_INT >= 16) {
            setBackground(transitionDrawable);
        } else {
            setBackgroundDrawable(transitionDrawable);
        }
        removeAllViewsInLayout();

        ImageView imageView = new ImageView(getContext());
        FrameLayout.LayoutParams fp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fp.gravity = Gravity.BOTTOM;
        imageView.setImageResource(resId);
        imageView.setLayoutParams(fp);
        imageView.setRotation(CLOSE_MENU_MAIN_TAB_ROTATION);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mainTabClickAction.call(new AmazingAnimateController(AmazingHamburgerView.this));
            }
        });
        addView(imageView);
        imageView.setTranslationX(getHorizontalSpacing());
        imageView.setTranslationY(-getVerticalSpacing());
        tabsWrapper = new TabsWrapper(imageView);
        return tabsWrapper;
    }

    private Action1<AmazingAbstractController> mainTabClickAction = new Action1<AmazingAbstractController>() {
        @Override
        public void call(AmazingAbstractController controller) {
            View mainTab = tabsWrapper.getMainTab();
            float rotationCheck = mainTab.getRotation() % OPEN_MENU_MAIN_TAB_ROTATION;
            if (rotationCheck != 0) {
                rotationCheck %= CLOSE_MENU_MAIN_TAB_ROTATION;
            }
            if (rotationCheck != 0) {
                return;
            }
            float startRotation = mainTab.getRotation();
            controller.changeMainTab();
            controller.changeBackgroundAlpha();
            if (startRotation == CLOSE_MENU_MAIN_TAB_ROTATION) {
                controller.showAll();
            } else {
                controller.hideAll();
            }
        }
    };

    TabsWrapper getTabsWrapper() {
        return tabsWrapper;
    }

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
            FrameLayout.LayoutParams fp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            fp.gravity = Gravity.BOTTOM;
            holder.itemView.setLayoutParams(fp);
            holder.itemView.setVisibility(INVISIBLE);
            addView(holder.itemView);
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

    @Override
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState ss = (SavedState) parcelable;
        super.onRestoreInstanceState(ss.getSuperState());
        if (ss.opened) {
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (Build.VERSION.SDK_INT >= 16) {
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                    mainTabClickAction.call(new AmazingInstantController(AmazingHamburgerView.this));
                }
            });
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.opened = tabsWrapper.getMainTab().getRotation() == OPEN_MENU_MAIN_TAB_ROTATION;
        return ss;
    }

    public int getVerticalSpacing() {
        return verticalSpacing;
    }

    public int getHorizontalSpacing() {
        return horizontalSpacing;
    }

    public void setMainTabClickAction(Action1<AmazingAbstractController> action) {
        mainTabClickAction = action;
    }

    static class SavedState extends BaseSavedState {

        boolean opened = false;

        SavedState(Parcel source) {
            super(source);
            opened = source.readInt() == 1;
        }

        private SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(opened ? 1 : 0);
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {

            @Override
            public SavedState createFromParcel(Parcel source) {
                return new SavedState(source);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
