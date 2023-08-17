package design.fiti.easybluetooth.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import design.fiti.easybluetooth.R
import design.fiti.easybluetooth.ui.screens.home.HomeScreen
import design.fiti.easybluetooth.ui.screens.onboarding.Onboarding
import design.fiti.easybluetooth.ui.screens.result.ResultScreen

enum class Routes {
    ONBOARDING, HOME, RESULTS
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(title = {
        Text(text = stringResource(id = R.string.app_name))
    })
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun EasyBluetoothApp(navController: NavHostController, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
    ) { paddingValues ->
        AnimatedNavHost(navController = navController, startDestination = Routes.ONBOARDING.name) {
            composable(route = Routes.ONBOARDING.name,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }) {

                Onboarding(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    navigate = {
                        navController.navigate(Routes.HOME.name)
                        navController.popBackStack()
                    }

                )


            }
            composable(
                route = Routes.HOME.name,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }

            ) {

                HomeScreen(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    navigate = { navController.navigate(Routes.RESULTS.name) }
                )


            }
            composable(route = Routes.RESULTS.name,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }
            ) {

                ResultScreen(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    navigate = { navController.navigateUp() }
                )


            }
        }
    }
}