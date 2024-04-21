package dev.zotov.words_api

import dev.zotov.words_api.models.WordDefinitionDto
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface WordsApi {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordDefinition(@Path("word") word: String): Response<List<WordDefinitionDto>>
}


fun WordsApi(baseUrl: String): WordsApi {
    val json = Json {
        ignoreUnknownKeys = true
    }

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            json.asConverterFactory(
                MediaType.parse("application/json; charset=UTF8")!!,
            )
        )
        .build()

    return retrofit.create(WordsApi::class.java)
}