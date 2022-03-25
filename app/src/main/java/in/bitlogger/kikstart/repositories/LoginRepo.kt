package `in`.bitlogger.kikstart.repositories

import `in`.bitlogger.kikstart.db.model.LoginResponseModel
import `in`.bitlogger.kikstart.db.model.SigninModel
import `in`.bitlogger.kikstart.db.model.SignupModel
import `in`.bitlogger.kikstart.network.apiInterface.APInterface
import `in`.bitlogger.studentsolutions.utils.PreferenceManager
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class LoginRepo @Inject constructor(
    var preferenceManager: PreferenceManager,
    private var apiClient: APInterface
) {

    suspend fun createUser(signUp: SignupModel): Response<LoginResponseModel> {
       return apiClient.register(signUp)
    }

    suspend fun logInUser(signIn: SigninModel): Response<LoginResponseModel> {
        return apiClient.login(signIn)
    }

    private fun addDataInPreference() {
    }
}