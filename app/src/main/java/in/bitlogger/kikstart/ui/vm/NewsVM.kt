package `in`.bitlogger.kikstart.ui.vm

import `in`.bitlogger.kikstart.interfaces.CoroutineDataPassCallbacks
import `in`.bitlogger.kikstart.repositories.NewsRepo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsVM @Inject constructor(
    var newsRepo: NewsRepo
): ViewModel()  {

    fun fetchAllNews(callbacks: CoroutineDataPassCallbacks) {
        callbacks.isDataLoading(true)
        viewModelScope.launch {
            val response = newsRepo.getSelectedNews(6)
            if (response.isSuccessful) {
                val data = response.body()
                callbacks.onLoadComplete(data?.news ?: arrayOf())
                callbacks.isDataLoading(false)
            }else {
                callbacks.onLoadFailed(
                    response.code() as String,
                    response.message()
                )
                callbacks.isDataLoading(false)
            }
        }
    }
}