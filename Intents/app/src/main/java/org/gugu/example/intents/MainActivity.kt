package org.gugu.example.intents

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private val loginOnClickListener: View.OnClickListener = object : View.OnClickListener {
        override fun onClick(v: View?) {
            login()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val btnSubmit = findViewById<Button>(R.id.submit_btn)
        btnSubmit.setOnClickListener(loginOnClickListener)
    }

    fun login() {
        // Get Username
        val editTextUsername = findViewById<EditText>(R.id.login_user)
        val username = editTextUsername.text
        val secondPageIntent = Intent(this, SecondActivity::class.java)
        secondPageIntent.putExtra("USERNAME", username)
        startActivity(secondPageIntent)
    }
}