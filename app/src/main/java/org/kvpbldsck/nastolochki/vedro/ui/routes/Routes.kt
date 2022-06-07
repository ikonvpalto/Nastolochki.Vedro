package org.kvpbldsck.nastolochki.vedro.ui.routes

sealed class Routes(val route: String) {
    object Events: Routes("events")
    object Groups: Routes("groups")
    object NewEvent: Routes("new-event")
    object Chats: Routes("chats")
    object Profile: Routes("profile")
}

