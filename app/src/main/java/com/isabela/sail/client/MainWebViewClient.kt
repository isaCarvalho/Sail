package com.isabela.sail.client

import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText

class MainWebViewClient(private val editText: EditText) : WebViewClient() {

    override fun onPageCommitVisible(view: WebView?, url: String?) {
        if (view != null) {
            editText.setText(view.url.toString())
        }

        super.onPageCommitVisible(view, url)
    }
}