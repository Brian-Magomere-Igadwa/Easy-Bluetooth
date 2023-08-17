package design.fiti.easybluetooth.ui.screens.components

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import design.fiti.easybluetooth.R
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.Text

@Preview
@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    colors: ButtonColors,
    onClick: () -> Unit = {},
    @StringRes title: Int = R.string.cta
) {

    Button(
        onClick = onClick,
        modifier = modifier,
        colors = colors
    ) {

        Text(
            text = stringResource(id = title),
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(
                8.dp
            )
        )


    }


}