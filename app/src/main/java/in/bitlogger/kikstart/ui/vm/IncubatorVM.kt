package `in`.bitlogger.kikstart.ui.vm

import `in`.bitlogger.kikstart.interfaces.CoroutineDataPassCallbacks
import `in`.bitlogger.kikstart.repositories.IncubatorRepo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncubatorVM @Inject constructor(
    var incubatorRepo: IncubatorRepo
): ViewModel() {

    fun getAllIncubators(callbacks: CoroutineDataPassCallbacks) {
        callbacks.isDataLoading(true)
        viewModelScope.launch {
            val response = incubatorRepo.getAllIncubators()
            if (response.isSuccessful) {
                callbacks.onLoadComplete(response.body())
                callbacks.isDataLoading(false)
            }else {
                callbacks.onLoadFailed(response.code().toString(), response.message())
                callbacks.isDataLoading(false)
            }
        }
    }
}