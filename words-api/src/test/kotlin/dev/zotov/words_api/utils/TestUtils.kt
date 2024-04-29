package dev.zotov.words_api.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.mockwebserver.MockResponse

object TestUtils {

    fun createMockResponse(obj: Any): MockResponse {
        val json = Json.encodeToString(obj)
        val response = MockResponse()
        response.setBody(json)
        return response
    }
}