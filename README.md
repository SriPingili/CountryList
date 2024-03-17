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
- handling orientation changes
- testing (added test cases for view model)

Note: I've observed that in certain cases, an empty string is returned from the server for the
'capital' field. It appears that this empty string is deliberate, indicating that these countries
lack a designated capital. To improve user experience, we could consider displaying informative
text such as 'No capital exists' instead of an empty string.

![tablet](https://github.com/SriPingili/CountryList/assets/16881321/b6f20548-a060-400b-86f9-944538edf092)


