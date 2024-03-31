# Skycast-TheWeatherForecast
# Weather History App

This repository contains an Android application developed in Kotlin that allows users to retrieve historical weather data for a specific date and year from a foreign country using a free weather API. The app also includes functionality to store this information in a local Room database, enabling access to weather data even without network connectivity.

# Tech Stacks Used:

Kotlin: The primary programming language used for developing the Android application.

Jetpack Compose: Used for building the UI components of the app, providing a modern and declarative way to create UI.

Room Database: Utilized for local storage of weather data, allowing efficient data access and manipulation.

ViewModel: Employed for managing UI-related data in a lifecycle-conscious manner, preserving data during configuration changes.

Weather API: Integrated a free weather API to fetch historical weather data in JSON format.
("https://archive-api.open-meteo.com/v1/era5?latitude=$latitude&longitude=$longitude&start_date=2012-01-01&end_date=2022-12-30&hourly=temperature_2m"
)

Coroutine: Implemented asynchronous programming using Kotlin Coroutines, enabling non-blocking operations for network calls and database transactions.

# Functionality:

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

Note: The weather API can provide temperature data only up to the year 2022. Any requests for year beyond this range may not return accurate results.

# App Working:

# HomePage(Input Page):
<img src="https://github.com/aanchalg06/Skycast-TheWeatherForecast/assets/108565060/a2ef8794-c441-4641-bf40-8b7c43cc97d1" width="400">

# Database Downloaded(DD option on the top-right corner):
<img src="https://github.com/aanchalg06/Skycast-TheWeatherForecast/assets/108565060/c49b43e6-4b68-40f6-99d4-ea04105111e2" width="400">

# Data Fetch(Net on):
<img src="https://github.com/aanchalg06/Skycast-TheWeatherForecast/assets/108565060/950c0f6c-e551-45c7-abbb-8edd04ec6039" width="400">

# Average Data Fetch(Net on):
<img src="https://github.com/aanchalg06/Skycast-TheWeatherForecast/assets/108565060/b1f4749b-5133-418f-b579-fff85a2ac94d" width="400">

# Data Fetch(Net off-Average as well as Normal- not loaded in Database- error handling):
<img src="https://github.com/aanchalg06/Skycast-TheWeatherForecast/assets/108565060/d96c73c5-0caa-4017-b94f-6f1876d1b831" width="400">

# Error(Invalid Country name):
<img src="https://github.com/aanchalg06/Skycast-TheWeatherForecast/assets/108565060/e8916a66-88e9-42fa-9108-921a70a0b700" width="400">


# How to Run:

Clone the repository to your local machine.

Open the project in Android Studio.

Connect your Android device or emulator.

Build and run the application.

# Contributors:
Aanchal Gupta
