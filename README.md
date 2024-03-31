# Skycast-TheWeatherForecast
# Weather History App

This repository contains an Android application developed in Kotlin that allows users to retrieve historical weather data for a specific date and year from a foreign country using a free weather API. The app also includes functionality to store this information in a local Room database, enabling access to weather data even without network connectivity.

# Tech Stacks Used:

Kotlin: The primary programming language used for developing the Android application.
Jetpack Compose: Used for building the UI components of the app, providing a modern and declarative way to create UI.
Room Database: Utilized for local storage of weather data, allowing efficient data access and manipulation.
ViewModel: Employed for managing UI-related data in a lifecycle-conscious manner, preserving data during configuration changes.
Weather API: Integrated a free weather API to fetch historical weather data in JSON format.
Coroutine: Implemented asynchronous programming using Kotlin Coroutines, enabling non-blocking operations for network calls and database transactions.
RecyclerView: Used to display lists of weather data in the app's UI.
Functionality:

# Weather Data Retrieval:

Users can input a specific date and year for the desired weather data.
The app fetches historical weather data using a free weather API in JSON format.
The JSON response is parsed to extract the maximum and minimum temperatures for the specified date.
# Database Integration:

The app allows users to store retrieved weather data in a local Room database.
Upon successful retrieval of weather data, it is inserted into the database.
Users can query the database to retrieve maximum and minimum temperatures for a given date.
In case the requested date is in the future, the app computes the average of the last 10 available years' temperatures and provides it as output.
# UI Design:

The UI is built using Jetpack Compose, offering a modern and intuitive user experience.
Users can input the date and year through text fields.
# File Structure:

src/main/java/com/example/weatherhistory: Contains Kotlin source files for the application logic.
app/src/main/res: Contains XML layout files for UI components and other resources.
Note: The weather API can provide temperature data only up to the year 2022. Any requests for dates beyond this range may not return accurate results.
# App Working:

HomePage(Input Page):
# How to Run:

Clone the repository to your local machine.
Open the project in Android Studio.
Connect your Android device or emulator.
Build and run the application.
# Contributors:
Aanchal Gupta
