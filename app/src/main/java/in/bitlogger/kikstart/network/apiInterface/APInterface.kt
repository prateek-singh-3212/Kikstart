package `in`.bitlogger.kikstart.network.apiInterface

import `in`.bitlogger.kikstart.db.model.LoginResponseModel
import `in`.bitlogger.kikstart.db.model.SigninModel
import `in`.bitlogger.kikstart.db.model.SignupModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APInterface {

    @POST("/api/login")
    suspend fun login(@Body signinModel: SigninModel): Response<LoginResponseModel>

    @POST("/api/register")
    suspend fun register(@Body signupModel: SignupModel): Response<LoginResponseModel>
}