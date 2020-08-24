package org.gugu.example.helloworld_from0

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Show toast on creation and pause
        showLaTostada()
        // Manually set title
        supportActionBar?.setTitle(R.string.app_title)
    }

    override fun onPause() {
        super.onPause()
        // Show toast on creation and pause
        showLaTostada()
    }

    private fun showLaTostada() {
        val laTostada = Toast.makeText(applicationContext, R.string.la_tostada, Toast.LENGTH_LONG)
        laTostada.setGravity(Gravity.TOP ,400,0)
        laTostada.show()

    }
}