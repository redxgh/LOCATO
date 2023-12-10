package com.example.locato.Chat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.locato.Chat.model.UserModel
import com.example.locato.Chat.utils.AndroidUtil.passUserModelAsIntent
import com.example.locato.Chat.utils.FirebaseUtil.allUserCollectionReference
import com.example.locato.Chat.utils.FirebaseUtil.isLoggedIn
import com.example.locato.HomeActivity
import com.example.locato.R
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (intent.extras != null) {
            //from notification
            val userId = intent.extras!!.getString("userId")
            allUserCollectionReference().document(userId!!).get()
                .addOnCompleteListener { task: Task<DocumentSnapshot> ->
                    if (task.isSuccessful) {
                        val model = task.result.toObject(UserModel::class.java)
                        val mainIntent = Intent(this, MainActivity::class.java)
                        mainIntent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                        startActivity(mainIntent)
                        val intent = Intent(this, ChatActivity::class.java)
                        passUserModelAsIntent(intent, model!!)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    }
                }
        } else {
            Handler().postDelayed({
                if (isLoggedIn) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                } else {
                    startActivity(Intent(this@SplashActivity, LoginPhoneNumberActivity::class.java))
                }
                finish()
            }, 1000)
        }
    }
}