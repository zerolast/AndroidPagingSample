package com.stupid.techie.sampleassignment.model

data class Article(val id:String,val createdAt:String,val content: String, val comments:Int, val likes:Int, val media:List<Media>?, val user:List<User>)