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
import com.isabela.sail.adapter.HistoryAdapter
import com.isabela.sail.viewmodel.HistoryViewModel

class HistoryActivity : AppCompatActivity() {

    private lateinit var toolbar : Toolbar
    private lateinit var viewModel : HistoryViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // toolbar
        toolbar = findViewById(R.id.history_toolbar)
        setSupportActionBar(toolbar)

        initRecyclerView()
        initViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.history_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.deleteHistory -> {
                viewModel.deleteAll()
                true
            }

            else -> true
        }
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.historyRecycler)
        adapter = HistoryAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initViewModel() {
        // view model
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(HistoryViewModel::class.java)

        viewModel.allItems.observe(this, Observer { historyItem ->
            historyItem.let { adapter.setHistory(it) }
        })
    }

    companion object {
        fun start(context : Context) {
            val intent = Intent(context, HistoryActivity::class.java)
            context.startActivity(intent)
        }
    }
}