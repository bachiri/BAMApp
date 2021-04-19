package io.bachiri.abderrahman.bamapp.data.remote

import io.bachiri.abderrahman.bamapp.data.Repository
import io.reactivex.Single
import retrofit2.http.GET

interface RepoAPI {
    //TODO To Specify The Page Dynamically
    @GET("/orgs/bamlab/repos?page=1")
    fun loadRepositories(): Single<List<Repository>>
}