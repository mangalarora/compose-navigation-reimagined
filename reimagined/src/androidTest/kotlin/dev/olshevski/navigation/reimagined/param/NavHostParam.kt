package dev.olshevski.navigation.reimagined.param

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import dev.olshevski.navigation.reimagined.AnimatedNavHost
import dev.olshevski.navigation.reimagined.NavHost
import dev.olshevski.navigation.reimagined.NavHostScope
import dev.olshevski.navigation.reimagined.NavHostState

enum class NavHostParam {
    NavHost,
    AnimatedNavHost
}

@Suppress("TestFunctionName")
@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun <T, S> ParamNavHost(
    param: NavHostParam,
    state: NavHostState<T, S>,
    content: @Composable NavHostScope<T>.(T) -> Unit
) {
    when (param) {
        NavHostParam.NavHost -> NavHost(state) { content(it) }
        NavHostParam.AnimatedNavHost -> AnimatedNavHost(state) { content(it) }
    }
}