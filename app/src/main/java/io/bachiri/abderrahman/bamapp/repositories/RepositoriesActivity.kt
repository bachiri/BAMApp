package io.bachiri.abderrahman.bamapp.repositories

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.bachiri.abderrahman.bamapp.R
import io.bachiri.abderrahman.bamapp.data.RepositoriesRepository
import io.bachiri.abderrahman.bamapp.data.Repository
import io.bachiri.abderrahman.bamapp.data.remote.RemoteDataSource
import io.bachiri.abderrahman.bamapp.data.remote.RepoAPI
import io.bachiri.abderrahman.bamapp.data.remote.RetrofitRepositoryClient

class RepositoriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val repositoriesRepository: RepositoriesRepository = RepositoriesRepository(
            RemoteDataSource(
                RetrofitRepositoryClient.getRetrofitInstance()?.create(RepoAPI::class.java)!!
            )
        )
        val repositoriesViewModel: RepositoriesViewModel = RepositoriesViewModel(
            repositoriesRepository
        )

        repositoriesViewModel.repositoriesList.observe(this, { repositoriesViewStates ->

            when (repositoriesViewStates) {

                is RepositoriesOnSuccess -> {
                    Log.d("RepositoriesActivity", repositoriesViewStates.data.toString())
                }
                is RepositoriesOnError -> {
                    Log.d("RepositoriesActivity", repositoriesViewStates.error)
                }
                is RepositoriesOnLoading -> {
                    Log.d("RepositoriesActivity", "Loading")
                }

            }

        })


    }
}

sealed class RepositoriesViewState
data class RepositoriesOnSuccess(val data: List<Repository>) : RepositoriesViewState()
object RepositoriesOnLoading : RepositoriesViewState()
data class RepositoriesOnError(val error: String) :
    RepositoriesViewState() //TODO Replace a string error with an exception to be more generalized