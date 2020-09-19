package com.isabela.sail.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.isabela.sail.util.URI_TAG

class MainViewModel : ViewModel()
{
    fun validateURL(uri : String) : String {
        var newUri = uri

        // url regex
        val regex = Regex("http(s)?://(\\w+\\.)*(\\w+)(:\\d+)?")

        // match
        val match = regex.find(uri, 0)

        if (match != null) {
            // checks if the url contains http or https
            if (!uri.contains("http://") && !uri.contains("https://"))
                newUri = "http://$uri"
        } else {
            val uriAux = uri.replace(" ", "%20")
            newUri = "https://google.com/search?q=$uriAux"

        }
        Log.i(URI_TAG, newUri)
        return newUri
    }
}