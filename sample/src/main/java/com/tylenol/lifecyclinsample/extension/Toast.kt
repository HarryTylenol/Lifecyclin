package com.tylenol.lifecyclinsample.extension

import android.content.Context
import android.widget.Toast

/**
 * Created by baghyeongi on 2017. 6. 9..
 */
fun Context.toast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}