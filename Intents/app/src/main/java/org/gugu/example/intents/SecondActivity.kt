package org.gugu.example.intents

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private val shareOnClickListener: View.OnClickListener = object : View.OnClickListener {
        override fun onClick(v: View?) {
            composeShare()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_mails)

        val username = intent?.extras?.get("USERNAME") ?: "username"
        val welcomeTextView = findViewById<TextView>(R.id.welcome_user_textView)
        welcomeTextView.text = getString(R.string.welcome_user_txt, username)

        val shareBtn = findViewById<Button>(R.id.share_btn)
        shareBtn.setOnClickListener(shareOnClickListener)
    }

    fun composeShare() {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.setType("text/html")
        sharingIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(getString(R.string.email_body)))
        //sharingIntent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.email_sendto))
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.sharing_intent_txt)))
    }
}