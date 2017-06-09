
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
compile 'com.tylenol.library:lifecyclin:0.2.1'
```

<br/>
<hr/>

## Features
### Add User Defined LifecycleObserver to LifecycleRegistry

First you should define class which inherits LifecycleObserver
```kotlin
class SampleActivith : LifecycleCompatActivity() {
    var myListener = MyListener()
    // ...
}

// ...
class MyListener : LifecycleObserver { /**/ }
```

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
    val listener : MyLocationListener
    onCreate { 
       listener = MyLocationListener()     
    }
    onStart { 
       listener.start()
    }
    onStop { 
       listener.stop()
    }
}
```
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    val service = MyService()
    onResume {
        service.resume()
    }
    onPause {
        service.pause()
    }
    
}
```

### Check Lifecycle Status
check current lifecycle status and return true if current status is matched

```kotlin
if (isCreated()) { // Do something cooler }
```

## ViewModel
### From ViewModelProviders
```kotlin
val myViewModel = MyViewModel().asViewModel(this) // 'this' is AppCompatActivity
myViewModel.getUsers().initObserver(this) {
    user -> doSomething(user)
}
```