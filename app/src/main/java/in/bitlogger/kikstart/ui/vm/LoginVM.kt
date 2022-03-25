package `in`.bitlogger.kikstart.ui.vm

import `in`.bitlogger.kikstart.db.model.SigninModel
import `in`.bitlogger.kikstart.db.model.SignupModel
import `in`.bitlogger.kikstart.interfaces.CoroutineDataPassCallbacks
import `in`.bitlogger.kikstart.repositories.LoginRepo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
    private var loginRepo: LoginRepo
) : ViewModel() {

    fun createUser(signUp: SignupModel, callbacks: CoroutineDataPassCallbacks) {
        callbacks.isDataLoading(true)
        viewModelScope.launch {
            val result = loginRepo.createUser(signUp)
            if (result.isSuccessful) {
                callbacks.onLoadComplete(result.body())
                callbacks.isDataLoading(false)
            } else {
                callbacks.onLoadFailed(
                    result.code().toString(),
                    result.body()?.message ?: "Load Failed !!"
                )
                callbacks.isDataLoading(false)
            }
        }
    }

    fun signInUser(signIn: SigninModel, callbacks: CoroutineDataPassCallbacks) {
        callbacks.isDataLoading(true)
        viewModelScope.launch {
            val result = loginRepo.logInUser(signIn)
            if (result.isSuccessful) {
                callbacks.onLoadComplete(result.body())
                callbacks.isDataLoading(false)
            } else {
                callbacks.onLoadFailed(
                    result.code().toString(),
                    result.body()?.message ?: "Load Failed !!"
                )
                callbacks.isDataLoading(false)
            }
        }
    }
}