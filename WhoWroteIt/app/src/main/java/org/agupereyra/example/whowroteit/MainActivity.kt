package org.agupereyra.example.whowroteit

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    lateinit var mBookInput : EditText
    lateinit var mTitleText : TextView
    lateinit var mAuthorText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        //TODO:Implement https://codelabs.developers.google.com/codelabs/android-training-asynctask-asynctaskloader/index.html#4
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBookInput = findViewById(R.id.bookInput)
        mTitleText = findViewById(R.id.titleText)
        mAuthorText = findViewById(R.id.authorText)

        val searchBtn = findViewById<Button>(R.id.searchButton)
        searchBtn.setOnClickListener {
            searchBooks()
        }
    }

    private fun searchBooks() {
        val queryString = mBookInput.text.toString()
        val fetchBookTask = FetchBook(mTitleText, mAuthorText)
        fetchBookTask.execute(queryString)
    }

    private class FetchBook(val mTitleText : TextView, val mAuthorText: TextView) : AsyncTask<String, Unit, String>() {

        override fun doInBackground(vararg p0: String?): String {
            if (p0 != null) {
                return NetworkUtils.getBookInfo(p0[0] as String)
            } else {
                return ""
            }
        }

        override fun onPostExecute(result: String?) {
            try {
                val jsonObject = JSONObject(result)
                val itemsArray = jsonObject.getJSONArray("items")

                var i = 0
                var title : String? = null
                var authors : String? = null

                while (i < itemsArray.length() && (authors == null && title == null)) {
                    // Get the current item information.
                    val book = itemsArray.getJSONObject(i);
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

                // If both are found, display the result.
                if (title != null && authors != null) {
                    mTitleText.setText(title)
                    mAuthorText.setText(authors)
                } else {
                    // If none are found, update the UI to
                    // show failed results.
                    mTitleText.setText(R.string.no_results)
                    mAuthorText.setText("")
                }
            } catch(e: JSONException) {
                e.printStackTrace()
                // If none are found, update the UI to
                // show failed results.
                mTitleText.setText(R.string.no_results)
                mAuthorText.setText("")
            }
        }
    }
}