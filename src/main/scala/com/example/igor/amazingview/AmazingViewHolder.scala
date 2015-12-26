package com.example.igor.amazingview

import android.view.View
import rx.functions.Action1
import android.view.View.OnClickListener

abstract class AmazingViewHolder(itemView: View) {

  def getItemView : View = itemView

  protected def onClickCallback: String

  def setOnClickAction(action: Action1[String]): AmazingViewHolder = {
    val listener = new OnClickListener {
      override def onClick(v: View): Unit = action.call(onClickCallback)
    }
    itemView.setOnClickListener(listener)
    this
  }
}