package io.bachiri.abderrahman.bamapp.utils

import androidx.appcompat.app.AppCompatActivity
import io.bachiri.abderrahman.bamapp.RepositoryApplication
import io.bachiri.abderrahman.bamapp.ViewModelFactory

fun AppCompatActivity.getViewModelFactory(): ViewModelFactory {
    val repositoriesRepository = (this.application as RepositoryApplication).repositoriesRepository
    return ViewModelFactory(repositoriesRepository, this)

}