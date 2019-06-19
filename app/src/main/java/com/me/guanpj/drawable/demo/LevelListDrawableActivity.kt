package com.me.guanpj.drawable.demo

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class LevelListDrawableActivity : AppCompatActivity() {
    lateinit var mImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_list_drawable)

        mImageView = findViewById(R.id.img)
        for (i in 0..15) {
            mHandler.sendEmptyMessageDelayed(i, (1000 * i).toLong())
        }
    }

    var mHandler: Handler = object: Handler() {
        override fun handleMessage(msg: Message?) {
            msg?.what?.let { mImageView.setImageLevel(it%5) }
        }
    }
}