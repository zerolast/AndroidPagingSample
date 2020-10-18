package com.stupid.techie.sampleassignment.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface ArticleService {

    @GET("/blogs?page=1&limit=10")
    fun getArticles(): Call<ResponseBody>

}