package com.example.newsapplication.retrofit

import com.example.newsapplication.model.NewResult
import com.example.newsapplication.model.NewsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceEndpoint {
    @GET("sources?")
    fun getNewsSource(@Query("apiKey") key: String): Call<NewResult>

    @GET("top-headlines")
    fun getHeadline(@Query("country")country:String,@Query("apiKey")key:String):Call<NewsList>

}