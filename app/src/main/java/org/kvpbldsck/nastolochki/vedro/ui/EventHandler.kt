package org.kvpbldsck.nastolochki.vedro.ui

interface EventHandler<TEvent> {
    fun handleEvent(event: TEvent)
}