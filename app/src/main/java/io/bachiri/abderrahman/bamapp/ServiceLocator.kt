package io.bachiri.abderrahman.bamapp

import io.bachiri.abderrahman.bamapp.data.RepositoriesDataSource
import io.bachiri.abderrahman.bamapp.data.RepositoriesRepository
import io.bachiri.abderrahman.bamapp.data.remote.RemoteDataSource
import io.bachiri.abderrahman.bamapp.data.remote.RepoAPI
import io.bachiri.abderrahman.bamapp.data.remote.RetrofitRepositoryClient

object ServiceLocator {


    @Volatile
    private var repositoriesRepository: RepositoriesRepository? = null

    public fun provideRepositoriesRepository(): RepositoriesRepository {
        synchronized(this) {
            return repositoriesRepository ?: repositoriesRepository
            ?: createRepositoriesRepository()
        }
    }

    private fun createRepositoriesRepository(): RepositoriesRepository {
        val newRepo = RepositoriesRepository(getRemoteDataSource())
        repositoriesRepository = newRepo
        return newRepo
    }

    private fun getRemoteDataSource(): RepositoriesDataSource {
        //TODO To be refactored to delete the !!
        return RemoteDataSource(
            RetrofitRepositoryClient.getRetrofitInstance()?.create(RepoAPI::class.java)!!
        )
    }

}