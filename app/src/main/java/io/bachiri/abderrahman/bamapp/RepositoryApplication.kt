package io.bachiri.abderrahman.bamapp

import android.app.Application
import io.bachiri.abderrahman.bamapp.data.RepositoriesRepository

class RepositoryApplication:Application() {

    val repositoriesRepository: RepositoriesRepository
        get() = ServiceLocator.provideRepositoriesRepository()
}