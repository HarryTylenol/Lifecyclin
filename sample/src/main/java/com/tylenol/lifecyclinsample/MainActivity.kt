package com.tylenol.lifecyclinsample

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.tylenol.library.lifecyclin.classes.LifecycleCompatActivity
import com.tylenol.library.lifecyclin.onCreate
import com.tylenol.lifecyclinsample.extension.UserList
import com.tylenol.lifecyclinsample.extension.toast
import com.tylenol.lifecyclinsample.model.User
import com.tylenol.lifecyclinsample.viewmodel.UserViewModel
import tylenol.lifecyclin.asViewModel
import tylenol.lifecyclin.initObserver

class MainActivity : LifecycleCompatActivity() {

    init {

        onCreate {
            setContentView(R.layout.activity_main)

            val userViewModel = UserViewModel().asViewModel(this)

            val recyclerView = findViewById(R.id.rv_user) as RecyclerView
            val button = findViewById(R.id.btn_fetch_user) as Button
            val swipeRefreshLayout = findViewById(R.id.srl_loading) as SwipeRefreshLayout

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = UserAdapter(listOf())

            // Click to fetch user
            button.setOnClickListener {
                swipeRefreshLayout.isRefreshing = true
                userViewModel.fetchUser(listOf("Harry", "Tom", "Alex", "Sam", "Kevin"))
            }
            // Update recyclerview when data change
            userViewModel.getUsers().initObserver(this) {
                swipeRefreshLayout.isRefreshing = false
                if (it != null) toast("${it.size} Users Fetched") else toast("User is Null")
                (recyclerView.adapter as UserAdapter).updateUserList(it as UserList)
            }

        }

    }

    inner class UserAdapter(var list: UserList) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

        fun updateUserList(updatedList: UserList?) {
            this.list = updatedList ?: listOf()
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup?, p1: Int)
                = UserViewHolder(View.inflate(this@MainActivity, R.layout.row_user, null))

        override fun onBindViewHolder(viewholder: UserViewHolder?, position: Int) {
            viewholder?.set(list[position])
        }

        override fun getItemCount() = list.size

        inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            var userName: TextView

            init {
                userName = view.findViewById(R.id.tv_username) as TextView
            }

            fun set(user: User) {
                userName.text = user.name
            }
        }
    }


}
