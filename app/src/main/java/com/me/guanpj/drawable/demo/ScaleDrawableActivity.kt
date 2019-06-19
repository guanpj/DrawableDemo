package com.me.guanpj.drawable.demo

import android.graphics.drawable.ScaleDrawable
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ScaleDrawableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale_drawable)

        var scaleDrawable: ScaleDrawable = findViewById<Button>(R.id.button).background as ScaleDrawable
        scaleDrawable.level = 0
    }
}