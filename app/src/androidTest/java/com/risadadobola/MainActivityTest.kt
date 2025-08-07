package com.risadadobola

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun playPauseButton_changesIcon() {
        // Initial state: Play icon
        composeTestRule.onNodeWithContentDescription("Play").assertExists()
        composeTestRule.onNodeWithContentDescription("Pause").assertDoesNotExist()

        // Click to play
        composeTestRule.onNodeWithContentDescription("Play").performClick()
        composeTestRule.onNodeWithContentDescription("Pause").assertExists()
        composeTestRule.onNodeWithContentDescription("Play").assertDoesNotExist()

        // Click to pause
        composeTestRule.onNodeWithContentDescription("Pause").performClick()
        composeTestRule.onNodeWithContentDescription("Play").assertExists()
        composeTestRule.onNodeWithContentDescription("Pause").assertDoesNotExist()
    }
}
