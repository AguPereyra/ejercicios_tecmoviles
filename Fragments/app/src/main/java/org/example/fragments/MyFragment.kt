package org.example.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class MyFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val thisView = inflater.inflate(R.layout.fragment_test, container, false) as View
        val listener = activity as FragmentListener
        val nextButton = thisView.findViewById<Button>(R.id.fragment_next_button)

        nextButton.setOnClickListener{
            listener.navigateToNextFragment()
        }
        return thisView
    }

    interface FragmentListener {
        fun navigateToNextFragment() : Unit
    }
}
