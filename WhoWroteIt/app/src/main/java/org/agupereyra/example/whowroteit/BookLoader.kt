package org.agupereyra.example.whowroteit


import android.content.Context
import androidx.loader.content.AsyncTaskLoader

class BookLoader(context: Context, private val mQueryString: String) : AsyncTaskLoader<String>(context) {

    override fun onStartLoading() {
        forceLoad()
    }
    override fun loadInBackground(): String? {
        return NetworkUtils.getBookInfo(mQueryString)
    }
}