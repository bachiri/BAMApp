package io.bachiri.abderrahman.bamapp.data

import com.google.gson.annotations.SerializedName


data class Repository(
    @SerializedName("name")
    val repoName: String,
    @SerializedName("language")
    val repoLanguage: String,
    @SerializedName("watchers")
    val repoWatchers: String

)

