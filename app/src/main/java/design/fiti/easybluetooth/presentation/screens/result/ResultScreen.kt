package design.fiti.easybluetooth.presentation.screens.result

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import design.fiti.easybluetooth.R
import design.fiti.easybluetooth.domain.BtDevice
import design.fiti.easybluetooth.presentation.screens.EasyBtViewModel
import design.fiti.easybluetooth.presentation.screens.ResultsUiState


@Composable
fun ResultScreen(modifier: Modifier = Modifier, navigate: () -> Unit = {}) {
    val viewModel: EasyBtViewModel = viewModel(factory = EasyBtViewModel.Factory)
    val results by viewModel.uiState.collectAsState()

    Log.d(TAG, "ResultScreen: $results")

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(48.dp))
        UpBar(navigate = navigate, refresh = {})
        Spacer(modifier = Modifier.height(80.dp))
        Results(results = results)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Results(modifier: Modifier = Modifier, results: ResultsUiState = ResultsUiState()) {
    val pairedDevices = results.pairedDevices
    val scannedDevices = results.scannedDevices

    Box(modifier.fillMaxHeight(), contentAlignment = Alignment.BottomCenter) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text(text = stringResource(id = R.string.paired_devices))
            }
            items(pairedDevices) {
                Box(modifier = Modifier.padding(horizontal = 36.dp)) {
                    ResultItem(modifier = Modifier.fillMaxWidth(), device = it, fromNewScan = false)
                }
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                Text(text = stringResource(id = R.string.scanned_devices))
            }
            items(scannedDevices) {
                Box(modifier = Modifier.padding(horizontal = 36.dp)) {
                    ResultItem(modifier = Modifier.fillMaxWidth(), device = it, fromNewScan = true)
                }
            }
            item {
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
        val shades = listOf(
            MaterialTheme.colorScheme.background.copy(alpha = 0f),
            MaterialTheme.colorScheme.background.copy(alpha = 0.25f),
            MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
            MaterialTheme.colorScheme.background.copy(alpha = 0.8f)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .background(
                    brush = Brush.verticalGradient(
                        shades,
                        startY = 0.0f,
                        endY = 100.0f
                    )
                )
        )

    }
}

@Preview
@Composable
fun ResultItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    device: BtDevice,
    fromNewScan: Boolean = true
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        modifier = modifier.shadow(
            elevation = 4.dp,
            spotColor = Color(0x66000000),
            shape = RoundedCornerShape(20.dp),
            ambientColor = Color(0x66000000)
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(60.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxSize()
                .padding(
                    horizontal = 24.dp,
                    vertical = 32.dp
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(60.dp, Alignment.Start),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.bluetoothicon),
                    contentDescription = null
                )
                Column {
                    Text(
                        text = device.deviceName,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.32.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF586D5E),
                        ),
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = if (fromNewScan) "Found now!" else "Used it in the past!",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.32.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF586D5E),
                        ),
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

        }
    }

}

@Preview
@Composable
fun UpBar(
    modifier: Modifier = Modifier,
    navigate: () -> Unit = {},
    refresh: () -> Unit = {}
) {
    Row(
        modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = navigate) {
            Icon(
                painter = painterResource(id = R.drawable.upicon),
                contentDescription = stringResource(R.string.back_navigation)
            )
        }

        Text(text = stringResource(id = R.string.result_screen), textAlign = TextAlign.Center)
        IconButton(onClick = refresh) {
            Icon(
                painter = painterResource(id = R.drawable.refreshicon),
                contentDescription = stringResource(R.string.back_navigation)
            )
        }


    }
}