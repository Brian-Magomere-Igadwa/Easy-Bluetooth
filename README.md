# Easy-Bluetooth
A mobile app that improves the bluetooth scanning user experience by simplifying it a notch higher.
#### Tech-stack
* Tech-stack
    * [Kotlin](https://kotlinlang.org/) - a cross-platform, statically typed, general-purpose programming language with type inference.
    * [Figma](https:/figma.com/) - a highly adopted UI/UX platform for designing great user expiriences.
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - lightweight threads to perfom asynchronous tasks.
    * [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - a type of stream of data that emit multiple values sequentially.
    * [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#:~:text=StateFlow%20is%20a%20state%2Dholder,property%20of%20the%20MutableStateFlow%20class.) - Flow APIs that enables flows to optimmaly emit state updated and emit values to multiple consumers.
    * [Dagger Hilt](https://dagger.dev/hilt/) - a pragmatic lightweight dependency injection framework.
    * [Jetpack](https://developer.android.com/jetpack)
        * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - is an observable data holder.
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform action when lifecycle state changes.
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious fashion.


* Architecture
    * MVVM - Clean architecture inspired

### App Architecture
A well planned architecture is integral for scalability. A common goal accross all architectures is - to help manage the  complexity of your app.Investing in architectural patterns almost always proves very useful in the long run especially in an environment that has a bigger team.


Read more about clean architecture [here](http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) <br />
Easy Bluetooth follows clean architecture guidelines set in it as:
#### 1. Domain
The domain layer serves as the application's central core. It operates independently of other layers, allowing domain models and business logic to remain isolated. Consequently, modifications in other layers, such as alterations to the user interface (presentation layer) or adjustments to the database (data layer), do not necessitate any changes within the domain layer. Components within the domain layer include:

<br/>
* Models: These define the fundamental structure of the data used within the application.

* Repositories: These are interfaces employed by the use cases and are implemented in the data layer.

#### 2. Data 
The data layer is responsible for selecting the appropriate data source for the domain layer, which, in this case, exclusively comprises a local source. It encompasses the implementations of the repositories declared in the domain layer. Components within the data layer encompass:* Repositories: Responsible for exposing data to the domain layer.
* Repositories: These are responsible for making data available to the domain layer.
* Mappers: They facilitate the transformation of data between domain models, DTOs (Data Transfer Objects), and entity models.
* Sources: These determine which data source (network or cache) will be utilized when retrieving data much of which is this case is more less just the Android Os.


#### 3. Presentation
The presentation layer consists of components responsible for rendering information to the user. The primary components within this layer are the composables and viewModels.






#### App Screenshots
##### Onboarding 
<img src="/art/screen_1.png" width="260">&emsp;<img src="/art/add1.jpg" width="260">
<img src="/art/screen_2.png" width="260">&emsp;<img src="/art/add1.jpg" width="260">
##### Results of scanned devices
<img src="/art/screen_3.png" width="260">&emsp;<img src="/art/add1.jpg" width="260">
