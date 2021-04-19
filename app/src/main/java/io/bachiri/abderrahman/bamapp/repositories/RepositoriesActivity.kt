package io.bachiri.abderrahman.bamapp.repositories

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import io.bachiri.abderrahman.bamapp.R
import io.bachiri.abderrahman.bamapp.data.RepositoriesRepository
import io.bachiri.abderrahman.bamapp.data.Repository
import io.bachiri.abderrahman.bamapp.data.remote.RemoteDataSource
import io.bachiri.abderrahman.bamapp.data.remote.RepoAPI
import io.bachiri.abderrahman.bamapp.data.remote.RetrofitRepositoryClient
import io.bachiri.abderrahman.bamapp.utils.getViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class RepositoriesActivity : AppCompatActivity() {

    private val repositoriesAdapter: RepositoryAdapter =
        RepositoryAdapter(this@RepositoriesActivity, mutableListOf())




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initRecyclerView()
        initViewModel()



    }

    private fun initViewModel() {

        val repositoriesViewModel: RepositoriesViewModel =
            ViewModelProvider(this, getViewModelFactory()).get(RepositoriesViewModel::class.java)

        repositoriesViewModel.repositoriesList.observe(this, { repositoriesViewStates ->

            when (repositoriesViewStates) {

                is RepositoriesOnSuccess -> {
                    networkLoading.visibility = View.GONE
                    repositoriesRecyclerView.visibility = View.VISIBLE
                    displayRepositories(repositoriesViewStates.data)
                    repositoryRefresher.isRefreshing = false
                }
                is RepositoriesOnError -> {
                    networkLoading.visibility = View.GONE
                    Toast.makeText(
                        this@RepositoriesActivity,
                        "Something happened plz try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is RepositoriesOnLoading -> {
                    repositoriesRecyclerView.visibility = View.GONE
                    networkLoading.visibility = View.VISIBLE
                }

            }

        })

        repositoryRefresher.setOnRefreshListener {
            repositoriesViewModel.getRepositoriesList()
        }

    }

    private fun displayRepositories(data: List<Repository>) {
        repositoriesAdapter.setData(data)
    }

    private fun initRecyclerView() {
        val gridLayoutManager = GridLayoutManager(repositoriesRecyclerView.context, 2)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        repositoriesRecyclerView.layoutManager = gridLayoutManager
        repositoriesRecyclerView.adapter = repositoriesAdapter
    }
}

sealed class RepositoriesViewState
data class RepositoriesOnSuccess(val data: List<Repository>) : RepositoriesViewState()
object RepositoriesOnLoading : RepositoriesViewState()
data class RepositoriesOnError(val error: String) :
    RepositoriesViewState() //TODO Replace a string error with an exception to be more generalized