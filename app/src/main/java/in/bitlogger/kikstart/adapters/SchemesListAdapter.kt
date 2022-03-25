package `in`.bitlogger.kikstart.adapters

import `in`.bitlogger.kikstart.R
import `in`.bitlogger.kikstart.db.model.SchemesModel
import `in`.bitlogger.kikstart.ui.SchemesDetailActivity
import `in`.bitlogger.kikstart.utils.Constants
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SchemesListAdapter(val data: List<SchemesModel>): RecyclerView.Adapter<SchemesListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SchemesListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_schemes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SchemesListAdapter.ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.schemes_card_name).text = data[position].title
        holder.itemView.findViewById<TextView>(R.id.schemes_card_description).text = data[position].content
        holder.itemView.findViewById<ImageView>(R.id.schemes_card_image).apply {
            Glide.with(holder.itemView).load(data[position].image.toUri()).into(this)
        }
        holder.itemView.findViewById<CardView>(R.id.schemes_card).setOnClickListener {
            val intent = Intent(holder.itemView.context, SchemesDetailActivity::class.java)
            intent.putExtra(Constants.SCHEME_LIST, data[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = data.size
}