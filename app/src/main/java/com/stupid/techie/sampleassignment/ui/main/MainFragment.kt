package com.stupid.techie.sampleassignment.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stupid.techie.sampleassignment.R
import androidx.recyclerview.widget.DividerItemDecoration

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: ArticleViewModel
    private lateinit var articleListAdapter: ArticleListAdapter
    private lateinit var articleRecyclerView: RecyclerView
    private lateinit var progress_bar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root =  inflater.inflate(R.layout.main_fragment, container, false)
        articleRecyclerView =root.findViewById(R.id.recycler_view)
        progress_bar = root.findViewById(R.id.progress_bar)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this, BaseViewModelFactory()).get(ArticleViewModel::class.java)
        initAdapter()
       // initState()
    }


    private fun initAdapter() {
        articleListAdapter = ArticleListAdapter()
        articleRecyclerView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        articleRecyclerView.adapter = articleListAdapter
        viewModel.articleList.observe(viewLifecycleOwner, Observer {
            articleListAdapter.submitList(it)
        })

        val dividerItemDecoration = DividerItemDecoration(
            articleRecyclerView.context,
            RecyclerView.VERTICAL
        )
        articleRecyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun initState() {
        viewModel.getState().observe(viewLifecycleOwner, Observer { state ->
            progress_bar.visibility = if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            /*if (!viewModel.listIsEmpty()) {
                newsListAdapter.setState(state ?: State.DONE)
            }*/
        })
    }

}
