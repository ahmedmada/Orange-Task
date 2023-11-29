package com.ahmed.domain.model.photo

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photo(

    @SerializedName("id") var id: String? = null,
    @SerializedName("owner") var owner: String? = null,
    @SerializedName("secret") var secret: String? = null,
    @SerializedName("server") var server: String? = null,
    @SerializedName("farm") var farm: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("ispublic") var ispublic: Int? = null,
    @SerializedName("isfriend") var isfriend: Int? = null,
    @SerializedName("isfamily") var isfamily: Int? = null

)