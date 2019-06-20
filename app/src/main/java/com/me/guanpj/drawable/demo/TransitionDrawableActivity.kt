package com.me.guanpj.drawable.demo

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class TransitionDrawableActivity : AppCompatActivity() {
    lateinit var disposable: Disposable
    var reverse = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_drawable)

        var transitionDrawable = findViewById<ImageView>(R.id.img).background as TransitionDrawable

        Observable.interval(3000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }
                override fun onComplete() {
                }

                override fun onNext(t: Long) {
                    if (!reverse) {
                        transitionDrawable.startTransition(3000)
                        reverse = true
                    } else {
                        transitionDrawable.reverseTransition(3000)
                        reverse = false
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