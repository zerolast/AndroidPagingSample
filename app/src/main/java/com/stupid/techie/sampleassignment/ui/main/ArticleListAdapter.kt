package com.stupid.techie.sampleassignment.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.stupid.techie.sampleassignment.R
import com.stupid.techie.sampleassignment.Utils.DateUtils
import com.stupid.techie.sampleassignment.model.Article


class ArticleListAdapter
    : PagedListAdapter<Article, RecyclerView.ViewHolder>(ArticleDiffCallback) {

    private var state = State.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArticleViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArticleViewHolder).bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_list_article
    }

    companion object {
        val ArticleDiffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }


    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }

    class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        lateinit var userName: TextView
        lateinit var userDesgination: TextView
        lateinit var articleImage: ImageView
        lateinit var imageViewUserProfile: ImageView
        lateinit var comment: TextView
        lateinit var likes: TextView

        lateinit var content: TextView
        lateinit var title: TextView
        lateinit var url: TextView

        lateinit var textViewDuration: TextView


        fun bind(articles: Article?) {
            if (articles != null) {
                userName = itemView.findViewById(R.id.textViewUserName) as TextView
                userDesgination = itemView.findViewById(R.id.textViewUserDesignation) as TextView
                textViewDuration = itemView.findViewById(R.id.textViewDuration) as TextView

                comment = itemView.findViewById(R.id.textViewComments) as TextView
                likes = itemView.findViewById(R.id.textViewLikes) as TextView
                articleImage = itemView.findViewById(R.id.imageViewArticleImg) as ImageView
                imageViewUserProfile = itemView.findViewById(R.id.imageViewUserProfile) as ImageView

                content = itemView.findViewById(R.id.textViewArticleContent) as TextView
                title = itemView.findViewById(R.id.textViewArticleTitle) as TextView
                url = itemView.findViewById(R.id.textViewArticleUrl) as TextView

                userName.text = articles.user.get(0).name
                userDesgination.text = articles.user.get(0).designation

                likes.text = articles.likes.toString() + " Likes"
                comment.text = articles.comments.toString() + " Comments"

                content.text = articles.content

                textViewDuration.text = DateUtils.printDifference(articles.createdAt)

                if (articles.media == null || articles.media.isEmpty()){
                    articleImage.visibility = View.GONE
                    title.visibility = View.GONE
                    url.visibility = View.GONE
                }else{

                    articleImage.visibility = View.VISIBLE
                    title.visibility = View.VISIBLE
                    url.visibility = View.VISIBLE

                    articles.media.let {
                        if(it.isNotEmpty())
                        title.text = it[0].title
                    }

                    articles.media.let {
                        if(it.isNotEmpty())
                        url.text = it[0].url
                    }

                    articles.media?.let {
                        if(it.isNotEmpty())
                        Picasso.get().load(it[0].image).into(articleImage)
                    }

                }

                articles.user.let {
                    Picasso.get().load(it.get(0).avatar).into(imageViewUserProfile)
                }
            }
        }

        companion object {
            fun create(parent: ViewGroup): ArticleViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_article, parent, false)
                return ArticleViewHolder(view)
            }
        }
    }
}