package com.risadadobola

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MainViewModelTest {
    @Mock
    private lateinit var mockSoundPlayer: SoundPlayer

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mainViewModel = MainViewModel(mockSoundPlayer)
    }

    @Test
    fun onPlayPauseClick_startsSoundPlayerWhenNotPlaying() {
        mainViewModel.onPlayPauseClick()
        assert(mainViewModel.isPlaying)
        verify(mockSoundPlayer).play()
        verify(mockSoundPlayer, never()).stop()
    }

    @Test
    fun onPlayPauseClick_stopsSoundPlayerWhenPlaying() {
        mainViewModel.onPlayPauseClick() // First click to set isPlaying to true and call play()
        mainViewModel.onPlayPauseClick() // Second click to set isPlaying to false and call stop()
        assert(!mainViewModel.isPlaying)
        verify(mockSoundPlayer, times(1)).play() // Verify play was called once
        verify(mockSoundPlayer).stop()
    }

    @Test
    fun onPause_pausesSoundPlayerIfPlaying() {
        `when`(mockSoundPlayer.isPlaying()).thenReturn(true)
        mainViewModel.onPause()
        assert(mainViewModel.isPlayingBeforePause)
        verify(mockSoundPlayer).pause()
    }

    @Test
    fun onPause_doesNothingIfSoundPlayerNotPlaying() {
        `when`(mockSoundPlayer.isPlaying()).thenReturn(false)
        mainViewModel.onPause()
        assert(!mainViewModel.isPlayingBeforePause)
        verify(mockSoundPlayer, never()).pause()
    }

    @Test
    fun onResume_resumesSoundPlayerIfPlayingBeforePause() {
        `when`(mockSoundPlayer.isPlaying()).thenReturn(true) // Simulate playing before pause
        mainViewModel.onPause() // This sets isPlayingBeforePause to true
        mainViewModel.onResume()
        assert(!mainViewModel.isPlayingBeforePause)
        verify(mockSoundPlayer).resume()
    }

    @Test
    fun onResume_doesNothingIfSoundPlayerNotPlayingBeforePause() {
        mainViewModel.isPlayingBeforePause = false
        mainViewModel.onResume()
        verify(mockSoundPlayer, never()).resume()
    }

    @Test
    fun onCleared_stopsSoundPlayer() {
        mainViewModel.onCleared()
        verify(mockSoundPlayer).stop()
    }
}
