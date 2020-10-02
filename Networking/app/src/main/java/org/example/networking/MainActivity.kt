package org.example.networking

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.main_activity.*
import java.io.*
import java.lang.StringBuilder
import java.net.MalformedURLException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity(), ChangeImageCallbackInterface {

    private lateinit var doggoFragment: DoggoFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        get_doggo_btn.setOnClickListener {
            getDoggo()
        }

        doggoFragment = DoggoFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.dog_img_framelayout, doggoFragment)
        fragmentTransaction.commit()
    }

    private fun getDoggo() : Unit {
        if (isNetworkConnected()) {
            // Get random URL of dog image
            DownloadDoggoImageUrlTask(this).execute()
        } else {
            // Inform there is no Internet connection
            showLaTostada(getString(R.string.no_internet_connection))
        }
    }

    private fun showLaTostada(msg: String) : Unit {
        val laTostada = Toast.makeText(baseContext, msg, Toast.LENGTH_LONG)
        laTostada.setGravity(Gravity.CENTER ,0,0)
        laTostada.show()
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

    override fun changeFragmentImage(url: String) {
        doggoFragment.changeImage(url)
    }

    override fun showErrorMessage(error: String) {
        showLaTostada(error)
    }

    // AsyncTask
    // Returns JSON with URL for doggo image
    private class DownloadDoggoImageUrlTask(val callback: ChangeImageCallbackInterface) : AsyncTask<Unit, Unit, String>() {
        override fun doInBackground(vararg p0: Unit?): String? {
            // Get json with image url
            try {
                val url = URL(RANDOM_DOGGO_URL)
                val connection = url.openConnection() as HttpsURLConnection
                connection.requestMethod = "GET"
                val inputStream = BufferedInputStream(connection.inputStream)
                // Get input string and return
                return convertStreamToString(inputStream)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
                return null
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (result != null) {
                val gson = Gson()
                val randomDogDTO = gson.fromJson<RandomDogDTO>(result, RandomDogDTO::class.java)
                callback.changeFragmentImage(randomDogDTO.message)
            } else {
                callback.showErrorMessage("No se pudo descargar la imagen :(")
            }
        }

        private fun convertStreamToString(inputStream: InputStream) : String {
            val buffReader = BufferedReader(InputStreamReader(inputStream))

            val stringBuilder = StringBuilder()
            try {
                var line : String? = buffReader.readLine()
                while(line != null) {
                    stringBuilder.append(line)
                    line = buffReader.readLine()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return stringBuilder.toString()
        }
    }

}