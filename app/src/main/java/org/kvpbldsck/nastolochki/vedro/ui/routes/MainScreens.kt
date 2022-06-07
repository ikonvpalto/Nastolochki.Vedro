package org.kvpbldsck.nastolochki.vedro.ui.routes

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.kvpbldsck.nastolochki.vedro.R

sealed class MainScreens(val route: Routes, @DrawableRes val drawableResId: Int, @StringRes val labelResId: Int, val isMajor: Boolean = false) {
    object Events:   MainScreens(Routes.Events,   R.drawable.icon_dice,    R.string.events,    isMajor = false)
    object Groups:   MainScreens(Routes.Groups,   R.drawable.icon_group,   R.string.groups,    isMajor = false)
    object NewEvent: MainScreens(Routes.NewEvent, R.drawable.icon_add,     R.string.new_event, isMajor = true)
    object Chats:    MainScreens(Routes.Chats,    R.drawable.icon_chat,    R.string.chats,     isMajor = false)
    object Profile:  MainScreens(Routes.Profile,  R.drawable.icon_profile, R.string.profile,   isMajor = false)

    companion object {
        fun getImplemented() = listOf(
            Events,
            Groups,
            NewEvent,
            Chats,
            Profile
        )
    }
}