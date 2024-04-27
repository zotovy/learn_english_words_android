package dev.zotov.shared.services

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import dev.zotov.shared.data.SoundDataStore
import java.io.IOException
import javax.inject.Inject

interface SoundPlayerService {
    suspend fun playFromUrl(url: String)
}

class SoundPlayerServiceImpl @Inject constructor(
    private val soundDataStore: SoundDataStore,
) : SoundPlayerService {
    companion object {
        const val TAG = "SoundPlayerService"
    }

    override suspend fun playFromUrl(url: String) {
        try {
            val path = soundDataStore.getAudioFilePath(url)

            if (path == null) {
                Log.w(TAG, "Failed to playFromUrl. Path is null")
                return
            }

            var mediaPlayer: MediaPlayer? = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(path)
                prepare()
                start()
            }

            mediaPlayer?.setOnCompletionListener {
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
            }
        } catch (e: IOException) {
            Log.e(TAG, "Failed to play sound '$url'", e)
        }
    }
}