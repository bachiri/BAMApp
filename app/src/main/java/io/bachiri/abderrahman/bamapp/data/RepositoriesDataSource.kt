package io.bachiri.abderrahman.bamapp.data

import io.reactivex.Single

interface RepositoriesDataSource {
    fun getRepositories(): Single<List<Repository>>
}