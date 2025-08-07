# Risada do Bola

## Project Overview

"Risada do Bola" is a simple Android application built with Jetpack Compose that plays a looping audio clip ("risada.mp3") and displays a dynamic image based on the playback state. The app also includes features for sharing the audio file and rating the app on the Google Play Store, along with an integrated AdMob banner for monetization.

This project serves as a demonstration of modern Android development practices, showcasing a clean architecture, adherence to SOLID principles, comprehensive testing, and robust code styling.

## Features

*   **Play/Pause Audio:** A central button allows users to toggle the playback of a looping "risada.mp3" audio clip.
*   **Dynamic Image Display:** The main image on the screen changes between "rindo.jpeg" (when playing) and "serio.jpeg" (when paused), providing visual feedback to the user.
*   **Share Functionality:** Users can share the "risada.mp3" audio file with a pre-defined promotional message and an app store link via various sharing platforms.
*   **Rate App:** A dedicated button directs users to the app's listing on the Google Play Store, encouraging them to rate the application.
*   **AdMob Integration:** An AdMob banner is displayed at the bottom of the screen for monetization purposes.
*   **Multilingual Support:** The app supports English and Brazilian Portuguese for all user-facing strings.

## Architecture

The application is built following the **MVVM (Model-View-ViewModel)** architectural pattern, promoting a clear separation of concerns, testability, and maintainability.

*   **View (MainActivity):** The `MainActivity` is responsible for observing the UI state from the `MainViewModel` and rendering the user interface using Jetpack Compose. It delegates user interactions directly to the ViewModel.
*   **ViewModel (MainViewModel):** The `MainViewModel` holds and manages the UI-related data in a lifecycle-conscious way. It exposes observable data to the View and handles business logic, interacting with the Model layer (e.g., `SoundPlayer`, `ShareManager`, `RateAppManager`).
*   **Model (SoundPlayer, ShareManager, RateAppManager):** This layer encapsulates the application's business logic and data operations.
    *   `SoundPlayer`: Manages the playback, pausing, resuming, and stopping of the audio clip. It handles the `MediaPlayer` instance and its lifecycle.
    *   `ShareManager`: Encapsulates the logic for preparing and initiating the audio file sharing intent.
    *   `RateAppManager`: Handles the logic for directing users to the app's Google Play Store listing.

## SOLID Principles Applied

*   **Single Responsibility Principle (SRP):** Each class (`MainActivity`, `MainViewModel`, `SoundPlayer`, `ShareManager`, `RateAppManager`) has a single, well-defined responsibility. For example, `SoundPlayer` only deals with audio playback, and `ShareManager` only handles sharing.
*   **Open/Closed Principle (OCP):** The design allows for extensions without modification. New features can be added by creating new managers or modifying existing ones without altering core components like `MainActivity` or `MainViewModel`.
*   **Liskov Substitution Principle (LSP):** (Implicitly applied through proper inheritance and interface usage in Android/Compose frameworks).
*   **Interface Segregation Principle (ISP):** (Implicitly applied through the use of focused classes and functions).
*   **Dependency Inversion Principle (DIP):** Dependencies are abstracted (e.g., `SoundPlayer` is injected into `MainViewModel` via a factory), allowing for easier testing and swapping of implementations.

## Technologies Used

*   **Kotlin:** The primary programming language for Android development.
*   **Jetpack Compose:** Modern toolkit for building native Android UI.
*   **Android Architecture Components:**
    *   **ViewModel:** Manages UI-related data in a lifecycle-conscious way.
    *   **Lifecycle:** Manages activity and fragment lifecycles.
*   **Google Mobile Ads SDK:** For integrating AdMob banner advertisements.
*   **Gradle Kotlin DSL:** For build configuration.
*   **Mockito:** Mocking framework for unit tests.
*   **JUnit:** Testing framework for unit tests.
*   **Compose Test Rule:** For writing UI instrumentation tests.
*   **Ktlint:** A static code analysis tool for enforcing Kotlin coding conventions.
*   **JaCoCo:** Code coverage library for Java and Kotlin.

## Testing

The project includes a comprehensive testing strategy to ensure code quality and reliability:

*   **Unit Tests:**
    *   Implemented for `SoundPlayer`, `MainViewModel`, `ShareManager`, and `RateAppManager`.
    *   Utilizes Mockito for mocking dependencies, allowing for isolated testing of business logic.
    *   Achieves **100% code coverage** for these unit-testable components, ensuring all code paths are exercised.
*   **Instrumentation Tests:**
    *   Implemented for `MainActivity` to verify UI behavior and interactions.
    *   Uses `createAndroidComposeRule` to launch the activity and interact with Compose UI elements.
    *   Verifies icon changes on button clicks.

## Code Style

Ktlint is integrated into the Gradle build process to enforce consistent Kotlin coding conventions across the codebase. This ensures readability, maintainability, and adherence to best practices.

## Setup and Run

To set up and run the project locally:

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/odavidpedroza/risada
    cd RisadaDoBola
    ```
2.  **Open in Android Studio:**
    Open the project in Android Studio (Jellyfish | 2023.3.1 or newer recommended).
3.  **Sync Gradle:**
    Allow Gradle to sync and download all necessary dependencies.
4.  **Run on a device or emulator:**
    Select a connected Android device or emulator and click the "Run" button in Android Studio.

## Running Tests

*   **Run Unit Tests:**
    ```bash
    ./gradlew testDebugUnitTest
    ```
*   **Run Instrumentation Tests:**
    ```bash
    ./gradlew connectedDebugAndroidTest
    ```
*   **Generate Code Coverage Report:**
    ```bash
    ./gradlew :app:jacocoTestReport
    ```
    The report will be generated in `app/build/reports/jacoco/jacocoTestReport/html/index.html`. Open this file in your web browser to view the detailed coverage report.
