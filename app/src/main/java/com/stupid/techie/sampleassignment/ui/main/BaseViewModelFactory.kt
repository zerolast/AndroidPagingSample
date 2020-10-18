package com.stupid.techie.sampleassignment.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class BaseViewModelFactory:
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArticleViewModel() as T
    }


}