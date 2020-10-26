package org.agupereyra.example.whowroteit

import android.net.Uri
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL

// Base URL for Books API.
private const val BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?"

// Parameter for the search string.
private const val QUERY_PARAM = "q"

// Parameter that limits search results.
private const val MAX_RESULTS = "maxResults"

// Parameter to filter by print type.
private const val PRINT_TYPE = "printType"


class NetworkUtils {
    companion object {
        fun getBookInfo(queryString: String) : String {
            lateinit var bookJsonString : String
            lateinit var urlConnection : HttpURLConnection
            try {
                val builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build()
                val requestURL = URL(builtURI.toString())
                urlConnection = requestURL.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.connect()

                // Read response
                bookJsonString = convertStreamToString(urlConnection.inputStream)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                urlConnection.disconnect()
            }

            return bookJsonString
        }

        private fun convertStreamToString(inputStream: InputStream) : String {
            val buffReader = BufferedReader(InputStreamReader(inputStream))

            val stringBuilder = StringBuilder()
            try {
                var line: String? = buffReader.readLine()
                while (line != null) {
                    stringBuilder.append(line)
                    line = buffReader.readLine()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                if (buffReader != null) {
                    try {
                        buffReader.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            return stringBuilder.toString()
        }
    }

}