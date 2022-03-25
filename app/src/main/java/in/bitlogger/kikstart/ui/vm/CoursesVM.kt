package `in`.bitlogger.kikstart.ui.vm

import `in`.bitlogger.kikstart.interfaces.CoroutineDataPassCallbacks
import `in`.bitlogger.kikstart.repositories.CoursesRepo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesVM @Inject constructor(
    var coursesRepo: CoursesRepo
): ViewModel() {

    fun getAllCourses(callbacks: CoroutineDataPassCallbacks) {
        callbacks.isDataLoading(true)
        viewModelScope.launch {
            val response = coursesRepo.getAllCourses()
            if (response.isSuccessful) {
                callbacks.onLoadComplete(response.body())
                callbacks.isDataLoading(false)
            }else {
                callbacks.onLoadFailed(response.code().toString(), response.message())
                callbacks.isDataLoading(false)
            }
        }
    }

    fun getCoursesFromId(id:String, callbacks: CoroutineDataPassCallbacks) {
        callbacks.isDataLoading(true)
        viewModelScope.launch {
            val response = coursesRepo.getAllCourseFromId(id)
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