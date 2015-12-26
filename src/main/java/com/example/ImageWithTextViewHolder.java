package com.example;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.amazingview.AmazingViewHolder;
import com.example.igor.R;
import rx.functions.Action1;

public class ImageWithTextViewHolder extends AmazingViewHolder implements Action1<String> {

    @Bind(R.id.custom_image)
    ImageView image;
    @Bind(R.id.custom_text)
    TextView text;

    public ImageWithTextViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public ImageWithTextViewHolder setImageResource(int resId) {
        image.setImageResource(resId);
        return this;
    }

    public ImageWithTextViewHolder setTextResource(int resId) {
        text.setText(resId);
        return this;
    }

    @Override
    public void call(String s) {
        text.setText(s);
    }

    @Override
    protected String onClickCallback() {
        return text.getText().toString();
    }
}
