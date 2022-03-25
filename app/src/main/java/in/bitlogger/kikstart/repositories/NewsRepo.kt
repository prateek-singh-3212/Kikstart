package `in`.bitlogger.kikstart.repositories

import `in`.bitlogger.kikstart.db.model.NewsModel
import `in`.bitlogger.kikstart.network.apiInterface.NewsApiInterface
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class NewsRepo @Inject constructor(
    private val newClient: NewsApiInterface
) {

    suspend fun getAllNews(): Response<NewsModel> = newClient.fetchAllNews()

    suspend fun getSelectedNews(number: Int): Response<NewsModel> = newClient.fetchNumberNews(number)
}