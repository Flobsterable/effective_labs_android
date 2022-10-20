package ru.flobsterable.effective_labs.utils

interface EventHandler<E> {
    fun obtainEvent(event: E)
}
