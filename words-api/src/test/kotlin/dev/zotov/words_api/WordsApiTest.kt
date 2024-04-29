package dev.zotov.words_api

import dev.zotov.words_api.utils.TestData
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class WordsApiTest {

    private lateinit var wordsApi: WordsApi
    private lateinit var server: MockWebServer

    @Before
    fun setup() {
        server = MockWebServer()
        wordsApi = WordsApi(server.url("/").toString())
    }

    @Test
    fun shouldHandleDogWordDefinition() = runTest {
        val dogResponse = MockResponse().apply {
            setBody(TestData.WordDefinitionDtos.Raw.wordAccess)
        }
        server.enqueue(dogResponse)

        val data = wordsApi.getWordDefinition("access")
        server.takeRequest()

        Assert.assertEquals(1, data.body()!!.size)
        Assert.assertEquals(TestData.WordDefinitionDtos.wordAccess, data.body()!!.first())
    }

    @Test
    fun shouldHandleEmptyResponse() = runTest {
        val emptyResponse = MockResponse().apply { setBody("[]") }
        server.enqueue(emptyResponse)

        val data = wordsApi.getWordDefinition("empty")
        server.takeRequest()

        Assert.assertEquals(true, data.isSuccessful)
        Assert.assertEquals(emptyList<Any>(), data.body()!!)
    }
}