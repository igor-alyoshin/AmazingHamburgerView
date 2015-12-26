package com.example.igor

import android.app.Activity

class SecondActivity extends Activity {
  import android.os.Bundle

  var EXTRA_TITLE = "EXTRA_TITLE"

  override def onCreate(savedInstanceState: Bundle): Unit = {
    import android.widget.TextView

    super.onCreate(savedInstanceState)
    setContentView(R.layout.second_activity)
    findViewById(R.id.title).asInstanceOf[TextView].setText(getIntent.getExtras.getString(Intents.SecondActivity.EXTRA_TITLE))
    setHamburger()
  }

  def setHamburger() {
    import com.example.AmazingHamburgerView
    import com.example.igor.amazingview.{AmazingAbstractController, AmazingHamburgerView}
    import rx.functions.Action1

    val hamburger = findViewById(R.id.hamburger).asInstanceOf[AmazingHamburgerView]
    hamburger.addMainTab(R.drawable.back_btn)
    val mainTabClickAction = new Action1[AmazingAbstractController] {
      override def call(t: AmazingAbstractController): Unit = finish()
    }
    hamburger.setMainTabClickAction(mainTabClickAction)
  }
}