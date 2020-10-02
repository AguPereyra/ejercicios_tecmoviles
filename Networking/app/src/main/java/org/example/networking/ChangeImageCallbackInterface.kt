package org.example.networking

// Callback Interface that the AsyncTask depends on to change the fragment Image based on the passed image URL
interface ChangeImageCallbackInterface {
    fun changeFragmentImage(url: String) : Unit
    fun showErrorMessage(error: String) : Unit
}