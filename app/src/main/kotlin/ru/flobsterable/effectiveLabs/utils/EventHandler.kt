package ru.flobsterable.effectiveLabs.utils

interface EventHandler<E> {
    fun obtainEvent(event: E)
}
