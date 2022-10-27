@file:Suppress("UNUSED_VARIABLE")

package dev.olshevski.navigation.reimagined.sample.anvil.ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.NavHost
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.rememberNavController
import dev.olshevski.navigation.reimagined.sample.anvil.R
import dev.olshevski.navigation.reimagined.sample.anvil.appComponent

internal const val DemoId = 123
internal const val DemoText = "hello"

@Composable
fun MainScreen() = ScreenLayout(
    title = stringResource(R.string.main_screen_title)
) {
    val navController = rememberNavController<MainDestination>(
        startDestination = MainDestination.First
    )

    NavBackHandler(navController)

    NavHost(navController) { destination ->
        when (destination) {
            MainDestination.First -> FirstScreen(
                onOpenSecondScreenButtonClick = {
                    navController.navigate(MainDestination.Second(DemoId))
                }
            )
            is MainDestination.Second -> SecondScreen(
                id = destination.id,
                onOpenThirdScreenButtonClick = {
                    navController.navigate(MainDestination.Third(DemoText))
                }
            )
            is MainDestination.Third -> ThirdScreen(destination.text)
        }
    }
}

@Composable
private fun FirstScreen(
    onOpenSecondScreenButtonClick: () -> Unit
) = ContentLayout(
    title = stringResource(R.string.first_screen_title)
) {
    val appComponent = LocalContext.current.appComponent
    val viewModel = viewModel {
        appComponent.firstViewModel()
    }

    Button(
        onClick = { onOpenSecondScreenButtonClick() }
    ) {
        Text(stringResource(R.string.open_second_screen_button))
    }
}

@Composable
private fun SecondScreen(
    id: Int,
    onOpenThirdScreenButtonClick: () -> Unit
) = ContentLayout(
    title = stringResource(R.string.second_screen_title, id)
) {
    val appComponent = LocalContext.current.appComponent
    val viewModel = viewModel {
        appComponent.secondViewModelFactory().create(id)
    }

    Button(
        onClick = { onOpenThirdScreenButtonClick() }
    ) {
        Text(stringResource(R.string.open_third_screen_button))
    }

}

@Composable
private fun ThirdScreen(
    text: String
) = ContentLayout(
    title = stringResource(R.string.third_screen_title, text)
) {
    val appComponent = LocalContext.current.appComponent
    val viewModel = viewModel {
        appComponent.thirdViewModelFactory().create(text, createSavedStateHandle())
    }
}