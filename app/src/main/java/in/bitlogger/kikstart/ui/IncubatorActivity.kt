package `in`.bitlogger.kikstart.ui

import `in`.bitlogger.kikstart.adapters.IncubatorListAdapter
import `in`.bitlogger.kikstart.databinding.ActivityIncubatorBinding
import `in`.bitlogger.kikstart.databinding.ActivityLoginBinding
import `in`.bitlogger.kikstart.db.model.IncubatorModel
import `in`.bitlogger.kikstart.interfaces.CoroutineDataPassCallbacks
import `in`.bitlogger.kikstart.ui.vm.IncubatorVM
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IncubatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIncubatorBinding
    private val incubatorVM: IncubatorVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncubatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val callback =  object : CoroutineDataPassCallbacks {
            override fun isDataLoading(dataLoading: Boolean) {
                Toast.makeText(this@IncubatorActivity, "Inc: $dataLoading", Toast.LENGTH_SHORT).show()
            }

            override fun <T> onLoadComplete(data: T) {
                val d = data as List<IncubatorModel>
                val adapters = IncubatorListAdapter(d)
                binding.incubatorRv.apply {
                    adapter = adapters
                    layoutManager = LinearLayoutManager(this@IncubatorActivity)
                }
            }

            override fun onLoadFailed(errorCode: String, errorMessage: String) {
                Toast.makeText(this@IncubatorActivity, "Inc: $errorMessage", Toast.LENGTH_SHORT).show()
            }
        }
        incubatorVM.getAllIncubators(callback)
    }
}