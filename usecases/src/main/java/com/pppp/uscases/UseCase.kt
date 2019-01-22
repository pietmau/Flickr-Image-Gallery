package com.pppp.uscases

interface UseCase<F, E> {
    fun execute(effect: F, handler: (E) -> Unit)
}
