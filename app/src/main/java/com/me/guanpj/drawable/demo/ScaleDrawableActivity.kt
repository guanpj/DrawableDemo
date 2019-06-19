package com.me.guanpj.drawable.demo

import android.graphics.drawable.ScaleDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class ScaleDrawableActivity : AppCompatActivity() {
    lateinit var scaleDrawable:ScaleDrawable
    var curLevel = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale_drawable)

        scaleDrawable = findViewById<Button>(R.id.button).background as ScaleDrawable
        scaleDrawable.level = 0

        Observable.interval(200, TimeUnit.MILLISECONDS).subscribe {
            mHandler.sendEmptyMessage(1)
        }
    }

    var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            scaleDrawable.level = curLevel
            curLevel += 200
            if (curLevel >= 10000) {
                curLevel = 0
            }
            Log.e("gpj", "level ${curLevel}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeMessages(1)
    }
}