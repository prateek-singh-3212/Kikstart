package `in`.bitlogger.kikstart.adapters

import `in`.bitlogger.kikstart.R
import `in`.bitlogger.kikstart.db.model.CounsellingModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CounsellorListAdapter(var data: CounsellingModel) :
    RecyclerView.Adapter<CounsellorListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_counsellor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.counsellor_name).text = data.data[position].name
        holder.itemView.findViewById<TextView>(R.id.counsellor_rating).text = data.data[position].rating.toString()
        holder.itemView.findViewById<TextView>(R.id.counsellor_qualification).text = data.data[position].qualification
        Glide.with(holder.itemView.context).load(data.data[position].img.toUri()).into(holder.itemView.findViewById(R.id.counsellor_image))
    }

    override fun getItemCount(): Int = data.data.size
}