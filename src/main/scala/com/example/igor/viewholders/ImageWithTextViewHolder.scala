package com.example.igor.viewholders

import android.view.View
import android.widget.{ImageView, TextView}
import com.example.igor.amazingview.AmazingViewHolder
import rx.functions.Action1

class ImageWithTextViewHolder(itemView: View) extends AmazingViewHolder(itemView) with Action1[String] {

  import com.example.igor.R

  val image: ImageView = itemView.findViewById(R.id.custom_image).asInstanceOf[ImageView]
  val text: TextView = itemView.findViewById(R.id.custom_text).asInstanceOf[TextView]

  def setImageResource(resId: Int): ImageWithTextViewHolder = {
    image.setImageResource(resId)
    this
  }

  def setTextResource(resId: Int): ImageWithTextViewHolder = {
    text.setText(resId)
    this
  }

  def call(s: String) {
    text.setText(s)
  }

  protected def onClickCallback: String = {
    text.getText.toString
  }
}