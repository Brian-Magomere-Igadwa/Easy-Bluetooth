package design.fiti.easybluetooth.presentation.screens.onboarding


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import design.fiti.easybluetooth.R
import design.fiti.easybluetooth.presentation.screens.components.AppButton

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Onboarding(modifier: Modifier = Modifier, navigate: () -> Unit = {}) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .weight(0.2f)

        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = stringResource(id = R.string.app_title),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )

        }
        Image(painter = painterResource(R.drawable.onboardingperson), contentDescription = null)
        Spacer(
            modifier = Modifier
                .weight(0.3f)

        )
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
                .background(color = Color.Transparent)
        ) {
            AppButton(
                title = R.string.cta,
                modifier = Modifier
                    .fillMaxWidth(R.dimen.button_width.toFloat()),

                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                onClick = navigate,
            )
        }
        Spacer(
            modifier = Modifier
                .weight(0.7f)

        )
    }

}

