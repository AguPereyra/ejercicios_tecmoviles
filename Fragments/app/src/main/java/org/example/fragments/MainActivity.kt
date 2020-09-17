package org.example.fragments

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), MyFragment.FragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_activity)

        // Set fragment dynamically

        //1. Get a reference to fragment manager
        val fragmentManager = supportFragmentManager

        //2. Start a fragment transaction
        val fragmentTransaction = fragmentManager.beginTransaction()

        //3. Add the fragment to the container
        fragmentTransaction.replace(R.id.dinamic_fragment, MyFragment())

        //4. Commit transaction
        fragmentTransaction.commit()
    }

    override fun navigateToNextFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val myFragment = MyFragment()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(R.id.dinamic_fragment, SecondFragment())
        fragmentTransaction.commit()
    }


}