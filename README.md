# This application is over engineered
Becuase its purpose is only demostrative.

# MVI
It contains a very basic implementation of the MVI pattern based on
[Spotify's Mobius](https://github.com/spotify/mobius)

# Architecture
A simple clean architecture ([graph](docs/architecture.md)) of Gradle
modules connected via Dagger.

The view is completely decoupled from Mobius, all it cares about
is [Models](./usecases/src/main/java/com/pppp/Model.kt) that it receives (and renders on screen)

# Coroutines

# Libraries
Mobius, Dagger and:
* Android X
* LeakCanary



For testing
* Mockk
* Junit 5
* JAssert







