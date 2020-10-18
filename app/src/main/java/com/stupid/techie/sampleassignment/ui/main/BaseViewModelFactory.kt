package com.stupid.techie.sampleassignment.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stupid.techie.sampleassignment.repository.ArticleRepo

class BaseViewModelFactory(private val articleRepository: ArticleRepo) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArticleViewModel(articleRepository) as T
    }


}