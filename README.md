# MyMusic with Jetpack Compose and Bazel

A modern Android music player application built with Jetpack Compose and Bazel build system. This app demonstrates the implementation of a clean architecture approach with MVVM pattern.

<p align="center">
  <img src="https://github.com/user-attachments/assets/039de7bf-2d60-4f57-b54d-b5af6272c5b8" width="250"/>
  <img src="https://github.com/user-attachments/assets/8d959c32-182d-4992-92b6-1f7ace5f174a" width="250"/>
</p>

## Features

- Music search functionality
- Music playback with ExoPlayer
- Play/pause, seek, previous/next track controls
- Bottom sheet player interface
- Modern UI with Jetpack Compose

## Architecture

The application follows a clean architecture approach with the following modules:

- **Feature Layer**: UI components and ViewModels
- **Domain Layer**: Business logic and use cases
- **Data Layer**: Repositories and data sources
- **Core**: Common utilities and services

### Technologies Used

- **UI**: Jetpack Compose
- **Dependency Injection**: Koin
- **Networking**: Retrofit with Gson
- **Media Playback**: ExoPlayer
- **Image Loading**: Coil
- **Asynchronous Programming**: Kotlin Coroutines
- **Build System**: Bazel

## Setup Requirements

- Android Studio Arctic Fox or newer
- Bazel 7.x or newer
- Android SDK 34 or higher
- Android Build Tools 34.0.0
- JDK 17 or higher
- ADB (Android Debug Bridge)

## Build Instructions

1. Clone the repository
2. Install Bazel (if not already installed)
3. Configure Android SDK path in `WORKSPACE.bzlmod` if different from default:
   ```python
   android_sdk_repository(
       name = "androidsdk",
       path = "/Users/macbookpro/Library/Android/sdk",  # Update this path
       api_level = 34,
       build_tools_version = "34.0.0",
   )
   ```
4. Launch an Android emulator or connect a physical device
5. Build and install the app:

   **Option 1: Using the install script (Recommended)**
   ```bash
   ./install.sh
   ```

   **Option 2: Manual build and install**
   ```bash
   bazel build //app/src/main:app
   adb install -r bazel-bin/app/src/main/app.apk
   adb shell am start -n com.amary.my.music/.MainActivity
   ```

### Note on mobile-install

Due to compatibility issues between Bazel 7.x and rules_android 0.1.1, the `bazel mobile-install` command is currently not working. Use the `install.sh` script or manual build/install commands instead.

## Development

### Bazel Build System

This project uses Bazel as the build system. For more information about Kotlin rules in Bazel, visit the [rules_kotlin documentation page](https://github.com/bazelbuild/rules_kotlin/blob/master/docs/kotlin.md).

### Project Structure

- `app/src/main/java/com/amary/my/music/` - Main application code
  - `feature/` - UI components and ViewModels
  - `domain/` - Business logic
  - `data/` - Data repositories
  - `core/` - Core utilities (network, ExoPlayer, coroutines)

## Dependencies

The project uses various libraries including:
- Jetpack Compose for UI
- Koin for dependency injection
- Retrofit for API communication
- ExoPlayer for media playback
- Coil for image loading

For a complete list of dependencies, refer to the `MODULE.bazel` file.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
