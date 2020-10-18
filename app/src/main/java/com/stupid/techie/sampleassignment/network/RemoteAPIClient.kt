package com.stupid.techie.sampleassignment.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteAPIClient {

    companion object {
        const val CONNECTION_TIMEOUT: Long = 30
        const val READ_WRITE_TIMEOUT: Long = 60

        val baseURL = "https://5e99a9b1bc561b0016af3540.mockapi.io/jet2/api/v1/"

        private fun getOkHttpClient(): OkHttpClient {

            var loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .readTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()
        }

        fun getRetroFitClient(): Retrofit {

            val gson = GsonBuilder()
                .setLenient()
                .create()


            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getOkHttpClient())
                .build()
        }

        fun getArticleService() = getRetroFitClient().create(ArticleService::class.java)

    }


}