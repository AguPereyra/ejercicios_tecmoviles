package org.agupereyra.example.whowroteit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.loader.app.LoaderManager
import androidx.loader.content.AsyncTaskLoader
import androidx.loader.content.Loader
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {

    lateinit var mBookInput : EditText
    lateinit var mTitleText : TextView
    lateinit var mAuthorText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportLoaderManager.getLoader<AsyncTaskLoader<String>>(0) != null) {
            supportLoaderManager.initLoader(0, null, this)
        }

        mBookInput = findViewById(R.id.bookInput)
        mTitleText = findViewById(R.id.titleText)
        mAuthorText = findViewById(R.id.authorText)

        val searchBtn = findViewById<Button>(R.id.searchButton)
        searchBtn.setOnClickListener {
            searchBooks(it)
        }
    }

    private fun searchBooks(view : View) {
        val queryString = mBookInput.text.toString()
        // Hide keyboard
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        // Check for network connectivity and query string not empty
        if (isNetworkConnected() && !queryString.isBlank()) {
            // Search
            val queryBundle = Bundle()
            queryBundle.putString("queryString", queryString)
            supportLoaderManager.restartLoader(0, queryBundle, this)
            mAuthorText.text = ""
            mTitleText.text = getString(R.string.loading)
        } else {
            mAuthorText.text = ""
            if (queryString.isBlank()) {
                mTitleText.text = getString(R.string.no_serach_term)
            } else {
                mTitleText.text = getString(R.string.no_network)
            }
        }
    }

    private fun isNetworkConnected() : Boolean {
        // Get connectivity API service
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // Get Network Info
        val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        val conStatus = networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        if (conStatus == null) {
            return false
        } else {
            return conStatus
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        var queryString : String
        if (args?.getString("queryString") != null) {
            queryString = args.getString("queryString") as String
        } else {
            queryString = ""
        }
        return BookLoader(this, queryString)
    }

    override fun onLoadFinished(loader: Loader<String>, data: String?) {
        try {
            // Convert the response into a JSON object.
            val jsonObject = JSONObject(data)
            // Get the JSONArray of book items.
            val itemsArray = jsonObject.getJSONArray("items")

            // Initialize iterator and results fields.
            var i = 0
            var title: String? = null
            var authors: String? = null

            // Look for results in the items array, exiting
            // when both the title and author
            // are found or when all items have been checked.
            while (i < itemsArray.length() &&
                (authors == null && title == null)
            ) {
                // Get the current item information.
                val book = itemsArray.getJSONObject(i)
                val volumeInfo = book.getJSONObject("volumeInfo");

                // Try to get the author and title from the current item,
                // catch if either field is empty and move on.
                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (e: Exception) {
                    e.printStackTrace();
                }

                // Move to the next item.
                i++;
            }

            // Display result
            if (title != null && authors != null) {
                mTitleText.text = title
                mAuthorText.text = authors
            } else {
                mTitleText.text = getString(R.string.no_results)
                mAuthorText.text = ""
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        }

    override fun onLoaderReset(loader: Loader<String>) {
        TODO("Not yet implemented")
    }
}