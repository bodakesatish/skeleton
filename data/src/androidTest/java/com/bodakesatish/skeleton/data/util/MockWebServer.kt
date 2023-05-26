package com.bodakesatish.skeleton.data.util

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

/**
 * Enqueue the json from the provided file
 * @param filePath full filePath along with file name after [api_responses] directory
 * @param code response code from the json file
 */
internal fun MockWebServer.enqueueResponse(filePath: String, code: Int) {
    val inputStream = javaClass.classLoader?.getResourceAsStream("api_responses/$filePath")

    val source = inputStream?.let { inputStream.source().buffer() }
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(code)
                .setBody(source.readString(StandardCharsets.UTF_8))
        )
    }
}
