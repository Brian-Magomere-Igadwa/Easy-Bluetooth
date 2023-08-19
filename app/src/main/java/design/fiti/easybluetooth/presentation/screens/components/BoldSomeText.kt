package design.fiti.easybluetooth.presentation.screens.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun BoldSomeText(fullSentence: String, boldWords: String, maxLines: Int = 1, textStyle: TextStyle) {
    val sentence = fullSentence
    val targetWord = boldWords


    val index = sentence.indexOf(targetWord)
    if (index != -1) {
        val annotatedString = buildAnnotatedString {
            append(sentence.substring(0, index))
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(targetWord)
            }
            append(sentence.substring(index + targetWord.length))
        }

        Text(text = annotatedString, maxLines = maxLines, style = textStyle)
    } else {
        // Fallback if the target word is not found
        Text(text = sentence, maxLines = maxLines, style = textStyle)
    }
}