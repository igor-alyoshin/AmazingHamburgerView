package com.example.igor.amazingview;

import android.view.View;
import rx.functions.Action1;

public abstract class AmazingViewHolder {
    public final View itemView;

    public AmazingViewHolder(View itemView) {
        this.itemView = itemView;
    }

    protected abstract String onClickCallback();

    public AmazingViewHolder setOnClickAction(final Action1<String> action) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action.call(onClickCallback());
            }
        });
        return this;
    }
}
