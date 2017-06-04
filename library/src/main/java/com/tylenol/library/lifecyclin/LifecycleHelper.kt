package tylenol.lifecyclin

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistryOwner
import android.util.Log

/**
 * Created by baghyeongi on 2017. 6. 4..
 */

fun LifecycleRegistryOwner.addObserver(lifecycleObservers: LifecycleObserver) {
    if (this.lifecycle != null) this.lifecycle.addObserver(lifecycleObservers)
    else Log.d("Lifecyclin", "No Lifecycle detected in this Owner!")
}

fun LifecycleRegistryOwner.addObserverAll(vararg lifecycleObservers: LifecycleObserver) {
    if (this.lifecycle != null) lifecycleObservers.forEach { this.lifecycle.addObserver(it) }
    else Log.d("Lifecyclin", "No Lifecycle detected in this Owner!")
}

fun LifecycleObserver.registerObserver(lifecycleRegistryOwner: LifecycleRegistryOwner): LifecycleObserver {
    if (lifecycleRegistryOwner.lifecycle != null) lifecycleRegistryOwner.lifecycle.addObserver(this)
    else Log.d("Lifecyclin", "No Lifecycle detected in this Owner!")
    return this
}

fun LifecycleRegistryOwner.addLifecycleUnits(onCreate: (() -> Unit)? = null, onStart: (() -> Unit)? = null, onResume: (() -> Unit)? = null,
                                             onPause: (() -> Unit)? = null, onStop: (() -> Unit)? = null, onDestroy: (() -> Unit)? = null) {
    if (this.lifecycle != null)
        this.lifecycle.addObserver(object : LifecycleObserver {
            fun doOnCreate() = onCreate?.invoke()
            fun doOnStart() = onStart?.invoke()
            fun doOnResume() = onResume?.invoke()
            fun doOnPause() = onPause?.invoke()
            fun doOnStop() = onStop?.invoke()
            fun doOnDestroy() = onDestroy?.invoke()
        })
    else Log.d("Lifecyclin", "No Lifecycle detected in this Owner!")
}

fun LifecycleRegistryOwner.isCreated() = this.lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)
fun LifecycleRegistryOwner.isStart() = this.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
fun LifecycleRegistryOwner.isResumed() = this.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)
fun LifecycleRegistryOwner.isInitialized() = this.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)
fun LifecycleRegistryOwner.isDestroyed() = this.lifecycle.currentState.isAtLeast(Lifecycle.State.DESTROYED)