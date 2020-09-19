package com.isabela.sail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.isabela.sail.adapter.FavoriteAdapter
import com.isabela.sail.viewmodel.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var toolbar : Toolbar
    private lateinit var viewModel : FavoriteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        // toolbar
        toolbar = findViewById(R.id.favorite_toolbar)
        setSupportActionBar(toolbar)

        initRecyclerView()
        initViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.delete_all_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.deleteAll -> {
                viewModel.deleteAll()
                true
            }

            else -> true
        }
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.favoriteRecycler)
        adapter = FavoriteAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initViewModel() {
        // view model
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(FavoriteViewModel::class.java)

        viewModel.allFavorites.observe(this, Observer { favorite ->
            favorite.let { adapter.setFavorite(it) }
        })
    }

    companion object {
        fun start(context : Context) {
            val intent = Intent(context, FavoriteActivity::class.java)
            context.startActivity(intent)
        }
    }
}