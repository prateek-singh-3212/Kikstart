package `in`.bitlogger.kikstart.ui

import `in`.bitlogger.kikstart.adapters.SchemesListAdapter
import `in`.bitlogger.kikstart.databinding.ActivitySchemesBinding
import `in`.bitlogger.kikstart.db.model.SchemesModel
import `in`.bitlogger.kikstart.interfaces.CoroutineDataPassCallbacks
import `in`.bitlogger.kikstart.ui.vm.SchemesVM
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchemesActivity : AppCompatActivity() {
    private lateinit var schemesBinding: ActivitySchemesBinding
    private val schemesVM: SchemesVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        schemesBinding = ActivitySchemesBinding.inflate(layoutInflater)
        setContentView(schemesBinding.root)

        val callback = object : CoroutineDataPassCallbacks {
            override fun isDataLoading(dataLoading: Boolean) {
                Toast.makeText(this@SchemesActivity,"Schemes: $dataLoading", Toast.LENGTH_SHORT).show()

            }

            override fun <T> onLoadComplete(data: T) {
                val adapters = SchemesListAdapter(data as List<SchemesModel>)
                schemesBinding.schemesListRv.apply {
                    adapter = adapters
                    layoutManager = LinearLayoutManager(this@SchemesActivity)
                }
            }

            override fun onLoadFailed(errorCode: String, errorMessage: String) {
                Toast.makeText(this@SchemesActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
        schemesVM.getAllCourses(callback)
    }
}