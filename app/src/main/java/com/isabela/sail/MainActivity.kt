package com.isabela.sail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.EditText
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var webView : WebView
    private lateinit var urlEditText: EditText
    private lateinit var goIcon : ImageView
    private lateinit var homeIcon : ImageView
    private lateinit var clearIcon : ImageView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // finding by id
        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://www.google.com")

        urlEditText = findViewById(R.id.urlEditText)
        urlEditText.setText(R.string.home_url)

        goIcon = findViewById(R.id.go_icon)
        goIcon.setOnClickListener {
            Log.i("test", "${urlEditText.text}")
            webView.loadUrl("${urlEditText.text}")
        }

        homeIcon = findViewById(R.id.home_icon)
        homeIcon.setOnClickListener {
            webView.loadUrl("https://www.google.com")
            urlEditText.setText(R.string.home_url)
        }

        clearIcon = findViewById(R.id.clear_icon)
        clearIcon.setOnClickListener {
            urlEditText.setText("")
        }
    }
}