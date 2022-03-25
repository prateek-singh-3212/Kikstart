package `in`.bitlogger.kikstart.ui

import `in`.bitlogger.kikstart.adapters.CounsellorListAdapter
import `in`.bitlogger.kikstart.adapters.CourseListAdapter
import `in`.bitlogger.kikstart.databinding.ActivityCounsellorsListBinding
import `in`.bitlogger.kikstart.db.model.CounsellingModel
import `in`.bitlogger.kikstart.db.model.CoursesModel
import `in`.bitlogger.kikstart.interfaces.CoroutineDataPassCallbacks
import `in`.bitlogger.kikstart.ui.vm.CounsellorVM
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CounsellorsListActivity : AppCompatActivity() {
    private lateinit var counsellorsListBinding: ActivityCounsellorsListBinding

    private val counsellorVM: CounsellorVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        counsellorsListBinding = ActivityCounsellorsListBinding.inflate(layoutInflater)
        setContentView(counsellorsListBinding.root)

        val callback = object :CoroutineDataPassCallbacks {
            override fun isDataLoading(dataLoading: Boolean) {
                Toast.makeText(this@CounsellorsListActivity,"A $dataLoading", Toast.LENGTH_SHORT).show()
            }

            override fun <T> onLoadComplete(data: T) {
                val adapters = CounsellorListAdapter(data as CounsellingModel)
                counsellorsListBinding.counsellingListRv.apply {
                    adapter = adapters
                    layoutManager = LinearLayoutManager(this@CounsellorsListActivity)
                }
            }

            override fun onLoadFailed(errorCode: String, errorMessage: String) {
                Toast.makeText(this@CounsellorsListActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        counsellorVM.getAllCounsellors(callback)
    }
}