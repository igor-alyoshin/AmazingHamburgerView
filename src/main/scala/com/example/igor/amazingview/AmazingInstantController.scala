package com.example.igor.amazingview

import android.graphics.drawable.TransitionDrawable
import android.view.View

class AmazingInstantController(amazingHamburgerView : AmazingHamburgerView) extends AmazingAbstractController(amazingHamburgerView) {

  override def changeBackgroundAlpha() {
    val transitionDrawable: TransitionDrawable = amazingHamburgerView.getBackground.asInstanceOf[TransitionDrawable]
    transitionDrawable.reverseTransition(0)
  }

  override def changeMainTab() {
    val mainTab: View = tabsWrapper.getMainTab
    val startRotation: Float = mainTab.getRotation
    mainTab.setRotation(
      if (startRotation == AmazingHamburgerView.CLOSE_MENU_MAIN_TAB_ROTATION)
        AmazingHamburgerView.OPEN_MENU_MAIN_TAB_ROTATION
      else
        AmazingHamburgerView.CLOSE_MENU_MAIN_TAB_ROTATION
    )
  }

  def showItem(v: View, x: Int, y: Int) {
    v.setTranslationX(x)
    v.setTranslationY(y)
    v.setAlpha(1)
    v.setVisibility(View.VISIBLE)
  }

  def hideItem(v: View, x: Int, y: Int) {
    v.setTranslationX(x)
    v.setTranslationY(y)
    v.setAlpha(0)
    v.setVisibility(View.INVISIBLE)
  }
}