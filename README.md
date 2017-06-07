
!["Lifecyclin"](/assets/lifecyclin-long.png)

A Extension of Android Lifecycle Management for Kotlin

```kotlin
init {
       onStart {
           service.start()
       }
       onResume {
           service.refresh()
       }
 }
```

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
##### <!> You should use support lifecycle library beacuse it needed
```groovy
compile 'com.tylenol.library:lifecyclin:0.1.3'
```

<br/>
<hr/>

## Features
### Add User Defined LifecycleObserver to LifecycleRegistry

First you should define class which inherits LifecycleObserver
```kotlin
class SampleActivith : AppCompatActivity(), LifecycleRegistryOwner {
    var myListener = MyListener()
    // ...
}

// ...
class MyListener : LifecycleObserver { /**/ }
```

#### Before
```kotlin
lifecycle.addObserver(myListener)
```

#### After
```kotlin
addObserver(myListener) // Method 1
val myListener = MyListener().registerObserver(this) // Method 2
```

You can also add 2 more Observers as vararg
```kotlin
addObserver(myListener).addObserver(myLocationListener).addObserver(myGoogleApiClientListener)
addObserver(myListener, myLocationListener, myGoogleApiClientListener)
```

Define Units(functions) to each lifecycle components at once (Automatically Add it as Observer to LifecycleRegistry)
```kotlin
init {
    onCreate { }
    onResume { }
    onPause { }
    onDestory { }
}
```
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    onResume { 
    }
    
    onPause { 
    }
    
}
```

### Check Lifecycle Status
check current lifecycle status and return true if current status is matched

#### Before
```kotlin
if (lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)) {
  // Do something
}
```

#### After
```kotlin
if (isCreated()) {
  // Do something cooler
}
```
