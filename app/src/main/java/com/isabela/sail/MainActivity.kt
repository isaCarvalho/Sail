package com.isabela.sail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.isabela.sail.client.MainWebViewClient
import com.isabela.sail.model.Favorite
import com.isabela.sail.model.HistoryItem
import com.isabela.sail.viewmodel.FavoriteViewModel
import com.isabela.sail.viewmodel.HistoryViewModel
import com.isabela.sail.viewmodel.MainViewModel
import java.time.LocalDateTime

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
    private lateinit var viewModel : MainViewModel
    private lateinit var favoriteViewModel : FavoriteViewModel
    private lateinit var historyViewModel: HistoryViewModel

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // view model
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(MainViewModel::class.java)

        // favorites view model
        favoriteViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(FavoriteViewModel::class.java)

        // history view model
        historyViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(HistoryViewModel::class.java)

        // edit text
        urlEditText = findViewById(R.id.urlEditText)

        // web view
        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
        webView.settings.allowFileAccess = true
        webView.settings.allowFileAccessFromFileURLs = true
        webView.webViewClient = MainWebViewClient(urlEditText)
        loadHome()
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
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.go_icon -> {
                goToPage()
                true
            }

            R.id.clear_icon -> {
                // clears the text
                clearText()
                true
            }

            R.id.home_icon -> {
                loadHome()
                true
            }

            R.id.share_icon -> {
                share("${urlEditText.text}")
                true
            }

            R.id.history_icon -> {
                HistoryActivity.start(this)
                true
            }

            R.id.fav_icon -> {
                FavoriteActivity.start(this)
                true
            }

            R.id.fav_add_icon -> {
                addToFavorites()
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

    private fun addToFavorites() {
        val uri = viewModel.validateURL(webView.url)
        favoriteViewModel.insert(Favorite(uri))

        Toast.makeText(this, "Added to favorites!", Toast.LENGTH_SHORT)
            .show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun goToPage() {
        val uri = viewModel.validateURL("${urlEditText.text}")
        webView.loadUrl(uri)

        Snackbar.make(webView, "Loading...", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()

        historyViewModel.insert(HistoryItem(uri, LocalDateTime.now().toString().replace("T", "\n")))
    }

    private fun share(uri : String) {
        if (uri.isNotEmpty()) {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, uri)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    private fun loadHome() {
        // redirects to google as home page
        webView.loadUrl(resources.getString(R.string.home_url))
        urlEditText.setText(resources.getString(R.string.home_url))
    }

    private fun clearText() {
        urlEditText.setText("")
    }
}