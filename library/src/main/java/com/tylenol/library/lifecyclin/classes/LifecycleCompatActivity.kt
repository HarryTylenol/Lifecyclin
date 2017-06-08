package com.tylenol.library.lifecyclin.classes

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.support.v7.app.AppCompatActivity

/**
 * Created by baghyeongi on 2017. 6. 8..
 */
open class LifecycleCompatActivity : AppCompatActivity(), LifecycleRegistryOwner {
    val lifecycleRegistry = LifecycleRegistry(this)
    override fun getLifecycle() = lifecycleRegistry
}