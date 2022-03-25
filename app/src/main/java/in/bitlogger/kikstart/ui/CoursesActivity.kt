package `in`.bitlogger.kikstart.ui

import `in`.bitlogger.kikstart.adapters.CourseListAdapter
import `in`.bitlogger.kikstart.databinding.ActivityCoursesBinding
import `in`.bitlogger.kikstart.db.model.CoursesModel
import `in`.bitlogger.kikstart.interfaces.CoroutineDataPassCallbacks
import `in`.bitlogger.kikstart.ui.vm.CoursesVM
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.cursoradapter.widget.CursorAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CoursesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoursesBinding
    private val coursesVM: CoursesVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCourseList()
    }

    private fun setupCourseList() {
        val callbacks = object : CoroutineDataPassCallbacks {
            override fun isDataLoading(dataLoading: Boolean) {
                Toast.makeText(this@CoursesActivity,"A $dataLoading", Toast.LENGTH_SHORT).show()
            }

            override fun <T> onLoadComplete(data: T) {
                val adapters = CourseListAdapter(data as Array<CoursesModel>)
                binding.courseListRv.apply {
                    adapter = adapters
                    layoutManager = LinearLayoutManager(this@CoursesActivity)
                }
            }

            override fun onLoadFailed(errorCode: String, errorMessage: String) {
                Toast.makeText(this@CoursesActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
        coursesVM.getAllCourses(callbacks)
    }
}