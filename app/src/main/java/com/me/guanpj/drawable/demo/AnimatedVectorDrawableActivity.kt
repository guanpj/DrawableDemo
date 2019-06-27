package com.me.guanpj.drawable.demo

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat

class AnimatedVectorDrawableActivity : AppCompatActivity() {
    private lateinit var animatable2Compat: Animatable2Compat

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_vector_drawable)

        var image = findViewById<ImageView>(R.id.image)
        var animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(this, R.drawable.drawable_animated_vector)
        image.setImageDrawable(animatedVectorDrawableCompat)
        animatable2Compat = image.drawable as Animatable2Compat
        animatable2Compat.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationStart(drawable: Drawable?) {
                Log.e("gpj", "onAnimationStart")
            }
            override fun onAnimationEnd(drawable: Drawable?) {
                Log.e("gpj", "onAnimationEnd")
            }
        })
        animatable2Compat.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        animatable2Compat.stop()
    }
}
