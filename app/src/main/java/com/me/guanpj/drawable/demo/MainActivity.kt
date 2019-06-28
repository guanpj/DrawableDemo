package com.me.guanpj.drawable.demo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_bitmap_drawable.setOnClickListener {
            startActivity(Intent(MainActivity@this, BitmapDrawableActivity::class.java))
        }

        btn_gradient_drawable.setOnClickListener {
            startActivity(Intent(MainActivity@this, GradientDrawableActivity::class.java))
        }

        btn_layer_drawable.setOnClickListener {
            startActivity(Intent(MainActivity@this, LayerDrawableActivity::class.java))
        }

        findViewById<Button>(R.id.btn_state_list_drawable).setOnClickListener {
            startActivity(Intent(MainActivity@this, StateListDrawableActivity::class.java))
        }

        btn_level_list_drawable.setOnClickListener {
            startActivity(Intent(MainActivity@this, LevelListDrawableActivity::class.java))
        }

        btn_inset_drawable.setOnClickListener {
            startActivity(Intent(MainActivity@this, InsetDrawableActivity::class.java))
        }

        btn_scale_drawable.setOnClickListener {
            startActivity(Intent(MainActivity@this, ScaleDrawableActivity::class.java))
        }

        btn_clip_drawable.setOnClickListener {
            startActivity(Intent(MainActivity@this, ClipDrawableActivity::class.java))
        }

        btn_rotate_drawable.setOnClickListener {
            startActivity(Intent(MainActivity@this, RotateDrawableActivity::class.java))
        }

        btn_transition_drawable.setOnClickListener {
            startActivity(Intent(MainActivity@this, TransitionDrawableActivity::class.java))
        }

        btn_vector_drawable.setOnClickListener {
            startActivity(Intent(MainActivity@this, VectorDrawableActivity::class.java))
        }

        btn_animated_vector_drawable.setOnClickListener {
            startActivity(Intent(MainActivity@this, AnimatedVectorDrawableActivity::class.java))
        }

        btn_animated_state_list_drawable.setOnClickListener {
            startActivity(Intent(MainActivity@this, AnimatedStateListDrawableActivity::class.java))
        }

        btn_animated_image_drawable.setOnClickListener {
            startActivity(Intent(MainActivity@this, AnimatedImageDrawableActivity::class.java))
        }
    }
}