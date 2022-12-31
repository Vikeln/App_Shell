package tech.nouveta.demo.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp


@Composable
fun CentredImage(image: Painter, description: String, height: Dp, width: Dp) {
    Image(
        painter = image,
        contentDescription = description,
        modifier = Modifier
            .width(width)
            .height(height)
    )
}