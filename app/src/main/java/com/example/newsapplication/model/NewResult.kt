package com.example.newsapplication.model


import com.google.gson.annotations.SerializedName

data class NewResult(
    @SerializedName("sources")
    var sources: List<Source>?,
    @SerializedName("status")
    var status: String?
)