package com.example.silverlabs.datamodel

import com.google.gson.annotations.SerializedName

data class Celebrity(
    @SerializedName("height") val height: String,
    @SerializedName("age") val age: Int,
    @SerializedName("popularity") val popularity: Int,
    @SerializedName("photo") val photo: String

)

data class Celebrities(
    @SerializedName ("celebrities") val celebrities :List<Pair<String,Celebrity>>
)