package `in`.bitlogger.kikstart.repositories

import `in`.bitlogger.kikstart.db.model.CoursesModel
import `in`.bitlogger.kikstart.network.apiInterface.CoursesApiInterface
import retrofit2.Response
import javax.inject.Inject

class CoursesRepo @Inject constructor(
    var coursesApiInterface: CoursesApiInterface
) {

    suspend fun getAllCourses(): Response<Array<CoursesModel>> = coursesApiInterface.fetchAllCourses()

    suspend fun getAllCourseFromId(id: String): Response<CoursesModel> = coursesApiInterface.fetchCourseFromId(id)
}