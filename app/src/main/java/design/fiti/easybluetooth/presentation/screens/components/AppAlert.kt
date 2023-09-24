package design.fiti.easybluetooth.presentation.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AppAlert(
    permission: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClicked: () -> Unit,
    onGoToAppSettingsClicked: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Divider()
                Text(
                    text = if (isPermanentlyDeclined) {
                        "Grant Permission"
                    } else {
                        "OK"
                    },
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable {
                            if (isPermanentlyDeclined) {
                                onGoToAppSettingsClicked()
                                onOkClicked()
                            } else {
                                onOkClicked()
                            }
                        }
                        .padding(16.dp)
                )
            }
        },
        title = { Text(text = "Permission Required") },
        text = { Text(text = permission.getDescription(isPermanentlyDeclined = isPermanentlyDeclined)) }
    )

}

interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): String
}

class CameraPermissionDescription : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "Seems you permanently denied camera permissions," + " enda kwa settings!"
        } else {
            "The app needs access to your camera ndio ushare photos, acha ujinga!"
        }
    }
}

class RecordAudioPermissionDescription : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "Seems you permanently denied microphone permissions," + " enda kwa settings!"
        } else {
            "The app needs access to your microphone ndio uongee, acha ujinga!"
        }
    }
}

class PhoneCallPermissionDescription : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "Seems you permanently denied call permissions," + " enda kwa settings!"
        } else {
            "The app needs access to your phone ndio upigiane simu , acha ujinga!"
        }
    }
}