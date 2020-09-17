package org.example.material

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        // Get listener from parent activity
        val listener = activity as loginFragmentListener

        // Set error if password is not between 8 and 16 chars
        view.next_button.setOnClickListener({
            if (!isPasswordValid(password_edit_text.text)) {
                password_text_input.error = getString(R.string.error_password)
            } else {
                // Clear the error
                password_text_input.error = null

                // Navigate
                listener.navigateToMainPage()
            }
        })

        // Clear the error onche the right amount of chars is set
        view.password_edit_text.setOnKeyListener({_, _, _ ->
            if (isPasswordValid(password_edit_text.text)) {
                // Clear the error message
                password_text_input.error = null
            }
            false
        })
        return view
    }

    private fun isPasswordValid(text: Editable?) : Boolean {
        // Check if password is between 8 and 16 characters long
        return text != null && text.length >= 8 && text.length <= 16
    }

    interface loginFragmentListener {
        fun navigateToMainPage() : Unit
    }
}