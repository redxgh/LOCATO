package com.example.locato

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View

import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.locato.Chat.ChatActivity
import com.example.locato.Chat.ChatFragment
import com.example.locato.Chat.LoginPhoneNumberActivity
import com.example.locato.Chat.MainActivity
import com.example.locato.Chat.ProfileFragment
import com.example.locato.Chat.model.UserModel
import com.example.locato.Chat.utils.AndroidUtil
import com.example.locato.Chat.utils.FirebaseUtil
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dialog.FormDialogFragment
import dialog.ReportFragment

import java.time.LocalTime
import com.example.locato.ItemsAdapter.Action
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private lateinit var postAdBtn: FloatingActionButton
    private lateinit var profileFragment :ProfileFragment
    private var isLoggedIn = false
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUtil :FirebaseUtil

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scroll_main)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.home_fragment_view, HomeFragment())
                .commitNow()
        }
         profileFragment = ProfileFragment()
 //test logged in
         firebaseUtil = FirebaseUtil
         isLoggedIn = firebaseUtil.isLoggedIn


        //button post ad
        postAdBtn = findViewById(R.id.postAdBtn)
            postAdBtn.setOnClickListener() {
                if (isLoggedIn) {
                    startActivity(Intent(this, PostActivity::class.java))

                }else{
                    startActivity(Intent(this, LoginPhoneNumberActivity::class.java))
                }

            }

//profile
        val imageView = findViewById<ImageView>(R.id.imageView6)
        imageView.setOnClickListener {
            if (isLoggedIn) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.home_fragment_view, profileFragment)
                    .commit()
            }else{
                startActivity(Intent(this, LoginPhoneNumberActivity::class.java))
            }
        }
//Back to home

        val imageView1 = findViewById<ImageView>(R.id.imageView5)
        imageView1.setOnClickListener {
            if (isLoggedIn) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.home_fragment_view, HomeFragment())
                    .commit()
            }else{
                startActivity(Intent(this, LoginPhoneNumberActivity::class.java))

            }

        }
//Chat

        val imageView2 = findViewById<ImageView>(R.id.imageView8)
        imageView2.setOnClickListener {
            if(isLoggedIn) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.home_fragment_view, ChatFragment())
                    .commit()
            }
            else{
                startActivity(Intent(this, LoginPhoneNumberActivity::class.java))

            }

        }





    }



}











