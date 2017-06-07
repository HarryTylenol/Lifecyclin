package tylenol.lifecyclin

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistryOwner

/**
 * Created by baghyeongi on 2017. 6. 4..
 */

fun <T : LifecycleRegistryOwner> T.addObserver(lifecycleObservers: LifecycleObserver) : T {
    this.lifecycle.addObserver(lifecycleObservers)
    return this
}

fun LifecycleRegistryOwner.addObserverAll(vararg lifecycleObservers: LifecycleObserver) {
    lifecycleObservers.forEach { this.lifecycle.addObserver(it) }
}

fun <T : LifecycleObserver> T.registerObserver(lifecycleRegistryOwner: LifecycleRegistryOwner): T {
    lifecycleRegistryOwner.lifecycle.addObserver(this)
    return this
}

fun LifecycleRegistryOwner.isCreated() = this.lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)
fun LifecycleRegistryOwner.isStart() = this.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
fun LifecycleRegistryOwner.isResumed() = this.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)
fun LifecycleRegistryOwner.isInitialized() = this.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)
fun LifecycleRegistryOwner.isDestroyed() = this.lifecycle.currentState.isAtLeast(Lifecycle.State.DESTROYED)