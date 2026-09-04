# Implementation Plan - Tebak Buah, Sayur & Hewan Kids Game

A modern, child-friendly picture guessing game app ("Tebak Buah, Sayur & Hewan") developed in Jetpack Compose, featuring a 4-second splash screen, a 2x2 grid main menu, multiple game modes, score history tracking, and professional Google Play / AdMob compliance & disclaimer.

## User Review Required

> [!IMPORTANT]
> - **Jetpack Compose & Material 3**: We will enable Compose BOM (`2026.08.00`) and Material 3 to build a modern, high-performance UI tailored for kids.
> - **Game Categories**: Implemented two engaging modes: **Tebak Buah & Sayur** and **Tebak Hewan** with rich image/icon representations and multiple-choice questions in Bahasa Indonesia.
> - **Data Persistence**: Score history is persisted locally using SharedPreferences and JSON.
> - **Compliance**: About screen includes comprehensive content disclaimers, educational purpose notices, and Google Play / AdMob ready guidelines.

## Open Questions

- None. All requirements (4s splash screen, 2x2 grid main menu, play mode selection, history score, about with content disclaimer, modern kids theme) are fully addressed.

## Proposed Changes

### Build Configuration
#### [MODIFY] [libs.versions.toml](file:///C:/Users/IT BSGA/AndroidStudioProjects/tebakbuahsayur/gradle/libs.versions.toml)
- Add Compose BOM version and library aliases.

#### [MODIFY] [build.gradle.kts](file:///C:/Users/IT BSGA/AndroidStudioProjects/tebakbuahsayur/app/build.gradle.kts)
- Enable Jetpack Compose build features and add dependencies (`compose.bom`, `ui`, `material3`, `activity-compose`, `navigation-compose`, `gson`).

---

### Core Data & Repository (`com.cococue.tebakbuahsayur.data`)
#### [NEW] [Models.kt](file:///C:/Users/IT BSGA/AndroidStudioProjects/tebakbuahsayur/app/src/main/java/com/cococue/tebakbuahsayur/data/Models.kt)
- Define `Question`, `GameCategory`, and `ScoreRecord` data classes.

#### [NEW] [GameRepository.kt](file:///C:/Users/IT BSGA/AndroidStudioProjects/tebakbuahsayur/app/src/main/java/com/cococue/tebakbuahsayur/data/GameRepository.kt)
- Provide rich datasets for Buah & Sayur and Hewan questions in Bahasa Indonesia.

#### [NEW] [ScoreManager.kt](file:///C:/Users/IT BSGA/AndroidStudioProjects/tebakbuahsayur/app/src/main/java/com/cococue/tebakbuahsayur/data/ScoreManager.kt)
- Handle local persistence of game score history using SharedPreferences & Gson.

---

### UI Theme & Screens (`com.cococue.tebakbuahsayur.ui`)
#### [NEW] [Theme.kt](file:///C:/Users/IT BSGA/AndroidStudioProjects/tebakbuahsayur/app/src/main/java/com/cococue/tebakbuahsayur/ui/theme/Theme.kt)
- Modern kids color palette, rounded shapes, and cheerful typography.

#### [NEW] [SplashScreen.kt](file:///C:/Users/IT BSGA/AndroidStudioProjects/tebakbuahsayur/app/src/main/java/com/cococue/tebakbuahsayur/ui/SplashScreen.kt)
- 4-second splash screen with animated cute mascot/logo, automatically navigating to Main Menu.

#### [NEW] [MainMenuScreen.kt](file:///C:/Users/IT BSGA/AndroidStudioProjects/tebakbuahsayur/app/src/main/java/com/cococue/tebakbuahsayur/ui/MainMenuScreen.kt)
- Modern kids main menu with 2x2 grid buttons:
  1. **Bermain (Play)** -> Category selection
  2. **Riwayat Skor (History Score)**
  3. **Cara Bermain (How to Play)**
  4. **Tentang & Disclaimer (About)**

#### [NEW] [CategorySelectionScreen.kt](file:///C:/Users/IT BSGA/AndroidStudioProjects/tebakbuahsayur/app/src/main/java/com/cococue/tebakbuahsayur/ui/CategorySelectionScreen.kt)
- Selection between "Tebak Buah & Sayur" and "Tebak Hewan".

#### [NEW] [GameScreen.kt](file:///C:/Users/IT BSGA/AndroidStudioProjects/tebakbuahsayur/app/src/main/java/com/cococue/tebakbuahsayur/ui/GameScreen.kt)
- Interactive picture guessing game interface with options, score tracking, and feedback.

#### [NEW] [ResultScreen.kt](file:///C:/Users/IT BSGA/AndroidStudioProjects/tebakbuahsayur/app/src/main/java/com/cococue/tebakbuahsayur/ui/ResultScreen.kt)
- Final score summary, stats, and navigation back to menu or retry.

#### [NEW] [HistoryScreen.kt](file:///C:/Users/IT BSGA/AndroidStudioProjects/tebakbuahsayur/app/src/main/java/com/cococue/tebakbuahsayur/ui/HistoryScreen.kt)
- Display score history with clear data option.

#### [NEW] [HowToPlayScreen.kt](file:///C:/Users/IT BSGA/AndroidStudioProjects/tebakbuahsayur/app/src/main/java/com/cococue/tebakbuahsayur/ui/HowToPlayScreen.kt)
- Instructions for children and parents.

#### [NEW] [AboutScreen.kt](file:///C:/Users/IT BSGA/AndroidStudioProjects/tebakbuahsayur/app/src/main/java/com/cococue/tebakbuahsayur/ui/AboutScreen.kt)
- App version, publisher info, content disclaimer, and Google Play / AdMob compliance notices.

#### [MODIFY] [MainActivity.kt](file:///C:/Users/IT BSGA/AndroidStudioProjects/tebakbuahsayur/app/src/main/java/com/cococue/tebakbuahsayur/MainActivity.kt)
- Set up NavHost connecting all screens.

---

### Manifest & Resources
#### [MODIFY] [AndroidManifest.xml](file:///C:/Users/IT BSGA/AndroidStudioProjects/tebakbuahsayur/app/src/main/AndroidManifest.xml)
- Configure app name, screen orientation (portrait), and permissions.

## Verification Plan

### Automated Tests
- Build project using `gradle_build` (`app:assembleDebug`) to ensure zero compilation or syntax errors.
- Run unit test for game repository and score persistence.

### Manual Verification
- Deploy app to connected emulator/device.
- Observe 4-second splash screen transition.
- Test 2x2 grid navigation on Main Menu.
- Play both game categories, check score calculation and persistence in History Score.
- Verify About screen content disclaimer and modern kids UI styling.
