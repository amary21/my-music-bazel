# MyMusic with Jetpack Compose and Bazel

A modern Android music player application built with Jetpack Compose and Bazel build system. This app demonstrates the implementation of a clean architecture approach with MVVM pattern.

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
- Bazel build system
- Android SDK 21 or higher
- JDK 11 or higher

## Build Instructions

1. Clone the repository
2. Install Bazel (if not already installed)
3. Launch an Android emulator or connect a physical device
4. Run the following command to build and install the app:
   ```
   bazel mobile-install //app/src/main:app --start_app
   ```

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
