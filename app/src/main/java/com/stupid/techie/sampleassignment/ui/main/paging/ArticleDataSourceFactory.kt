package com.stupid.techie.sampleassignment.ui.main.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.stupid.techie.sampleassignment.model.Article
import com.stupid.techie.sampleassignment.network.ArticleService

class ArticleDataSourceFactory(
    private val articleService: ArticleService)
    : DataSource.Factory<Int, Article>() {

    val articleDataSourceLiveData = MutableLiveData<ArticleDataSource>()

    override fun create(): DataSource<Int, Article> {
        val articleDataSource = ArticleDataSource(articleService)
        articleDataSourceLiveData.postValue(articleDataSource)
        return articleDataSource
    }
}