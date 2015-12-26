package com.example;

import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.amazingview.AmazingViewHolder;
import com.example.igor.R;
import rx.functions.Action1;

public class CounterWithTextViewHolder extends AmazingViewHolder implements Action1<Integer> {

    @Bind(R.id.custom_counter)
    TextView counter;
    @Bind(R.id.custom_text)
    TextView text;

    public CounterWithTextViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public CounterWithTextViewHolder setCounter(int number) {
        counter.setText("" + number);
        return this;
    }

    public CounterWithTextViewHolder setTextResource(int resId) {
        text.setText(resId);
        return this;
    }

    @Override
    public void call(Integer number) {
        counter.setText(number.toString());
    }

    @Override
    protected String onClickCallback() {
        return null;
    }
}
