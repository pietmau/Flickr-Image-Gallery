
# This application is clearly over engineered :-)
Becuase its only an experiment.

# MVI
Contains a basic implementation of the MVI pattern based on
[Spotify's Mobius](https://github.com/spotify/mobius).

The implementation can be found in the [usecase](./usecases/) module.

The view is effectively decoupled from Mobius or any framework:

all the view cares about
are [Models](./usecases/src/main/java/com/pppp/usecases/Model.kt) (that it receives and renders on screen)
and [Effects](./usecases/src/main/java/com/pppp/usecases/Effect.kt) (that it emits when the user interacts).

`Model`, `Event`, `Effect` are super simple immutable data classes.

The view is completely stateless.

## The state
The state is stored in the `Controller`, retained in a `ViewModel`.

The Controller re-emits the latest `Model` when connects.

Long running operations happen in the `UseCase` (specifically in the
[`RepositoryUseCase`](./repository/src/main/java/com/pppp/flickrimagegallery/RepositoryuseCase.kt)).

The `Activity` connects and disconnects in `onResume` and `onPause`,
not leaking the connection.


# Architecture
A simple clean architecture ([see graph](docs/architecture.md)) of Gradle
modules connected via Dagger.


# Coroutines
Coroutines and Kotlin instead of RxJava (see [`RepositoryUseCase`](./repository/src/main/java/com/pppp/flickrimagegallery/RepositoryuseCase.kt)).

# Libraries
Mobius, Dagger and:
* Android X
* LeakCanary
* Retrofit
* Architecture Components:
  * Room
  * ViewModel
* For testing
  * Mockk
  * JUnit 5
  * AssertJ

# Coroutines

* Emit one event from the UI
* Remove unused dagger injections...



