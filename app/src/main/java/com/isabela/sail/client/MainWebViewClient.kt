package com.isabela.sail.client

import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText

class MainWebViewClient(private val editText: EditText)
    : WebViewClient() {

    /**
     * Changes the url when the pages changes
     */
    override fun onPageCommitVisible(view: WebView?, url: String?) {
        if (view != null && url != null) {
            editText.setText(view.url.toString())
        }
        super.onPageCommitVisible(view, url)
    }
}