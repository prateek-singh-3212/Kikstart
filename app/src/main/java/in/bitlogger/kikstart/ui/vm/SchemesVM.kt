package `in`.bitlogger.kikstart.ui.vm

import `in`.bitlogger.kikstart.interfaces.CoroutineDataPassCallbacks
import `in`.bitlogger.kikstart.repositories.SchemesRepo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchemesVM @Inject constructor(
    var schemesRepo: SchemesRepo
): ViewModel() {

    fun getAllCourses(callbacks: CoroutineDataPassCallbacks) {
        callbacks.isDataLoading(true)
        viewModelScope.launch {
            val response = schemesRepo.getAllSchemes()
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