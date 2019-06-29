package com.me.guanpj.drawable.demo

import android.graphics.ImageDecoder
import android.graphics.drawable.Animatable2
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_animated_image_drawable.*

class AnimatedImageDrawableActivity : AppCompatActivity() {
    private val cacheAsset: CacheAsset by lazy {
        CacheAsset(this)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_image_drawable)

        button.setOnClickListener {
            ImageDecoder.createSource(cacheAsset.file("webp_example.webp")).also { source ->
                ImageDecoder.decodeDrawable(source).also { drawable ->
                    image.setImageDrawable(drawable)
                    if(drawable is Animatable2) {
                        drawable.start()
                    }
                }
            }
        }
    }
}