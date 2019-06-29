package com.me.guanpj.drawable.demo

import android.content.Context
import android.graphics.ImageDecoder
import android.graphics.drawable.Animatable2
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_animated_image_drawable.*
import kotlinx.coroutines.*

class AnimatedImageDrawableActivity : AppCompatActivity() {
    private val cacheAsset: CacheAsset by lazy {
        CacheAsset(this)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_image_drawable)
        //image.setImageDrawable(resources.getDrawable(R.drawable.ic_arrow))
        button.setOnClickListener {
            //Glide.with(this@AnimatedImageDrawableActivity).load(R.drawable.giphy).into(image)
            var drawable = ImageDecoder.decodeDrawable(ImageDecoder.createSource(resources, R.drawable.giphy))
            image.setImageDrawable(drawable)
            if(drawable is Animatable2) {
                drawable.start()
            }
            /*ImageDecoder.createSource(resources, R.drawable.giphy).also { source ->
                ImageDecoder.decodeDrawable(source).also { drawable ->
                    image.setImageDrawable(drawable)
                    if(drawable is Animatable2) {
                        drawable.start()
                    }
                }
            }*/
            /*ImageDecoder.createSource(cacheAsset.file("giphy.gif")).also { source ->
                ImageDecoder.decodeDrawable(source).also { drawable ->
                    image.setImageDrawable(drawable)
                    if(drawable is Animatable2) {
                        drawable.start()
                    }
                }
            }*/
            /*ImageDecoder.createSource(cacheAsset.file("StylingAndroid.png")).also { source ->
                ImageDecoder.decodeBitmap(source).also { bitmap ->
                    image.setImageBitmap(bitmap)
                }
            }*/
        }
    }
}