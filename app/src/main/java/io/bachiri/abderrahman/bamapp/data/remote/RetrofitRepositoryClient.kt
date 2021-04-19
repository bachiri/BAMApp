package io.bachiri.abderrahman.bamapp.data.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRepositoryClient {

    companion object {

        private const val REPOSITORIES_BASE_URL = "https://api.github.com/"


        var retrofit: Retrofit? = null

        fun getRetrofitInstance(): Retrofit? {
            if (retrofit == null) {
                synchronized(RetrofitRepositoryClient::class.java) {
                    retrofit = Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(REPOSITORIES_BASE_URL)
                        .build()
                }

            }
            return retrofit

        }
    }
}