package com.example.igor.viewholders

import android.view.View
import android.widget.TextView
import com.example.igor.amazingview.AmazingViewHolder

class CounterWithTextViewHolder(itemView: View) extends AmazingViewHolder(itemView) {

  import com.example.igor.R

  val counter: TextView = itemView.findViewById(R.id.custom_counter).asInstanceOf[TextView]
  val text: TextView = itemView.findViewById(R.id.custom_text).asInstanceOf[TextView]

  def setCounter(number: Int): CounterWithTextViewHolder = {
    counter.setText("" + number)
    this
  }

  def setTextResource(resId: Int): CounterWithTextViewHolder = {
    text.setText(resId)
    this
  }

  val counterChangeAction : Long => Unit = {
    (number: Long) => counter.setText(number.toString)
  }

  protected def onClickCallback: String = {
    null
  }
}