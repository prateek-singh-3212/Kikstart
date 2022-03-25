package `in`.bitlogger.kikstart.adapters

import `in`.bitlogger.kikstart.R
import `in`.bitlogger.kikstart.db.model.CoursesModel
import `in`.bitlogger.kikstart.ui.CourseDisplayActivity
import `in`.bitlogger.kikstart.ui.CoursePlayerActivity
import `in`.bitlogger.kikstart.utils.Constants
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip

class CourseListAdapter(val data: Array<CoursesModel>): RecyclerView.Adapter<CourseListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_courses, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<ImageView>(R.id.course_card_thumbnail).apply {
            Glide.with(holder.itemView).load(data[position].courseVids[1].thumbnail.toUri()).into(this)
        }
        holder.itemView.findViewById<TextView>(R.id.course_card_title).text = data[position].course_name
        holder.itemView.findViewById<TextView>(R.id.course_card_instructor_name).text = data[position].Instructor
        holder.itemView.findViewById<TextView>(R.id.course_card_rating).text = data[position].rating.toString()
        holder.itemView.findViewById<Chip>(R.id.course_card_source).text = data[position].source
        holder.itemView.findViewById<CardView>(R.id.card_courses).setOnClickListener {
            val intent = Intent(holder.itemView.context, CourseDisplayActivity::class.java)
            intent.putExtra(Constants.COURSE_ID, data[position]._id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = data.size
}