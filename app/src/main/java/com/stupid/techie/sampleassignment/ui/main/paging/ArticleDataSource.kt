package com.stupid.techie.sampleassignment.ui.main.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.stupid.techie.sampleassignment.model.Article
import com.stupid.techie.sampleassignment.network.ArticleService
import com.stupid.techie.sampleassignment.network.RemoteAPIClient
import com.stupid.techie.sampleassignment.ui.main.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleDataSource(articleService: ArticleService) : PageKeyedDataSource<Int, Article>() {

    var state: MutableLiveData<State> = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Article>
    ) {
        updateState(State.LOADING)
        getInitlaData(1, params.requestedLoadSize, callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        updateState(State.LOADING)
        getData(params.key, params.requestedLoadSize, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {

    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    private fun getInitlaData(page: Int, limit: Int, callback: LoadInitialCallback<Int, Article>) {

        CoroutineScope(Dispatchers.IO).launch {
            val call = RemoteAPIClient.getArticleService().getArticles(page, limit)
            call.enqueue(object : Callback<List<Article>> {

                override fun onResponse(
                    call: Call<List<Article>>,
                    response: Response<List<Article>>
                ) {
                    Log.i(ArticleDataSource::class.java.simpleName, response.body().toString())
                    updateState(State.DONE)
                    val articleList = response.body()
                    articleList?.let { callback.onResult(it, null, 2) }
                }

                override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                    Log.e(ArticleDataSource::class.java.simpleName, t.message)
                    updateState(State.ERROR)
                }
            })

        }

    }

    private fun getData(page: Int, limit: Int, callback: LoadCallback<Int, Article>) {

        CoroutineScope(Dispatchers.IO).launch {
            val call = RemoteAPIClient.getArticleService().getArticles(page, limit)
            call.enqueue(object : Callback<List<Article>> {

                override fun onResponse(
                    call: Call<List<Article>>,
                    response: Response<List<Article>>
                ) {
                    Log.i(ArticleDataSource::class.java.simpleName, response.body().toString())
                    updateState(State.DONE)
                    val articleList = response.body()
                    articleList?.let { callback.onResult(it, page + 1) }
                }

                override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                    Log.e(ArticleDataSource::class.java.simpleName, t.message)
                    updateState(State.ERROR)
                }
            })

        }

    }
}