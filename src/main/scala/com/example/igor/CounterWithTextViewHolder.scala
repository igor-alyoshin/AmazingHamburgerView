package com.example.igor

import android.view.View
import com.example.amazingview.AmazingViewHolder
import rx.functions.Action1

class CounterWithTextViewHolder(itemView: View) extends AmazingViewHolder(itemView) with Action1[Integer] {

  import android.widget.TextView

  var counter: TextView = itemView.findViewById(R.id.custom_counter).asInstanceOf[TextView]
  var text: TextView = itemView.findViewById(R.id.custom_text).asInstanceOf[TextView]

  def setCounter(number: Int): CounterWithTextViewHolder = {
    counter.setText("" + number)
    this
  }

  def setTextResource(resId: Int): CounterWithTextViewHolder = {
    text.setText(resId)
    this
  }

  def call(number: Integer) {
    counter.setText(number.toString)
  }

  protected def onClickCallback: String = {
    null
  }
}