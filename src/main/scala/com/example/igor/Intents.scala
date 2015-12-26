package com.example.igor

object Intents {

  object SecondActivity {

    import android.content.Context

    val EXTRA_TITLE = "EXTRA_TITLE"

    def start(ctx : Context, title : String) {
      import android.content.Intent

      val intent = new Intent(ctx, classOf[SecondActivity])
      intent.putExtra(EXTRA_TITLE, title)
      ctx.startActivity(intent)
    }
  }
}