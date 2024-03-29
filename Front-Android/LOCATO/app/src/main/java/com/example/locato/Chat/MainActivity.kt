package com.example.locato.Chat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.locato.Chat.utils.FirebaseUtil.currentUserDetails
import com.example.locato.R
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    lateinit  var bottomNavigationView: BottomNavigationView
    var chatFragment: ChatFragment? = null
    var profileFragment: ProfileFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chatFragment = ChatFragment()
        profileFragment = ProfileFragment()

        bottomNavigationView = findViewById(R.id.bottom_navigation)



        bottomNavigationView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            if (item.itemId == R.id.menu_chat) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame_layout, chatFragment!!).commit()
            }
            if (item.itemId == R.id.menu_profile) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame_layout, profileFragment!!).commit()
            }
            true
        })
        bottomNavigationView.setSelectedItemId(R.id.menu_chat)
        fCMToken
    }

    val fCMToken: Unit
        get() {
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task: Task<String?> ->
                if (task.isSuccessful) {
                    val token = task.result
                    currentUserDetails().update("fcmToken", token)
                }
            }
        }

}