package `in`.bitlogger.kikstart.ui

import `in`.bitlogger.kikstart.adapters.CoursePlayerAdapter
import `in`.bitlogger.kikstart.databinding.ActivityCoursePlayerBinding
import `in`.bitlogger.kikstart.db.model.CoursesVideos
import `in`.bitlogger.kikstart.interfaces.DataCallback
import `in`.bitlogger.kikstart.utils.Constants
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class CoursePlayerActivity : AppCompatActivity() {
    private lateinit var coursePlayerBinding: ActivityCoursePlayerBinding
    private var cid: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coursePlayerBinding = ActivityCoursePlayerBinding.inflate(layoutInflater)
        setContentView(coursePlayerBinding.root)

        val videosList = intent.getSerializableExtra(Constants.COURSE_LIST) as List<CoursesVideos>
        Toast.makeText(this, videosList[0].thumbnail, Toast.LENGTH_SHORT).show()

        lifecycle.addObserver(coursePlayerBinding.youtubePlayerView)

        val callback: DataCallback = object :DataCallback {
            override fun <T> data(data: T) {
                val id = getId(data as String)
                Toast.makeText(this@CoursePlayerActivity, id, Toast.LENGTH_SHORT).show()
                coursePlayerBinding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(id, 0f)
                    }
                })
            }

            private fun getId(s: String): String = s.replace("https://www.youtube.com/watch?v=", "")
        }

        val adapters = CoursePlayerAdapter(videosList, callback)
        coursePlayerBinding.coursePlayer.apply {
            adapter = adapters
            layoutManager = LinearLayoutManager(this@CoursePlayerActivity)
        }
    }
}