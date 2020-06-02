package com.example.newsapplication.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.R
import com.example.newsapplication.model.Article
import kotlinx.android.synthetic.main.news_list_row.view.*
import org.kodein.di.android.x.AndroidLifecycleScope


class NewsListAdapter(
    private val newsList: List<Article>,
    val context: Context,
    val adapterOnClick: (Any?) -> Unit

) :
    RecyclerView.Adapter<NewsListAdapter.SwapHistoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwapHistoryItemViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.news_list_row, parent, false)
        return SwapHistoryItemViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: SwapHistoryItemViewHolder, position: Int) {

        holder.userName.text=newsList[position].author
        holder.auname.text=newsList[position].source?.name
        holder.publishDate.text=newsList[position].publishedAt
        Glide.with(context)  //2
            .load(newsList[position].urlToImage) //3
            .centerCrop() //4
            .placeholder(R.drawable.ic_launcher_background) //5
            .into(holder.imagview)

      holder.imagview.setOnClickListener{
          adapterOnClick(newsList[position].url)
      }
    }

    class SwapHistoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName = itemView.author
        val publishDate = itemView.publish
        var auname = itemView.name
        var imagview=itemView.imageView
    }
}