package `in`.bitlogger.kikstart.repositories

import `in`.bitlogger.kikstart.db.model.IncubatorModel
import `in`.bitlogger.kikstart.network.apiInterface.IncubationApiInterface
import retrofit2.Response
import javax.inject.Inject

class IncubatorRepo @Inject constructor(
    var incubationApiInterface: IncubationApiInterface
) {

    suspend fun getAllIncubators(): Response<List<IncubatorModel>> = incubationApiInterface.getAllIncubationCenters()
}