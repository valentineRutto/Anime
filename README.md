# Anime Features
The following are the features of the application;

-It should have bottom navigation.

-It should have a page that pulls the top 25 animes and lists them. The link to the API is; [anime](https://api.jikan.moe/v4/top/anime). Further API Documentation can be found here; [Jikan REST API v4 Docs](https://docs.api.jikan.moe/#tag/top/operation/getTopReviews).

-The relevant data should be stored on the app using SQLite.

-A user should be able to refresh the page and the new data should overwrite the stored one.

-It should have another page that allows for uploading of images to the following URL [search](https://api.trace.moe/search). The response should be displayed below the image after the API call is complete. Only display relevant information such as title and episode. API documentation can be found here; [API Docs](soruly.github.io)


# Tech Stack<br/>
-[Kotlin](https://developer.android.com/kotlin?gclid=CjwKCAjw9r-DBhBxEiwA9qYUpWK_ANJvWx6zBkFk-4XeP5a0dCxwyFZv_EeeqAcUx1K_Mj3gGkpdxRoCW9IQAvD_BwE&gclsrc=aw.ds)- a cross-platform, statically typed, general-purpose programming language with type inference.<br/>
-Coroutines - perform background operations.<br/>
-[KOIN](https://insert-koin.io/) - a pragmatic lightweight dependency injection framework.<br/>
-[ROOM](https://developer.android.com/training/data-storage/room) - persistence library providing an abstraction over SQL .<br/>
[Jetpack](https://developer.android.com/jetpack) -<br/>
-[Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android.<br/><br/>


# Architecture<br/>
The app contains these packages: 

#### Data

- ```data-remote```

Handles data interacting with the network and is later serverd up to the presentation layer through 
domain object

- ```data-local```

Handles persistence of object with Room ORM from.This module is responsible for handling all local related
logic and serves up data to and from the presentation layer through domain objects.

With this separation we can easily swap in new or replace the database being used without causeing
major ripples across the codebase.

#### Repository
Gets data from api and room and distributes it to the rest of the app
#### DI
Koin handles dependency injection on components making it easier to reuse
#### util
This package contains utility functions like networkresult which are used throughtout the application 
#### ui
contains views that are shown to the user

# Installation
Clone the repo and run on emulator or phone  or download the apk file 

# Screenshots
![Anime List](https://github.com/valentineRutto/Anime/blob/main/animelist.png)
![Photo Picker](https://github.com/valentineRutto/Anime/blob/main/takepic.png)
![Upload Photo](https://github.com/valentineRutto/Anime/blob/main/imageupload.png)



