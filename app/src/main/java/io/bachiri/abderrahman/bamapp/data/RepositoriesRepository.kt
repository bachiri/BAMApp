package io.bachiri.abderrahman.bamapp.data

import io.reactivex.Single

class RepositoriesRepository(private val repositoriesDataSource: RepositoriesDataSource) :
    RepositoriesDataSource {
    override fun getRepositories(): Single<List<Repository>> {
        return repositoriesDataSource.getRepositories()
    }
}