package `in`.bitlogger.kikstart.adapters

import `in`.bitlogger.kikstart.R
import `in`.bitlogger.kikstart.db.model.HomeIcon
import `in`.bitlogger.kikstart.ui.CommingSoon
import `in`.bitlogger.kikstart.ui.CounsellorsListActivity
import `in`.bitlogger.kikstart.ui.CoursesActivity
import `in`.bitlogger.kikstart.ui.SchemesActivity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HomeIconAdapter(private val data: Array<HomeIcon>) :
    RecyclerView.Adapter<HomeIconAdapter.Viewholder>() {

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_icon, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.itemView.apply {
            findViewById<ImageView>(R.id.icon_image).setOnClickListener {
                Toast.makeText(holder.itemView.context, "Coming Soon", Toast.LENGTH_SHORT).show()
                when (position) {
                    0 -> {
                        val intent = Intent(holder.itemView.context, CounsellorsListActivity::class.java)
                        holder.itemView.context.startActivity(intent)
                    }
                    1 -> {
                        val intent = Intent(holder.itemView.context, CommingSoon::class.java)
                        holder.itemView.context.startActivity(intent)
                    }
                    2 -> {
                        val intent = Intent(holder.itemView.context, CoursesActivity::class.java)
                        holder.itemView.context.startActivity(intent)
                    }
                    3 -> {
                        val intent = Intent(holder.itemView.context, SchemesActivity::class.java)
                        holder.itemView.context.startActivity(intent)
                    }
                    4 -> {
                        val intent = Intent(holder.itemView.context, CommingSoon::class.java)
                        holder.itemView.context.startActivity(intent)
                    }
                    5 -> {
                        val intent = Intent(holder.itemView.context, CommingSoon::class.java)
                        holder.itemView.context.startActivity(intent)
                    }
                    else -> {
                        Toast.makeText(
                            holder.itemView.context,
                            "Unable to click!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            Glide.with(this).load(data[position].resId)
                .into(findViewById<ImageView>(R.id.icon_image))
            findViewById<TextView>(R.id.icon_text).text = data[position].name
        }
    }

    override fun getItemCount(): Int = data.size
}