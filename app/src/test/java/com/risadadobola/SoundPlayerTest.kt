package com.risadadobola

import android.content.Context
import android.media.MediaPlayer
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SoundPlayerTest {
    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockMediaPlayer: MediaPlayer

    private lateinit var soundPlayer: SoundPlayer

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        soundPlayer = SoundPlayer(mockContext) { _, _ -> mockMediaPlayer }
    }

    @Test
    fun play_startsMediaPlayerAndSetsLooping() {
        soundPlayer.play()
        verify(mockMediaPlayer).isLooping = true
        verify(mockMediaPlayer).start()
    }

    @Test
    fun pause_pausesMediaPlayerIfPlaying() {
        soundPlayer.play() // Initialize mediaPlayer
        `when`(mockMediaPlayer.isPlaying).thenReturn(true)
        soundPlayer.pause()
        verify(mockMediaPlayer).pause()
    }

    @Test
    fun pause_doesNothingIfMediaPlayerNotPlaying() {
        soundPlayer.play() // Initialize mediaPlayer
        `when`(mockMediaPlayer.isPlaying).thenReturn(false)
        soundPlayer.pause()
        verify(mockMediaPlayer, never()).pause()
    }

    @Test
    fun resume_startsMediaPlayerIfPaused() {
        soundPlayer.play() // Initialize mediaPlayer
        soundPlayer.pause() // Simulate pausing
        `when`(mockMediaPlayer.isPlaying).thenReturn(false)
        soundPlayer.resume()
        verify(mockMediaPlayer, times(2)).start() // One from play(), one from resume()
    }

    @Test
    fun resume_doesNothingIfMediaPlayerPlaying() {
        soundPlayer.play() // Initialize mediaPlayer and start it
        `when`(mockMediaPlayer.isPlaying).thenReturn(true)
        soundPlayer.resume()
        verify(mockMediaPlayer, times(1)).start() // Only one start() from play()
    }

    @Test
    fun stop_stopsAndReleasesMediaPlayer() {
        soundPlayer.play() // Ensure mediaPlayer is initialized
        soundPlayer.stop()
        verify(mockMediaPlayer).stop()
        verify(mockMediaPlayer).release()
    }

    @Test
    fun isPlaying_returnsTrueIfMediaPlayerPlaying() {
        soundPlayer.play() // Initialize mediaPlayer
        `when`(mockMediaPlayer.isPlaying).thenReturn(true)
        assert(soundPlayer.isPlaying())
    }

    @Test
    fun isPlaying_returnsFalseIfMediaPlayerNotPlaying() {
        soundPlayer.play() // Initialize mediaPlayer
        `when`(mockMediaPlayer.isPlaying).thenReturn(false)
        assert(!soundPlayer.isPlaying())
    }
}
