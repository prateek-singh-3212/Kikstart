package `in`.bitlogger.kikstart.repositories

import `in`.bitlogger.kikstart.db.model.SchemesModel
import `in`.bitlogger.kikstart.network.apiInterface.SchemesApiInterface
import retrofit2.Response
import javax.inject.Inject

class SchemesRepo @Inject constructor(
    var schemesApiInterface: SchemesApiInterface
) {

    suspend fun getAllSchemes(): Response<List<SchemesModel>> = schemesApiInterface.fetchAllSchemes()
}