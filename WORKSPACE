# FIXME(alexeagle): move to bzlmod
workspace(name = "my_music_bazel")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

## JVM External (Maven)
http_archive(
    name = "rules_jvm_external",
    sha256 = "cd1a77b7b02e8e008439ca76fd34f5b07aecb8c752961f9640dea15e9e5ba1ca",
    strip_prefix = "rules_jvm_external-4.2",
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/4.2.zip",
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    name = "maven",
    artifacts = [
        "androidx.appcompat:appcompat:1.5.1",
        # Jetpack Compose Dependencies
        "androidx.activity:activity-compose:1.6.0",
        "androidx.compose.material:material:1.2.1",
        "androidx.compose.ui:ui:1.2.1",
        "androidx.compose.ui:ui-tooling:1.2.1",
        "androidx.compose.compiler:compiler:1.5.8",
        "androidx.compose.runtime:runtime:1.2.1",
        # Dependencies needed to manage version conflicts
        "androidx.core:core:1.6.0",
        "androidx.core:core-ktx:1.6.0",
        "androidx.savedstate:savedstate-ktx:1.2.0",
        "androidx.savedstate:savedstate:1.2.0",
        "androidx.lifecycle:lifecycle-livedata-core-ktx:2.5.1",
        "androidx.lifecycle:lifecycle-livedata-core:2.5.1",
        "androidx.lifecycle:lifecycle-livedata:2.5.1",
        "androidx.lifecycle:lifecycle-process:2.5.1",
        "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1",
        "androidx.lifecycle:lifecycle-runtime:2.5.1",
        "androidx.lifecycle:lifecycle-service:2.5.1",
        "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1",
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:2.5.1",
        "androidx.lifecycle:lifecycle-viewmodel:2.5.1",
        # Koin
        "io.insert-koin:koin-core:3.3.3",
        "io.insert-koin:koin-android:3.3.3",
        "io.insert-koin:koin-core-jvm:3.3.3",
        # Retrofit & Serialize Json
        "com.squareup.retrofit2:retrofit:2.11.0",
        "com.squareup.retrofit2:converter-gson:2.11.0",
        "com.google.code.gson:gson:2.10.1",
        "com.squareup.okhttp3:okhttp:4.12.0",
        # ExoPlayer
        "androidx.media3:media3-exoplayer:1.3.1",
        "androidx.media3:media3-ui:1.3.1"
    ],
    repositories = [
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
        "https://repo.maven.apache.org/maven2",
    ],
)

## Android

http_archive(
    name = "build_bazel_rules_android",
    sha256 = "cd06d15dd8bb59926e4d65f9003bfc20f9da4b2519985c27e190cddc8b7a7806",
    strip_prefix = "rules_android-0.1.1",
    urls = ["https://github.com/bazelbuild/rules_android/archive/v0.1.1.zip"],
)

load("@build_bazel_rules_android//android:rules.bzl", "android_sdk_repository")

android_sdk_repository(
    name = "androidsdk",
    path = "/Users/amary/Library/Android/sdk",  # Sesuaikan dengan lokasi SDK kamu
)

## Kotlin

http_archive(
    name = "rules_kotlin",
    sha256 = "d9898c3250e0442436eeabde4e194c30d6c76a4a97f517b18cefdfd4e345725a",
    url = "https://github.com/bazelbuild/rules_kotlin/releases/download/v1.9.1/rules_kotlin-v1.9.1.tar.gz",
)

load("@rules_kotlin//kotlin:repositories.bzl", "kotlin_repositories", "kotlinc_version")

kotlin_repositories()

load("@rules_kotlin//kotlin:core.bzl", "kt_register_toolchains")

kt_register_toolchains()
