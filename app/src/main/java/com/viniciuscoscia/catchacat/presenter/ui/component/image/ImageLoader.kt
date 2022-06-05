package com.viniciuscoscia.catchacat.presenter.ui.component.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.viniciuscoscia.catchacat.R

@Composable
fun ImageLoader(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    progressBarSize: Int = 30,
    contentScale: ContentScale = ContentScale.Fit
) {
    SubcomposeAsyncImage(
        model = imageUrl,
        contentScale = contentScale,
        modifier = modifier,
        contentDescription = stringResource(R.string.cat_image_description)
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(progressBarSize.dp))
                }
            }
            is AsyncImagePainter.State.Error,
            is AsyncImagePainter.State.Empty -> {
                Image(
                    painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }
            else -> {
                SubcomposeAsyncImageContent()
            }
        }
    }
}