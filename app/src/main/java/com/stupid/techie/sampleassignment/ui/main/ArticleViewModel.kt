package com.stupid.techie.sampleassignment.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stupid.techie.sampleassignment.model.Article
import com.stupid.techie.sampleassignment.repository.ArticleRepo

class ArticleViewModel(private val articleRepo: ArticleRepo) : ViewModel() {

    val articleList = MutableLiveData<List<Article>>()

    fun getArticles() {

        articleRepo.getArticles()

    }

}
