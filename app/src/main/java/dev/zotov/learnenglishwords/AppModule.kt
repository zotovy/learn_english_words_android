package dev.zotov.learnenglishwords

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.zotov.database.AppDatabase
import dev.zotov.words_api.WordsApi
import dev.zotov.words_data.WordsRepository
import dev.zotov.words_data.WordsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesWordsApi(): WordsApi {
        val apiUrl = BuildConfig.WORDS_API_BASE_URL
        return WordsApi(apiUrl)
    }

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext application: Context): AppDatabase {
        return AppDatabase(application)
    }

    @Provides
    @Singleton
    fun provideWordsRepository(
        wordsApi: WordsApi,
        appDatabase: AppDatabase,
    ): WordsRepository {
        return WordsRepositoryImpl(
            wordsApi = wordsApi,
            appDatabase = appDatabase,
        )
    }
}