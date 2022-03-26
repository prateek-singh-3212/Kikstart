package `in`.bitlogger.kikstart.di

import `in`.bitlogger.kikstart.network.apiInterface.*
import `in`.bitlogger.studentsolutions.utils.PreferenceManager
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePreferenceManager(
        @ApplicationContext context: Context
    ) = PreferenceManager(context)

    @Provides
    @Singleton
    fun apiClient(): APInterface {
        val auth = Retrofit.Builder()
            .baseUrl("https://smartindiahackathon2022.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(interceptor())
            .build()
        return auth.create(APInterface::class.java)
    }

    @Provides
    @Singleton
    fun newsApiInterface(): NewsApiInterface {
        val news = Retrofit.Builder()
            .baseUrl("https://news-sih.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(interceptor())
            .build()
        return news.create(NewsApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun coursesApiInterface(): CoursesApiInterface {
        val course = Retrofit.Builder()
            .baseUrl("https://smartindiahackathon2.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(interceptor())
            .build()

        return course.create(CoursesApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun schemesApiInterface(): SchemesApiInterface {
        val schemes = Retrofit.Builder()
            .baseUrl("https://schemes-sih.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(interceptor())
            .build()

        return schemes.create(SchemesApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun counsellingApiInterface(): CounsellingApiInterface {
        val counsellors = Retrofit.Builder()
            .baseUrl("https://counselling-sih.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(interceptor())
            .build()

        return counsellors.create(CounsellingApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun incubatorApiInterface(): IncubationApiInterface {
        val incubator = Retrofit.Builder()
            .baseUrl("https://incubators-sih.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(interceptor())
            .build()

        return incubator.create(IncubationApiInterface::class.java)
    }

    private fun interceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}