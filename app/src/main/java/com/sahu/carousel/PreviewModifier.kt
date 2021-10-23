package com.sahu.carousel

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class PreviewModifier : PreviewParameterProvider<Modifier> {
    override val values: Sequence<Modifier>
        get() = listOf<Modifier>(Modifier).asSequence()

}