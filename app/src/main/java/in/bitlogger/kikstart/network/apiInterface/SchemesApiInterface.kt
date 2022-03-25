package `in`.bitlogger.kikstart.network.apiInterface

import `in`.bitlogger.kikstart.db.model.SchemesModel
import retrofit2.Response
import retrofit2.http.GET

interface SchemesApiInterface {

    @GET("/api/getAll")
    suspend fun fetchAllSchemes(): Response<List<SchemesModel>>
}