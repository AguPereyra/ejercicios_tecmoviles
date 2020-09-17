package org.example.material

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), LoginFragment.loginFragmentListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.main_activity, LoginFragment())
                .commit()
        }
    }

    fun navigateTo(fragment: Fragment, addToBackstack: Boolean) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_activity, fragment)

        if (addToBackstack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    override fun navigateToMainPage() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val mainPageFragment = MainPageFragment()
        fragmentTransaction.replace(R.id.main_activity, mainPageFragment)
        fragmentTransaction.commit()
    }
}