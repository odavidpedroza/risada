package com.risadadobola

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel(private val soundPlayer: SoundPlayer) : ViewModel() {
    var isPlaying by mutableStateOf(false)
        private set

    var isPlayingBeforePause by mutableStateOf(false)

    fun onPlayPauseClick() {
        isPlaying = !isPlaying
        if (isPlaying) {
            soundPlayer.play()
        } else {
            soundPlayer.stop()
        }
    }

    fun onPause() {
        if (soundPlayer.isPlaying()) {
            isPlayingBeforePause = true
            soundPlayer.pause()
        }
    }

    fun onResume() {
        if (isPlayingBeforePause) {
            soundPlayer.resume()
            isPlayingBeforePause = false
        }
    }

    public override fun onCleared() {
        super.onCleared()
        soundPlayer.stop()
    }
}
