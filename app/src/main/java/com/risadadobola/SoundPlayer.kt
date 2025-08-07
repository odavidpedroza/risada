package com.risadadobola

import android.content.Context
import android.media.MediaPlayer

class SoundPlayer(
    private val context: Context,
    private val mediaPlayerFactory: ((Context, Int) -> MediaPlayer)? = null,
) {
    private var mediaPlayer: MediaPlayer? = null

    fun play() {
        mediaPlayer = mediaPlayerFactory?.invoke(context, R.raw.risada) ?: MediaPlayer.create(context, R.raw.risada)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
    }

    fun pause() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }
    }

    fun resume() {
        if (mediaPlayer?.isPlaying == false) {
            mediaPlayer?.start()
        }
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun isPlaying(): Boolean {
        return mediaPlayer?.isPlaying ?: false
    }
}
