package design.fiti.easybluetooth.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EasyBluetoothApp(navController: NavHostController) {
    Scaffold() { paddingValues ->
        NavHost(navController = navController, startDestination = Routes.ONBOARDING.name) {
            composable(route = Routes.ONBOARDING.name) {
                Onboarding(modifier = Modifier.padding(paddingValues))
            }
            composable(route = Routes.HOME.name) {
                HomeScreen(modifier = Modifier.padding(paddingValues))
            }
            composable(route = Routes.RESULTS.name) {
                ResultScreen(modifier = Modifier.padding(paddingValues))
            }
        }
    }
}