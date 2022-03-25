package `in`.bitlogger.kikstart.network.apiInterface

import `in`.bitlogger.kikstart.db.model.CoursesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoursesApiInterface {

    @GET("/api/courses")
    suspend fun fetchAllCourses() : Response<Array<CoursesModel>>

    @GET("/api/courses/{id}")
    suspend fun fetchCourseFromId(@Path("id") id: String): Response<CoursesModel>
}