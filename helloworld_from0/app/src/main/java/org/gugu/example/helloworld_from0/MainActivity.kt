package org.gugu.example.helloworld_from0

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Show toast on creation and pause
        showLaTostada()
        // Manually set title
        supportActionBar?.setTitle(R.string.app_title)
        // Set snackbar to button click
        val mainButton = findViewById<Button>(R.id.main_button)
        mainButton.setOnClickListener {
            val snack = Snackbar.make(it, "Behold the Snackbar!", Snackbar.LENGTH_LONG)
            snack.setAction("Chau", View.OnClickListener {
                System.out.println("Adios mundo curel...")
            })
            snack.show()
        }
    }

    override fun onPause() {
        super.onPause()
        // Show toast on creation and pause
        showLaTostada()
    }

    private fun showLaTostada() {
        val laTostada = Toast.makeText(baseContext, R.string.la_tostada, Toast.LENGTH_LONG)
        laTostada.setGravity(Gravity.TOP ,400,0)
        laTostada.show()

    }
}