package `in`.bitlogger.kikstart.adapters

import `in`.bitlogger.kikstart.R
import `in`.bitlogger.kikstart.db.model.IncubatorModel
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class IncubatorListAdapter(val data: List<IncubatorModel>):
    RecyclerView.Adapter<IncubatorListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_incubator, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<ImageView>(R.id.incubator_logo).apply {
            Glide.with(holder.itemView).load(data[position].logo.toUri()).into(this)
        }
        holder.itemView.findViewById<TextView>(R.id.incubator_name).text = data[position].name
        holder.itemView.findViewById<TextView>(R.id.incubator_desc).text = data[position].content
        holder.itemView.findViewById<Button>(R.id.incubator_view_btn).setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(data[position].link)
            holder.itemView.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int = data.size
}