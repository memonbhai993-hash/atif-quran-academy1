# Atif Quran Academy Android App

## Project Overview

A professional Android mobile application for online Quran learning platform built with Kotlin and Jetpack Compose/AndroidX.

## Features

- **Student Registration**: Easy enrollment for Quran courses
- **Teacher Verification**: Admin-verified teacher profiles
- **Online Classes**: Live classes via Google Meet/Zoom integration
- **Course Management**: Multiple Quran courses (Noorani Qaida, Nazra, Hifz, Tajweed)
- **Contact Integration**: WhatsApp, Email, and Phone contact options
- **Admin Dashboard**: Manage teachers, students, and payments

## Tech Stack

- **Language**: Kotlin
- **UI**: XML Layouts with Material Design 3
- **Architecture**: MVVM (Model-View-ViewModel)
- **Backend**: Firebase (Firestore, Authentication, Storage)
- **Navigation**: Jetpack Navigation Component
- **Async**: Kotlin Coroutines & Flow
- **Build**: Gradle 8.4 with Android Gradle Plugin 8.1.4

## Project Structure

```
atifquranapp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/quranacademy/atifquranapp/
│   │   │   │   ├── data/
│   │   │   │   │   ├── model/          # Data classes
│   │   │   │   │   └── repository/     # Data repositories
│   │   │   │   ├── ui/
│   │   │   │   │   ├── splash/         # Splash screen
│   │   │   │   │   ├── main/           # Main activity
│   │   │   │   │   ├── home/           # Home fragment
│   │   │   │   │   ├── courses/        # Courses screens
│   │   │   │   │   ├── teachers/       # Teachers screens
│   │   │   │   │   ├── classes/        # Online classes
│   │   │   │   │   ├── contact/         # Contact screen
│   │   │   │   │   ├── registration/   # Registration forms
│   │   │   │   │   ├── admin/          # Admin panel
│   │   │   │   │   ├── webview/        # WebView for forms
│   │   │   │   │   └── youtube/        # YouTube player
│   │   │   │   └── util/               # Utilities
│   │   │   └── res/                    # Resources
│   │   └── test/                       # Unit tests
│   └── build.gradle.kts
├── gradle/
│   └── wrapper/
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```

## Setup Instructions

### Prerequisites

- Android Studio Hedgehog or newer
- JDK 17 or newer
- Android SDK 34
- Gradle 8.4

### Configuration

1. **Clone the repository**
2. **Open in Android Studio**: File → Open → Select project folder
3. **Configure Firebase**:
   - Create a Firebase project at https://console.firebase.google.com
   - Add an Android app with package name `com.quranacademy.atifquranapp`
   - Download `google-services.json` and replace the placeholder in `app/google-services.json`
   - Enable Firebase Authentication, Firestore, and Storage

### Build Commands

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run unit tests
./gradlew test

# Run lint checks
./gradlew lint

# Clean build
./gradlew clean
```

### Admin Credentials

Default admin credentials:
- Username: `admin`
- Password: `atif123`

## Google Forms Integration

Update the following URLs in `Constants.kt`:
- `STUDENT_REGISTRATION_FORM_URL` - Google Form for student registration
- `TEACHER_REGISTRATION_FORM_URL` - Google Form for teacher application
- `PAYMENT_UPLOAD_FORM_URL` - Google Form for payment proof upload

## Contact Information

For support, contact:
- Email: info@atifquranacademy.com
- WhatsApp: [Your WhatsApp Number]

## License

This project is proprietary software. All rights reserved.

## Version

- Version: 1.0
- Date: February 2026
- Author: Atif Ahmad
