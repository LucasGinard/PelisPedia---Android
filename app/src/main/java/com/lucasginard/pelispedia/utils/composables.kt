package com.lucasginard.pelispedia.utils

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.lucasginard.pelispedia.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

const val DEFAULT_MINIMUM_TEXT_LINE = 3

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    fontStyle: FontStyle? = null,
    text: String,
    collapsedMaxLine: Int = DEFAULT_MINIMUM_TEXT_LINE,
    showMoreText: String = "...Leer más",
    showMoreStyle: SpanStyle = SpanStyle(fontWeight = FontWeight.W500),
    showLessText: String = " ..Leer menos",
    showLessStyle: SpanStyle = showMoreStyle,
    textAlign: TextAlign? = null
) {
    var isExpanded by remember { mutableStateOf(false) }
    var clickable by remember { mutableStateOf(false) }
    var lastCharIndex by remember { mutableStateOf(0) }
    Box(modifier = Modifier
        .clickable(clickable) {
            isExpanded = !isExpanded
        }
        .then(modifier)
    ) {
        Text(
            modifier = textModifier
                .fillMaxWidth()
                .animateContentSize(),
            text = buildAnnotatedString {
                if (clickable) {
                    if (isExpanded) {
                        append(text)
                        withStyle(style = showLessStyle) { append(showLessText) }
                    } else {
                        val adjustText = text.substring(startIndex = 0, endIndex = lastCharIndex)
                            .dropLast(showMoreText.length)
                            .dropLastWhile { Character.isWhitespace(it) || it == '.' }
                        append(adjustText)
                        withStyle(style = showMoreStyle) { append(showMoreText) }
                    }
                } else {
                    append(text)
                }
            },
            maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLine,
            fontStyle = fontStyle,
            onTextLayout = { textLayoutResult ->
                if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                    clickable = true
                    lastCharIndex = textLayoutResult.getLineEnd(collapsedMaxLine - 1)
                }
            },
            style = style,
            textAlign = textAlign
        )
    }

}

@Composable
fun errorDialog(
    dialogState: Boolean = false,
    onClickAccept:() -> Unit,
    onClickCache:() -> Unit
) {
    if (dialogState) {
        val openDialog = remember { mutableStateOf(true) }
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {

                },
                title = {
                    Text(text = stringResource(id = R.string.errorTitleConnect), modifier = Modifier.padding(10.dp))
                },
                text ={
                    Text(text = stringResource(id = R.string.errorDescriptionConnect), modifier = Modifier.padding(10.dp))
                },
                buttons = {
                    Column(
                        modifier = Modifier.padding(all = 20.dp),
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                openDialog.value = false
                                onClickAccept()
                            }
                        ) {
                            Text("Volver al inicio")
                        }
                        if (SessionCache.listSeriesCache.isNotEmpty()){
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    openDialog.value = false
                                    onClickCache()
                                }
                            ) {
                                Text("Última lista guardada")
                            }
                        }
                    }
                }
            )
        }
    }
}