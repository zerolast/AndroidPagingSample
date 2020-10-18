package com.stupid.techie.sampleassignment.network

import com.stupid.techie.sampleassignment.model.Article
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {

    @GET("/blogs")
    fun getArticles(@Query("page") page: Int, @Query("limit") limit: Int): Call<List<Article>>

}