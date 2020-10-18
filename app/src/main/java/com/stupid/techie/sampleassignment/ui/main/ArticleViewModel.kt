package com.stupid.techie.sampleassignment.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.stupid.techie.sampleassignment.model.Article
import com.stupid.techie.sampleassignment.network.RemoteAPIClient
import com.stupid.techie.sampleassignment.ui.main.paging.ArticleDataSource
import com.stupid.techie.sampleassignment.ui.main.paging.ArticleDataSourceFactory

class ArticleViewModel : ViewModel() {

    var articleList : LiveData<PagedList<Article>>
    private val pageSize = 5
    val articleDataSourceFactory : ArticleDataSourceFactory
    init {

        articleDataSourceFactory = ArticleDataSourceFactory(RemoteAPIClient.getArticleService())
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        articleList = LivePagedListBuilder<Int, Article>(articleDataSourceFactory, config).build()
    }

    fun listIsEmpty(): Boolean {
        return articleList.value?.isEmpty() ?: true
    }

    fun getState(): LiveData<State> = Transformations.switchMap<ArticleDataSource,
            State>(articleDataSourceFactory.articleDataSourceLiveData, ArticleDataSource::state)


}
