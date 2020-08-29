package org.gugu.example.intents

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.Unit

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_mails)

        val username = intent?.extras?.get("USERNAME") ?: "username"
        val welcomeTextView = findViewById<TextView>(R.id.welcome_user_textView)
        welcomeTextView.text = getString(R.string.welcome_user_txt, username)
    }
}