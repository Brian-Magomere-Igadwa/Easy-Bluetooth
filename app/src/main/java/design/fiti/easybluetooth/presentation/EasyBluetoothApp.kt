package design.fiti.easybluetooth.presentation


import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import design.fiti.easybluetooth.R
import design.fiti.easybluetooth.presentation.screens.home.HomeScreen
import design.fiti.easybluetooth.presentation.screens.onboarding.Onboarding
import design.fiti.easybluetooth.presentation.screens.result.ResultScreen

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
    val timeInMillis = 300
    Scaffold(
        modifier = modifier
            .fillMaxSize()
    ) { paddingValues ->
        AnimatedNavHost(navController = navController, startDestination = Routes.ONBOARDING.name) {

            composable(route = Routes.ONBOARDING.name,
                popEnterTransition = {
                    slideIntoContainer(

                        AnimatedContentTransitionScope.SlideDirection.Right,

                        animationSpec = tween(
                            durationMillis = timeInMillis,
                            easing = FastOutSlowInEasing
                        )
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(

                        AnimatedContentTransitionScope.SlideDirection.Right,

                        animationSpec = tween(
                            durationMillis = timeInMillis,
                            easing = FastOutSlowInEasing
                        )
                    )
                },
                enterTransition = {
                    slideIntoContainer(

                        AnimatedContentTransitionScope.SlideDirection.Left,

                        animationSpec = tween(
                            durationMillis = timeInMillis,
                            easing = FastOutSlowInEasing
                        )
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(
                            durationMillis = timeInMillis,
                            easing = FastOutSlowInEasing
                        )
                    )
                }) {


                Onboarding(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    navigate = {
                        navController.navigate(Routes.HOME.name)
                    }

                )


            }
            composable(
                route = Routes.HOME.name,
                popEnterTransition = {
                    slideIntoContainer(

                        AnimatedContentTransitionScope.SlideDirection.Right,

                        animationSpec = tween(
                            durationMillis = timeInMillis,
                            easing = FastOutSlowInEasing
                        )
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(

                        AnimatedContentTransitionScope.SlideDirection.Right,

                        animationSpec = tween(
                            durationMillis = timeInMillis,
                            easing = FastOutSlowInEasing
                        )
                    )
                },
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(
                            durationMillis = timeInMillis,
                            easing = FastOutSlowInEasing
                        )
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(
                            durationMillis = timeInMillis,
                            easing = FastOutSlowInEasing
                        )
                    )
                }

            ) {

                HomeScreen(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    navigate = {
                        navController.navigate(Routes.RESULTS.name)

                    }
                )


            }
            composable(route = Routes.RESULTS.name,
                popEnterTransition = {
                    slideIntoContainer(

                        AnimatedContentTransitionScope.SlideDirection.Right,

                        animationSpec = tween(
                            durationMillis = timeInMillis,
                            easing = FastOutSlowInEasing
                        )
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(

                        AnimatedContentTransitionScope.SlideDirection.Right,

                        animationSpec = tween(
                            durationMillis = timeInMillis,
                            easing = FastOutSlowInEasing
                        )
                    )
                },
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(
                            durationMillis = timeInMillis,
                            easing = FastOutSlowInEasing
                        )
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(
                            durationMillis = timeInMillis,
                            easing = FastOutSlowInEasing
                        )
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