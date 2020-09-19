package com.isabela.sail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.isabela.sail.HistoryActivity
import com.isabela.sail.R
import com.isabela.sail.model.HistoryItem
import com.isabela.sail.viewmodel.HistoryViewModel

class HistoryAdapter internal constructor(private val context: Context)
    : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>()
{
    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private var history = emptyList<HistoryItem>()

    inner class HistoryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val urlHistoryItemTxt = itemView.findViewById<TextView>(R.id.urlHistoryItemTxt)
        val dateHistoryTxt = itemView.findViewById<TextView>(R.id.dateHistoryTxt)
        val imgDeleteItem = itemView.findViewById<ImageView>(R.id.imgDeleteItem)
    }

    override fun getItemCount(): Int = history.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val current = history[position]
        holder.urlHistoryItemTxt.text = current.url
        holder.dateHistoryTxt.text = current.date

        holder.imgDeleteItem.setOnClickListener {
            // view model
            val historyViewModel = ViewModelProviders.of(context as HistoryActivity).get(HistoryViewModel::class.java)
            historyViewModel.delete(current)

            Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemView = inflater.inflate(R.layout.history_item_layout, parent, false)

        return HistoryViewHolder(itemView)
    }

    internal fun setHistory(history : List<HistoryItem>) {
        this.history = history
        notifyDataSetChanged()
    }
}