package com.example.newsapplication.model


import com.google.gson.annotations.SerializedName

data class SourceX(
    @SerializedName("id")
    var id: Any?,
    @SerializedName("name")
    var name: String?
)