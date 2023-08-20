package design.fiti.easybluetooth.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import design.fiti.easybluetooth.R
import design.fiti.easybluetooth.presentation.screens.EasyBtViewModel
import design.fiti.easybluetooth.presentation.screens.components.AppButton
import design.fiti.easybluetooth.presentation.screens.components.BoldSomeText

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    deviceConnected: Boolean = true,
    navigate: () -> Unit = {}
) {
    val viewModel: EasyBtViewModel = viewModel(factory = EasyBtViewModel.Factory)
    var itemSelected = rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .weight(0.2f)

        )
        BoldSomeText(
            fullSentence = "Welcome to EasyBluetooth!",
            boldWords = "EasyBluetooth!",
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = Color(0xFF586D5E),
                textAlign = TextAlign.Center,
            )
        )
        Spacer(
            modifier = Modifier
                .weight(0.15f)

        )
        Image(painter = painterResource(R.drawable.speakersguy), contentDescription = null)
        Spacer(
            modifier = Modifier
                .weight(0.15f)

        )
        AnimatedVisibility(visible = itemSelected.value) {
//            if (deviceConnected) {
            if (itemSelected.value) {
                Connected(navigate = navigate, disconnect = {
                    itemSelected.value = false

                }, rescan = {
                    navigate()
                    viewModel.scanForDevices()
                })
            }

        }
        AnimatedVisibility(visible = !itemSelected.value) {
            if (!itemSelected.value) {
                NotConnected(onClick = {
                    navigate()
                    viewModel.scanForDevices()
                })
            }
        }
        Spacer(
            modifier = Modifier
                .weight(0.7f)
        )

    }


}


@Preview
@Composable
fun NotConnected(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(modifier = modifier) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 54.dp)

        ) {
            BoldSomeText(
                fullSentence = "Click to search for nearby bluetooth devices.",
                boldWords = "to search for nearby bluetooth devices.",
                maxLines = 2,
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = Color(0xFF586D5E),
                    textAlign = TextAlign.Center,

                    )
            )
        }
        Spacer(
            modifier = Modifier
                .height(32.dp)

        )
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
        ) {
            AppButton(
                title = R.string.search_cta,
                modifier = Modifier
                    .fillMaxWidth(R.dimen.button_width.toFloat()),
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
            )
        }
    }
}

@Preview
@Composable
fun Connected(
    modifier: Modifier = Modifier,
    navigate: () -> Unit,
    disconnect: () -> Unit,
    rescan: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 54.dp)
        ) {
            BoldSomeText(
                fullSentence = "You’re connected to Sonny Home Theatre",
                boldWords = "Sonny Home Theatre",
                maxLines = 2,
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = Color(0xFF586D5E),
                    textAlign = TextAlign.Center,

                    )
            )
        }
        Spacer(
            modifier = Modifier
                .height(32.dp)

        )
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
        ) {
            Row {
                Button(onClick = disconnect, shape = RoundedCornerShape(size = 14.dp)) {
                    Text(text = stringResource(R.string.disconnect_cta))
                }
                Spacer(modifier = Modifier.width(24.dp))
                OutlinedButton(onClick = rescan, shape = RoundedCornerShape(size = 14.dp)) {
                    Text(text = stringResource(R.string.scan_cta))
                }

            }
        }
    }
}
