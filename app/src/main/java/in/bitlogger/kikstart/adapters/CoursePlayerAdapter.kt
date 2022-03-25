package `in`.bitlogger.kikstart.adapters

import `in`.bitlogger.kikstart.R
import `in`.bitlogger.kikstart.db.model.CoursesVideos
import `in`.bitlogger.kikstart.interfaces.DataCallback
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
import com.google.android.youtube.player.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class CoursePlayerAdapter(val data: List<CoursesVideos>, val dataCallback: DataCallback): RecyclerView.Adapter<CoursePlayerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_courses, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoursePlayerAdapter.ViewHolder, position: Int) {
        holder.itemView.findViewById<ImageView>(R.id.course_card_thumbnail).apply {
            Glide.with(holder.itemView).load(data[position].thumbnail.toUri()).into(this)
        }
        holder.itemView.findViewById<TextView>(R.id.course_card_title).text = data[position].title
        holder.itemView.findViewById<TextView>(R.id.course_card_instructor_name).text = ""
        holder.itemView.findViewById<TextView>(R.id.course_card_rating).text = ""
        holder.itemView.findViewById<Chip>(R.id.course_card_source).text = data[position].duration
        holder.itemView.findViewById<CardView>(R.id.card_courses).setOnClickListener {
            dataCallback.data(data[position].url)
        }
    }

    override fun getItemCount(): Int = data.size
}