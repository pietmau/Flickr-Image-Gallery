# Flickr Image Gallery
The puropse of this repo is to experiment with MVI and coroutines in a clean architecture. As a consequence it is a bit verbose.

# MVI with Mobius
The app runs on a very basic implementation of the MVI pattern based on
[Spotify's Mobius](https://github.com/spotify/mobius/wiki/Concepts#mobius-loop)
(there are some very similar frameworks,
like [Badoo's](https://github.com/badoo/MVICore), [Spotify's](https://github.com/airbnb/MvRx),
[RxRedux](https://github.com/freeletics/RxRedux), etc.).

The implementation can be found in the [usecase](./usecases/) module.

The view is effectively decoupled from Mobius or any framework:
all the view cares about are `Models` (that receives and renders on screen)
and `Events` (that emits when the user interacts).
__Model__, __Event__, __Effect__ are super simple immutable data classes.

The view is completely stateless.

As a consequence Espresso tests are very simple and effective.

## Remarks
Compared to [Badoo's MVICore](https://github.com/badoo/MVICore) it seems that is a bit less
easy to implement features like navigation and transient messages.
This is because the view listens to events of only one type, while MVICore has a second event type, `News`,
that is used to model one-off occurrences.

## The state
Models, Events, Effects are processed by pure functions.

The state is stored in the `Controller`, retained in a `ViewModel`, the Controller re-emits the latest `Model` when connects.

Long running operations happen in the `UseCase` (specifically in the
`RepositoryUseCase`).

The `Activity` connects and disconnects in `onResume` and `onPause`,
not leaking the connection.

# Architecture
A simple clean architecture ([see dependency graph](docs/architecture.md)) of Gradle
modules connected via Dagger.

# Coroutines
Coroutines and Kotlin instead of RxJava (see `RepositoryUseCase`).

# Libraries
Mobius, Dagger and:
* Simple Xml
* AndroidX
* LeakCanary
* Retrofit
* Architecture Components:
  * Room
  * ViewModel
* For testing
  * Mockk
  * JUnit 5
  * AssertJ
  * Espresso

# Tests
* __android-ui__ (Espresso tests)
* __repository__ (JUnit 5 tests)
* __network__ (no unit tests, only configuration of Retrofit)
* __usecases__ (JUnit 5 tests)
* __entities__ (no tests, only interfaces here...)

