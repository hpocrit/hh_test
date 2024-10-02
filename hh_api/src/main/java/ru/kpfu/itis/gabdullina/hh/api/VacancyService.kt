package ru.kpfu.itis.gabdullina.hh.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.kpfu.itis.gabdullina.hh.api.model.Response

// u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download/
interface VacancyService {

    @GET("u/0/uc")
    suspend fun getAll(
        @Query("id") appid: String = "1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r",
        @Query("export") export: String = "download"
    ): Response
}

fun VacancyService(): VacancyService {
    val okHttpClient = OkHttpClient.Builder()
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://drive.usercontent.google.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(VacancyService::class.java)
}
