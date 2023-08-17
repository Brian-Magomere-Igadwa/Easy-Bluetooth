package design.fiti.easybluetooth.ui.screens.result

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import design.fiti.easybluetooth.R

val data = listOf(
    "something",
    "something",
    "something",
    "something",
    "something",
    "something",
    "something",
    "something",
    "something",
    "something",
    "something",
    "something",
    "something",
    "something",
    "something",
    "something"
)

@Composable
fun ResultScreen(modifier: Modifier = Modifier, navigate: () -> Unit = {}) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(48.dp))
        UpBar(navigate = navigate, refresh = {})
        Spacer(modifier = Modifier.height(80.dp))
        Results()
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Results(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxHeight(), contentAlignment = Alignment.BottomCenter) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(data) {
                Box(modifier = Modifier.padding(horizontal = 42.dp)) {
                    ResultItem(modifier = Modifier.fillMaxWidth())
                }
            }
        }
        val shades = listOf(
            Color.White.copy(alpha = 0f),
            Color.White.copy(alpha = 0.25f),
            Color.White.copy(alpha = 0.5f),
            Color.White
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
fun ResultItem(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(60.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { }
            .clip(shape = RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.tertiary)
            .shadow(
                elevation = 2.2368102073669434.dp,
                spotColor = Color(0x66000000),
                ambientColor = Color(0x66000000)
            )
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
                    text = "Sony Home Theater",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.32.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF586D5E),
                    ),
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Found now!",
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
        horizontalArrangement = Arrangement.SpaceBetween
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