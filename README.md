## Tech stack

- Minimum SDK level 24
- Target SDK level 34
- [Kotlin](https://kotlinlang.org/)
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- Architecture - MVVM Architecture (Model - View - ViewModel)
- Jetpack components - LiveData, ViewModel, DataBinding
- [Retrofit2](https://github.com/square/retrofit)

## Steps to run the app

- To run, simply open the project in Android Studio, build it and run it
- I have used Android Studio Hedgehog | 2023.1.1 to run this app.

## Areas focussed on

- using the right the architecture for the app (MVVM)
- handling the possible errors gracefully
- testing (added test cases for view model)

NOTE: I have noticed that for some of the countries an empty string is returned from the server for
capital. Initially, I gave it a thought on handling this, however a quick google search revealed
those countries do not have capital and an empty string is intentional, and hence leaving it as-is
