package com.me.guanpj.drawable.demo

import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.RotateDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RotateDrawableActivity : AppCompatActivity() {
    lateinit var rotateDrawable: RotateDrawable
    lateinit var disposable: Disposable
    var curLevel = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotate_drawable)

        rotateDrawable = findViewById<Button>(R.id.button).background as RotateDrawable
        rotateDrawable.level = 0

        Observable.interval(50, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }
                override fun onComplete() {
                }

                override fun onNext(t: Long) {
                    rotateDrawable.level = curLevel
                    curLevel += 200
                    if (curLevel >= 10000) {
                        curLevel = 0
                    }
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}