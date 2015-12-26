package com.example.igor.viewholders

import android.view.View
import android.widget.ImageView
import com.example.igor.amazingview.AmazingViewHolder
import rx.functions.Action1

class ImageWithoutTextViewHolder(itemView: View) extends AmazingViewHolder(itemView) with Action1[Integer] {

  import com.example.igor.R

  val image: ImageView = itemView.findViewById(R.id.custom_image).asInstanceOf[ImageView]

  def setImageResource(resId: Int): ImageWithoutTextViewHolder = {
    image.setImageResource(resId)
    this
  }

  def call(resId: Integer) {
    image.setImageResource(resId)
  }

  protected def onClickCallback: String = {
    null
  }
}