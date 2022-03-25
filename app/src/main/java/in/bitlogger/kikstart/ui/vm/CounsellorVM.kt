package `in`.bitlogger.kikstart.ui.vm

import `in`.bitlogger.kikstart.interfaces.CoroutineDataPassCallbacks
import `in`.bitlogger.kikstart.repositories.CounsellorRepo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounsellorVM @Inject constructor(
    private var counsellorRepo: CounsellorRepo
): ViewModel() {

    fun getAllCounsellors(callbacks: CoroutineDataPassCallbacks) {
        viewModelScope.launch {
            val response = counsellorRepo.getAllCounsellors()
            if (response.isSuccessful) {
                callbacks.onLoadComplete(response.body())
                callbacks.isDataLoading(false)
            }else {
                callbacks.onLoadFailed(response.code().toString(), response.message())
                callbacks.isDataLoading(false)
            }
        }
    }

    fun getNumberedCounsellor(num: Int, callbacks: CoroutineDataPassCallbacks) {
        viewModelScope.launch {
            val response = counsellorRepo.getNumberedCounsellors(num)
            if (response.isSuccessful) {
                callbacks.onLoadComplete(response.body())
                callbacks.isDataLoading(false)
            }else {
                callbacks.onLoadFailed(response.code().toString(), response.message())
                callbacks.isDataLoading(false)
            }
        }
    }

    fun getParticularCounsellor(id: String, callbacks: CoroutineDataPassCallbacks) {
        viewModelScope.launch {
            val response = counsellorRepo.getCounsellor(id)
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