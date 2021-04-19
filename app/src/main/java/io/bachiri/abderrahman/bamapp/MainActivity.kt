package io.bachiri.abderrahman.bamapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.bachiri.abderrahman.bamapp.data.RepositoriesRepository
import io.bachiri.abderrahman.bamapp.data.remote.RemoteDataSource
import io.bachiri.abderrahman.bamapp.data.remote.RepoAPI
import io.bachiri.abderrahman.bamapp.data.remote.RetrofitRepositoryClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val repositoriesRepository: RepositoriesRepository = RepositoriesRepository(
                RemoteDataSource(RetrofitRepositoryClient.getRetrofitInstance()?.create(RepoAPI::class.java)!!))
        repositoriesRepository.getRepositories().subscribe(
                { Log.d("Repositories Are ", it.toString())},
                { Log.d("Error is ", it.toString())}

        )

    }
}