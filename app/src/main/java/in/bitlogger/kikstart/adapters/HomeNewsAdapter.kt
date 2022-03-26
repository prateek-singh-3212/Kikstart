package `in`.bitlogger.kikstart.adapters

import `in`.bitlogger.kikstart.R
import `in`.bitlogger.kikstart.db.model.News
import `in`.bitlogger.kikstart.ui.SchemesDetailActivity
import `in`.bitlogger.kikstart.utils.Constants
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HomeNewsAdapter(val data: Array<News>): RecyclerView.Adapter<HomeNewsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeNewsAdapter.ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.card_news_heading).text = data[position].title
        holder.itemView.findViewById<ImageView>(R.id.card_news_image).apply {
            Glide.with(holder.itemView).load(data[position].thumbnail.toUri()).into(this)
        }
    }

    override fun getItemCount(): Int = data.size
}