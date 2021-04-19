package io.bachiri.abderrahman.bamapp.data.remote

import io.bachiri.abderrahman.bamapp.data.RepositoriesDataSource
import io.bachiri.abderrahman.bamapp.data.Repository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RemoteDataSource(private val repoAPI: RepoAPI) : RepositoriesDataSource {
    override fun getRepositories(): Single<List<Repository>> {
        return repoAPI
            .loadRepositories()
            .map {
                if (!it.isNullOrEmpty()) {
                    it
                } else listOf()
            }.subscribeOn(Schedulers.io())

    }
}