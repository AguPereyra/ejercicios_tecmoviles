package com.example.helloworld

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Gravity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Bonjour mon ami :<)", Snackbar.LENGTH_LONG).show()
        }

        // Mostrar la toast en creacion o pausa
        showTheToast()

        // Establecer el titulo de la activity
        supportActionBar?.setTitle("Un titulazo")

        // Estoy seguro que deberia ir en otro lado esto, pero no me anda en fragment
        /*val switch1: View = findViewById(R.id.switch1)
        switch1.setOnClickListener{view ->
            Snackbar.make(view, "Un gran snackbar, hurra :|", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
        //TODO: Add stuff
    }

    override fun onResume() {
        super.onResume()
        //TODO: Add stuff
    }

    override fun onPause() {
        super.onPause()
        // Mostrar la toast en creacion o pausa
        showTheToast()
    }

    override fun onStop() {
        super.onStop()
        //TODO: Add stuff
    }

    override fun onRestart() {
        super.onRestart()
        //TODO: Add stuff
    }

    override fun onDestroy() {
        super.onDestroy()
        //TODO: Add stuff
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //TODO: Add something
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //TODO: Add something
    }

    fun showTheToast() {
        val theToast = Toast.makeText(baseContext, "Que talka", Toast.LENGTH_LONG)
        theToast.setGravity(Gravity.TOP, 0, 0)
        theToast.show()

    }
}