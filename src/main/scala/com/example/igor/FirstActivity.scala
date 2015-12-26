package com.example.igor

import android.app.Activity

class FirstActivity extends Activity {

  import android.content.Context
  import android.os.Bundle
  import android.view.LayoutInflater
  import com.example.amazingview.AmazingHamburgerView

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main)
    setHamburger()
  }

  def setHamburger() {
    import android.view.View
    import com.example.{ImageWithTextViewHolder, ImageWithoutTextViewHolder}
    import rx.functions.Action1
    import rx.lang.scala.subjects.PublishSubject

    val hamburger = findViewById(R.id.hamburger).asInstanceOf[AmazingHamburgerView]
    val notificationsSubject = PublishSubject.apply[Integer]()

    val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE).asInstanceOf[LayoutInflater]

    val clickAction = new Action1[String] {
      override def call(t: String): Unit = Intents.SecondActivity.start(FirstActivity.this, t)
    }

    val home = new ImageWithTextViewHolder(inflater.inflate(R.layout.image_with_text, null, false))
      .setImageResource(R.drawable.custom_button_home)
      .setTextResource(R.string.home)
      .setOnClickAction(clickAction)

    val explore = new ImageWithTextViewHolder(inflater.inflate(R.layout.image_with_text, null, false))
      .setImageResource(R.drawable.custom_button_search)
      .setTextResource(R.string.explore)
      .setOnClickAction(clickAction)

    val messages = new ImageWithTextViewHolder(inflater.inflate(R.layout.image_with_text, null, false))
      .setImageResource(R.drawable.custom_button_messages)
      .setTextResource(R.string.messages)
      .setOnClickAction(clickAction)

    val notifications = new ImageWithTextViewHolder(inflater.inflate(R.layout.image_with_text, null, false))
      .setImageResource(R.drawable.custom_button_notifications)
      .setTextResource(R.string.notifications)
      .setOnClickAction(clickAction)

    val avatar = new ImageWithoutTextViewHolder(inflater.inflate(R.layout.image_without_text, null, false))
      .setImageResource(R.drawable.user_ava)
    val v : View = inflater.inflate(R.layout.counter_with_text, null, false)
    val connecting = new CounterWithTextViewHolder(inflater.inflate(R.layout.counter_with_text, null, false))
      .setTextResource(R.string.connecting)
      .setCounter(18)

    val connections = new CounterWithTextViewHolder(inflater.inflate(R.layout.counter_with_text, null, false))
      .setTextResource(R.string.connections)
      .setCounter(146)

    hamburger.addMainTab(R.drawable.menu_btn_def)
      .addTopTab(home)
      .addTopTab(messages)
      .addTopTab(explore)
      .addTopTab(notifications)
      .addBottomTab(connecting)
      .addBottomTab(connections)
      .addBottomTab(avatar)
  }
}