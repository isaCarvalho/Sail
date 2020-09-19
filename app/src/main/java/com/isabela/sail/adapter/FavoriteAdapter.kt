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
import com.isabela.sail.FavoriteActivity
import com.isabela.sail.R
import com.isabela.sail.model.Favorite
import com.isabela.sail.viewmodel.FavoriteViewModel

class FavoriteAdapter internal constructor(private val context: Context)
    : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>()
{
    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private var favorite = emptyList<Favorite>()

    inner class FavoriteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val urlFavoriteTxt = itemView.findViewById<TextView>(R.id.urlFavoriteTxt)
        val imgDeleteItem = itemView.findViewById<ImageView>(R.id.imgDeleteFavorite)
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val current = favorite[position]
        holder.urlFavoriteTxt.text = current.url

        holder.imgDeleteItem.setOnClickListener {
            // view model
            val favoriteViewModel = ViewModelProviders.of(context as FavoriteActivity).get(
                FavoriteViewModel::class.java)
            favoriteViewModel.delete(current)

            Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemView = inflater.inflate(R.layout.favorite_item_layout, parent, false)

        return FavoriteViewHolder(itemView)
    }

    internal fun setFavorite(favorite : List<Favorite>) {
        this.favorite = favorite
        notifyDataSetChanged()
    }
}