package org.kvpbldsck.nastolochki.vedro.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.kvpbldsck.nastolochki.vedro.R
import org.kvpbldsck.nastolochki.vedro.ui.routes.MainScreens
import org.kvpbldsck.nastolochki.vedro.ui.routes.Routes
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.EventsScreen
import org.kvpbldsck.nastolochki.vedro.ui.screens.events.EventsViewModel
import org.kvpbldsck.nastolochki.vedro.ui.theme.NastolochkiVedroTheme
import org.kvpbldsck.nastolochki.vedro.ui.theme.navigationButtonTextStyle

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val eventsViewModel = EventsViewModel()
    
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Routes.Events.route,
                Modifier.padding(innerPadding)
            ) {
                composable(Routes.Events.route) {
                    EventsScreen(eventsViewModel)
                }
                composable(Routes.Groups.route) {
                    Text(text = stringResource(id = R.string.groups))
                }
                composable(Routes.NewEvent.route) {
                    Text(text = stringResource(id = R.string.new_event))
                }
                composable(Routes.Chats.route) {
                    Text(text = stringResource(id = R.string.chats))
                }
                composable(Routes.Profile.route) {
                    Text(text = stringResource(id = R.string.profile))
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination

        MainScreens.getImplemented()
            .forEach { screen ->
                BottomNavigationItem(
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route.route } == true,
                    icon = {
                        Icon(
                            modifier = if (screen.isMajor) Modifier.size(38.dp) else Modifier.Companion,
                            painter = painterResource(screen.drawableResId),
                            contentDescription = stringResource(screen.labelResId))
                    },
                    label =
                        if(!screen.isMajor) {
                            { Text(text = stringResource(screen.labelResId), style = navigationButtonTextStyle, maxLines = 1) }
                        } else
                            null,
                    unselectedContentColor = MaterialTheme.colors.contentColorFor(MaterialTheme.colors.background),
                    selectedContentColor = MaterialTheme.colors.primary,
                    onClick = {
                        navController.navigate(screen.route.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreen_Preview() {
    NastolochkiVedroTheme {
        MainScreen()
    }
}