
!["Lifecyclin"](/assets/lifecyclin-long.png)

A Extension Lifecycle Management Android Library for Kotlin
<hr/>

### Get Started

add maven url in to your build.gradle (Normally in root project)
```groovy
repositories {
    maven {
        url  "http://harrytylenol97.bintray.com/maven"
    }
}
```

And add library into module dependencies
```groovy
compile 'com.tylenol.library:lifecyclin:0.1.2'
```

<hr/>

### Features
##### Add User Defined LifecycleObserver to LifecycleRegistry

First you should define class which inherits LifecycleObserver
```kotlin
var myListener = MyListener()
// ...
class MyListener : LifecycleObserver { /**/ }
```

- Before
```kotlin
lifecycle.addObserver(myListener)
```

- After
```kotlin
addObserver(myListener) // Method 1
val myListener = MyListener().registerObserver(this) // Method 2
```

You can also add 2 more Observers as vararg
```kotlin
addObserver(myListener, myLocationListener, myGoogleApiClientListener)
```

You can define Units(functions) to each lifecycle components (Automatically Add it as Observer to LifecycleRegistry)
```kotlin
val doWhenCreate : Unit = { toast("Created") }
val doWhenPause : Unit  = { toast("Paused") }
addLifecycleUnits(onCreate = doWhenCreate, onPause = doWhenPause)

addLifecycleUnits(onResume = { toast("Resumed") }, onPause = { toast("Destroyed") })
```

##### Check Lifecycle Status
check current lifecycle status and return true if current status is matched

- Before
```kotlin
if (lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)) {
  // Do something
}
```

- After
```kotlin
if (isCreated()) {
  // Do something cooler
}
```
