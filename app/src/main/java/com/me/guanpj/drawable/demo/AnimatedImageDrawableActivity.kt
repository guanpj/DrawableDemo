package com.me.guanpj.drawable.demo

import android.graphics.ImageDecoder
import android.graphics.drawable.Animatable2
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_animated_image_drawable.*
import kotlinx.coroutines.*

class AnimatedImageDrawableActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_image_drawable)
        image.setImageDrawable(resources.getDrawable(R.drawable.ic_arrow))
        /*button.setOnClickListener {
            ImageDecoder.createSource(resources, R.drawable.giphy).also { source ->
                ImageDecoder.decodeDrawable(source).also { drawable ->
                    image.setImageDrawable(drawable)
                    if(drawable is Animatable2) {
                        drawable.start()
                    }
                }
            }
        }*/
    }
}