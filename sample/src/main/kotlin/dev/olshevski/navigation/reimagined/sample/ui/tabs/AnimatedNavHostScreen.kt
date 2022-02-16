package dev.olshevski.navigation.reimagined.sample.ui.tabs

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.with
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import dev.olshevski.navigation.reimagined.AnimatedNavHost
import dev.olshevski.navigation.reimagined.AnimatedNavHostTransitionSpec
import dev.olshevski.navigation.reimagined.NavAction
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.rememberNavController
import dev.olshevski.navigation.reimagined.sample.singleLine
import dev.olshevski.navigation.reimagined.sample.ui.CenteredText
import dev.olshevski.navigation.reimagined.sample.ui.SubScreenLayout

private val AnimatedNavHostTransitionSpec = AnimatedNavHostTransitionSpec<Int> { action, from, to ->
    val directionsCount = 4
    val direction = when (action) {
        NavAction.Pop -> when (from.mod(directionsCount)) {
            0 -> AnimatedContentScope.SlideDirection.End
            1 -> AnimatedContentScope.SlideDirection.Down
            2 -> AnimatedContentScope.SlideDirection.Start
            else -> AnimatedContentScope.SlideDirection.Up
        }
        else -> when (to.mod(directionsCount)) {
            // opposite directions
            0 -> AnimatedContentScope.SlideDirection.Start
            1 -> AnimatedContentScope.SlideDirection.Up
            2 -> AnimatedContentScope.SlideDirection.End
            else -> AnimatedContentScope.SlideDirection.Down
        }
    }
    slideIntoContainer(direction) with slideOutOfContainer(direction)
}

@Composable
fun AnimatedNavHostScreen() {
    val navController = rememberNavController(
        startDestination = 0,
    )

    NavBackHandler(navController)

    AnimatedNavHost(
        controller = navController,
        transitionSpec = AnimatedNavHostTransitionSpec
    ) { destination ->
        SubScreenLayout(title = "Screen #$destination") {

            CenteredText(
                text = """AnimatedNavHost switches between destinations with animations. 
                    You can select specific animation for every transition.""".singleLine(),
            )

            Button(onClick = { navController.navigate(destination + 1) }) {
                Text("To Next screen")
            }
        }
    }
}