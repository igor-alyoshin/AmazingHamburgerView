package com.example;

import android.view.View;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.amazingview.AmazingViewHolder;
import com.example.igor.R;
import rx.functions.Action1;

public class ImageWithoutTextViewHolder extends AmazingViewHolder implements Action1<Integer> {

    @Bind(R.id.custom_image)
    ImageView image;

    public ImageWithoutTextViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public ImageWithoutTextViewHolder setImageResource(int resId) {
        image.setImageResource(resId);
        return this;
    }

    @Override
    public void call(Integer resId) {
       image.setImageResource(resId);
    }

    @Override
    protected String onClickCallback() {
        return null;
    }
}
