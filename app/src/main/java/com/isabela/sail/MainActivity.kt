package com.isabela.sail

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.isabela.sail.client.MainWebViewClient

/**
 * class MainActivity
 * This is the main activity for the Sail Browser.
 * Sail is a basic browser created to learn the usage of android's web view
 */
class MainActivity : AppCompatActivity() {

    // Declares the components
    private lateinit var webView : WebView
    private lateinit var urlEditText: EditText
    private lateinit var toolbar : Toolbar

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // edit text
        urlEditText = findViewById(R.id.urlEditText)
        urlEditText.setText(R.string.home_url)

        // web view
        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = MainWebViewClient(urlEditText)
        webView.loadUrl("https://www.google.com")
    }

    /**
     * This method inflates the menu
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    /**
     * This method sets the menu events
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.go_icon -> {
                var uri = "${urlEditText.text}"

                // url regex
                val regex = Regex("http(s)?://(\\w+\\.)*(\\w+)(:\\d+)?")

                // match
                val match = regex.find(uri, 0)

                if (match != null) {
                    // checks if the url contains http or https
                    if (!uri.contains("http://") && !uri.contains("https://"))
                        uri = "http://$uri"
                } else {
                    val uriAux = uri.replace(" ", "%20")
                    uri = "https://google.com/search?q=$uriAux"

                }

                webView.loadUrl(uri)
                true
            }

            R.id.clear_icon -> {
                // clears the text
                urlEditText.setText("")
                true
            }

            R.id.home_icon -> {
                // redirects to google as home page
                webView.loadUrl("https://www.google.com")
                urlEditText.setText(R.string.home_url)
                true
            }

            R.id.share_icon -> {
                if (urlEditText.text.isNotEmpty()) {
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, urlEditText.text)
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
                }
                true
            }

            else -> true
        }
    }

    /**
     * Go back in the history when the back key is pressed
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
        }

        return true
    }
}