package com.me.guanpj.drawable.demo

import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_animated_image_drawable.*

@RequiresApi(Build.VERSION_CODES.P)
class AnimatedImageDrawableActivity : AppCompatActivity() {
    var mAnimatedImageDrawable: AnimatedImageDrawable? = null

    private val cacheAsset: CacheAsset by lazy {
        CacheAsset(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_image_drawable)

        button.setOnClickListener {
            ImageDecoder.createSource(cacheAsset.file("gif_example.gif")).also { source ->
                ImageDecoder.decodeDrawable(source).also { drawable ->
                    image.setImageDrawable(drawable)
                    if(drawable is AnimatedImageDrawable) {
                        mAnimatedImageDrawable = drawable
                        drawable.start()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mAnimatedImageDrawable?.run { stop() }
    }
}