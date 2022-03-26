package `in`.bitlogger.kikstart.network.apiInterface

import `in`.bitlogger.kikstart.db.model.IncubatorModel
import retrofit2.Response
import retrofit2.http.GET

interface IncubationApiInterface {

    @GET("/api/getAll")
    suspend fun getAllIncubationCenters(): Response<List<IncubatorModel>>
}