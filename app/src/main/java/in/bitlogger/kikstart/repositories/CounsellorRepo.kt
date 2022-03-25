package `in`.bitlogger.kikstart.repositories

import `in`.bitlogger.kikstart.db.model.CounsellingModel
import `in`.bitlogger.kikstart.network.apiInterface.CounsellingApiInterface
import retrofit2.Response
import javax.inject.Inject

class CounsellorRepo @Inject constructor(
    var counsellingApiInterface: CounsellingApiInterface
) {

    suspend fun getAllCounsellors(): Response<CounsellingModel> = counsellingApiInterface.fetchAllCounsellors("a")

    suspend fun getNumberedCounsellors(num: Int): Response<CounsellingModel> = counsellingApiInterface.fetchNumberedCounsellors(num)

    suspend fun getCounsellor(id: String): Response<CounsellingModel> = counsellingApiInterface.fetchCounsellor(id)
}