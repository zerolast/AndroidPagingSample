package com.stupid.techie.sampleassignment.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stupid.techie.sampleassignment.R
import com.stupid.techie.sampleassignment.repository.ArticleRepo

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, BaseViewModelFactory(ArticleRepo())).get(ArticleViewModel::class.java)
        viewModel.getArticles()

        viewModel.articleList.observe(viewLifecycleOwner, Observer {
            // observe the data
        })
    }

}
