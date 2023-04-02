package com.example.madcourse.ui.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit

// Provide custom ripple effect colour using Composition LocalProvider
object RippleCustomTheme : RippleTheme {

    @Composable
    override fun defaultColor() =
        RippleTheme.defaultRippleColor(
            Color.Black,
            lightTheme = true
        )

    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleTheme.defaultRippleAlpha(
            Color.Black,
            lightTheme = true
        )
}

/**
 *
 * @param modifier: this modifier apply to whole text
 * @param fullText: full text that we are presenting to screen. (including link)
 * @param linkMap<KEY,VALUE>:
 *          KEY: provide text to match part of the fullText (case sensitive)
 *          VALUE: provide link to apply to that key
 * @param linkTextColor: provide color for linked text
 * @param linkFontStyle: provide style for the linked text
 * @param fontSize: font size for the linkText
 *
 */
@Composable
fun HyperlinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    linkMap: Map<String, String> = mapOf(),
    linkTextColor: Color = MaterialTheme.colorScheme.onSurface,
    linkFontStyle: FontStyle? = MaterialTheme.typography.bodyLarge.fontStyle,
    linkTextDecoration: TextDecoration = TextDecoration.None,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)
        linkMap.entries.forEach { entry ->
            val startIndex = fullText.indexOf(entry.key)
            val endIndex = startIndex + entry.key.length
            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize = fontSize,
                    fontStyle = linkFontStyle,
                    textDecoration = linkTextDecoration
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = entry.value,
                start = startIndex,
                end = endIndex
            )
        }

        addStyle(
            style = SpanStyle(
                fontSize = fontSize
            ),
            start = 0,
            end = fullText.length
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}