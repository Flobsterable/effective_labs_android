package ru.flobsterable.effectiveLabs.presentation.utils

interface EventHandler<E> {
    fun obtainEvent(event: E)
}
