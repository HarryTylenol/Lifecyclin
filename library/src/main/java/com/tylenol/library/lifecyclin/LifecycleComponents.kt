package com.tylenol.library.lifecyclin

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.OnLifecycleEvent

/**
 * Created by baghyeongi on 2017. 6. 7..
 */

fun LifecycleRegistryOwner.onCreate(function: (() -> Unit)) {
    this.lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate() = function?.invoke()
    })
}

fun LifecycleRegistryOwner.onResume(function: (() -> Unit)) {
    this.lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume() = function?.invoke()
    })
}

fun LifecycleRegistryOwner.onPause(function: (() -> Unit)) {
    this.lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause() = function?.invoke()
    })
}

fun LifecycleRegistryOwner.onStart(function: (() -> Unit)) {
    this.lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart() = function?.invoke()
    })
}

fun LifecycleRegistryOwner.onStop(function: (() -> Unit)) {
    this.lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop() = function?.invoke()
    })
}

fun LifecycleRegistryOwner.onDestroy(function: (() -> Unit)) {
    this.lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() = function?.invoke()
    })
}