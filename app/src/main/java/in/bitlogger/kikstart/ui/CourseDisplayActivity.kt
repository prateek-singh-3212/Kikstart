package `in`.bitlogger.kikstart.ui

import `in`.bitlogger.kikstart.databinding.ActivityCourseDisplayBinding
import `in`.bitlogger.kikstart.db.model.CoursesModel
import `in`.bitlogger.kikstart.interfaces.CoroutineDataPassCallbacks
import `in`.bitlogger.kikstart.ui.vm.CoursesVM
import `in`.bitlogger.kikstart.utils.Constants
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@AndroidEntryPoint
class CourseDisplayActivity : AppCompatActivity() {
    private lateinit var courseDisplayBinding: ActivityCourseDisplayBinding
    private var cid: String? = null
    private val coursesVM: CoursesVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        courseDisplayBinding = ActivityCourseDisplayBinding.inflate(layoutInflater)
        setContentView(courseDisplayBinding.root)

        cid = intent.getStringExtra(Constants.COURSE_ID) ?: "623c5f840e583bbb99570f2d"

        val callback = object :CoroutineDataPassCallbacks{
            override fun isDataLoading(dataLoading: Boolean) {
                Toast.makeText(this@CourseDisplayActivity,"A $dataLoading", Toast.LENGTH_SHORT).show()
            }

            override fun <T> onLoadComplete(data: T) {
                val data = data as CoursesModel
                Glide.with(this@CourseDisplayActivity).load(data.courseVids[0].thumbnail.toUri()).into(courseDisplayBinding.courseImage)
                courseDisplayBinding.courseName.text = data.course_name
                courseDisplayBinding.courseDescription.text = data.desc
                courseDisplayBinding.courseInstructor.text = data.Instructor
                courseDisplayBinding.courseRating.text = data.rating.toString()
                courseDisplayBinding.courseSource.text =data.source
                courseDisplayBinding.courseEnroll.setOnClickListener {
                    val intent = Intent(this@CourseDisplayActivity, CoursePlayerActivity::class.java)
                    intent.putParcelableArrayListExtra(Constants.COURSE_LIST, data.courseVids as ArrayList)
                    startActivity(intent)
                }
            }

            override fun onLoadFailed(errorCode: String, errorMessage: String) {
                Toast.makeText(this@CourseDisplayActivity,errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
        coursesVM.getCoursesFromId(cid!!, callback)
    }
}