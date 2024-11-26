# 🎬 Disney Android App

This Android project is a Disney-themed app built using Jetpack Compose, ViewModels, Flows, Clean Architecture, and Hilt. The app fetches data from the Disney API and displays a list of Disney characters, movies, or related content. It follows modern Android development practices to ensure scalability, maintainability, and testability.

- 🌐 URL: https://api.disneyapi.dev

## 🚀 Key Features

- Jetpack Compose:
Uses Jetpack Compose to create modern, declarative UI components for the app.

- API Integration:
Fetches data from the Disney API (characters, movies, etc.) and displays it in the UI.

- ViewModels and StateFlow:
ViewModels manage UI-related data in a lifecycle-conscious way.
StateFlow is used for reactive state management in the UI.

- Implements Clean Architecture to separate concerns between UI, business logic, and data management.

- Hilt Dependency Injection:
Hilt is used to inject dependencies across the app, simplifying DI and enhancing testability.

## 🔧 Pros of Clean Architecture

- Separation of Concerns: Divides the app into distinct layers for UI, business logic, and data management.
- Testability: Each layer can be tested independently, improving reliability.
- Scalability: Easily add new features without impacting other parts of the app.
- Maintainability: Makes the app easier to maintain and extend over time.
- Reusability: The Domain Layer is decoupled from UI frameworks and data sources, allowing for reuse in different contexts.

## ⚠️ Cons of Clean Architecture

- Complexity: The architecture can feel complex for smaller apps or simple use cases.
- Boilerplate Code: Clean Architecture may introduce some initial boilerplate code, making the app setup longer.

