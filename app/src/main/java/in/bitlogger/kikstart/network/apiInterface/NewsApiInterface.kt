package `in`.bitlogger.kikstart.network.apiInterface

import `in`.bitlogger.kikstart.db.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiInterface {

    @GET("/send")
    suspend fun fetchAllNews() :Response<NewsModel>

    @GET("/send")
    suspend fun fetchNumberNews(@Query("n") n: Int): Response<NewsModel>
}