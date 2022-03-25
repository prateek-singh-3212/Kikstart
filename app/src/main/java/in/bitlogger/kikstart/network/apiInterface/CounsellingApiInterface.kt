package `in`.bitlogger.kikstart.network.apiInterface

import `in`.bitlogger.kikstart.db.model.CounsellingModel
import `in`.bitlogger.kikstart.db.model.CoursesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CounsellingApiInterface {

    @GET("/list")
    suspend fun fetchAllCounsellors(@Query("n") n: String): Response<CounsellingModel>

    @GET("/list")
    suspend fun fetchNumberedCounsellors(@Query("n") n: Int): Response<CounsellingModel>

    @GET()
    suspend fun fetchCounsellor(id: String): Response<CounsellingModel>
}