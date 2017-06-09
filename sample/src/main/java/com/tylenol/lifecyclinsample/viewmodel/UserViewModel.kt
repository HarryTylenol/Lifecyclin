package com.tylenol.lifecyclinsample.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.tylenol.lifecyclinsample.extension.UserList
import com.tylenol.lifecyclinsample.model.User
import com.tylenol.lifecyclinsample.util.Android
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.util.*


/**
 * Created by baghyeongi on 2017. 6. 9..
 */
class UserViewModel : ViewModel() {

    internal var userLiveData: MutableLiveData<UserList>

    init {
        userLiveData = MutableLiveData<UserList>()
    }

    // Update Users with Coroutines
    fun fetchUser(data: List<String>) {
        launch(Android) {
            val fetchUser = async(Android) {
                delay(3000) // 3 sec Delay
                Collections.shuffle(data) // Shuffle names
                data.map(::User) // Set it to User
            }
            userLiveData.value = fetchUser.await()
        }
    }

    fun getUsers() = userLiveData

}