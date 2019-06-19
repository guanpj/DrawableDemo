package com.me.guanpj.drawable.demo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_bitmap_drawable).setOnClickListener {
            startActivity(Intent(MainActivity@this, BitmapDrawableActivity::class.java))
        }

        findViewById<Button>(R.id.btn_gradient_drawable).setOnClickListener {
            startActivity(Intent(MainActivity@this, GradientDrawableActivity::class.java))
        }

        findViewById<Button>(R.id.btn_layer_drawable).setOnClickListener {
            startActivity(Intent(MainActivity@this, LayerDrawableActivity::class.java))
        }

        findViewById<Button>(R.id.btn_state_list_drawable).setOnClickListener {
            startActivity(Intent(MainActivity@this, StateListDrawableActivity::class.java))
        }
    }
}