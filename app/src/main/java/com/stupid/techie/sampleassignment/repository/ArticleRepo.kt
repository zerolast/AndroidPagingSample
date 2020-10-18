package com.stupid.techie.sampleassignment.repository

import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.stupid.techie.sampleassignment.model.Article
import com.stupid.techie.sampleassignment.network.RemoteAPIClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleRepo {

    fun getArticles() {
        getData()
    }


    private fun getData(){

        CoroutineScope(Dispatchers.IO).launch {
            val call = RemoteAPIClient.getArticleService().getArticles()
            call.enqueue(object : Callback<ResponseBody> {

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Log.i(ArticleRepo::class.java.simpleName,response.body()?.string())
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e(ArticleRepo::class.java.simpleName,t.message)
                }
            })

        }

    }

}