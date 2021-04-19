package io.bachiri.abderrahman.bamapp

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import io.bachiri.abderrahman.bamapp.data.RepositoriesRepository
import io.bachiri.abderrahman.bamapp.repositories.RepositoriesViewModel

class ViewModelFactory(
    private val repositoriesRepository: RepositoriesRepository,
    owner: SavedStateRegistryOwner, defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T =
        with(modelClass) {
            when {
                isAssignableFrom(RepositoriesViewModel::class.java) -> RepositoriesViewModel(
                    repositoriesRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel ")
            }
        } as T

}