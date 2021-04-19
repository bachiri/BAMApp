package io.bachiri.abderrahman.bamapp.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.bachiri.abderrahman.bamapp.data.RepositoriesRepository
import io.reactivex.disposables.CompositeDisposable

class RepositoriesViewModel(private val repositoriesRepository: RepositoriesRepository) :
    ViewModel() {

    private val _repositoriesList: MutableLiveData<RepositoriesViewState> = MutableLiveData()

    val repositoriesList: LiveData<RepositoriesViewState>
        get() = _repositoriesList


    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        initViewModel()
    }

    private fun initViewModel() {
        getRepositoriesList()


    }

    fun getRepositoriesList() {
        _repositoriesList.postValue(RepositoriesOnLoading)
        compositeDisposable.add(
            repositoriesRepository
                .getRepositories()
                .subscribe(
                    {
                        _repositoriesList.postValue(RepositoriesOnSuccess(it))
                    },
                    {
                        _repositoriesList.postValue(RepositoriesOnError("Something went wrong while loading BAM's repository "))

                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()

    }

}
