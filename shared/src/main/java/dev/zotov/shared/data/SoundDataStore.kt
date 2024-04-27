package dev.zotov.shared.data

import android.app.Application
import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL
import javax.inject.Inject

interface SoundDataStore {
    suspend fun getAudioFilePath(url: String): String?
}

class SoundDataStoreImpl @Inject constructor(
    private val context: Application
) : SoundDataStore {
    private val sharedPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    companion object {
        const val TAG = "SoundDataStore"
        const val PREFS = "audio_cache_prefs"
    }

    override suspend fun getAudioFilePath(url: String): String? {
        // Get from cache
        var path = getFilePath(url)

        if (path != null && checkFile(url, path)) {
            return path
        }

        // Download file
        path = downloadFile(url)
        return path
    }

    private suspend fun downloadFile(url: String): String? = withContext(Dispatchers.IO) {
        try {
            val cacheDir = context.cacheDir
            val file = File.createTempFile("audio_", ".mp3", cacheDir)
            file.outputStream().use { output ->
                URL(url).openStream().use { input ->
                    input.copyTo(output)
                }
            }

            val path = file.absolutePath
            sharedPreferences.edit().putString(url, path).apply()
            path
        } catch (e: Throwable) {
            Log.e(TAG, "Failed to download file '$url'", e)
            null
        }
    }

    private fun checkFile(url: String, path: String): Boolean {
        val file = File(path)

        if (file.exists()) {
            return true
        }

        sharedPreferences.edit().remove(url).apply()
        return false
    }

    private fun getFilePath(url: String): String? = sharedPreferences.getString(url, null)
}