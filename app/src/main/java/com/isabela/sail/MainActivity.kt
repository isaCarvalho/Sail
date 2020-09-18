package com.isabela.sail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.widget.EditText
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var webView : WebView
    private lateinit var urlEditText: EditText
    private lateinit var toolbar : Toolbar

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // finding by id
        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://www.google.com")

        urlEditText = findViewById(R.id.urlEditText)
        urlEditText.setText(R.string.home_url)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.go_icon -> {
                Log.i("test", "${urlEditText.text}")
                webView.loadUrl("${urlEditText.text}")
                true
            }

            R.id.clear_icon -> {
                urlEditText.setText("")
                true
            }

            R.id.home_icon -> {
                webView.loadUrl("https://www.google.com")
                urlEditText.setText(R.string.home_url)
                true
            }

            else -> true
        }
    }
}