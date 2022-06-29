package com.viniciuscoscia.catchacat.presenter.ui.component.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.viniciuscoscia.catchacat.R

@Composable
fun CatLoader(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader,
    progressBarSize: Int = 30,
    contentScale: ContentScale,
) {
    SubcomposeAsyncImage(
        model = imageUrl,
        imageLoader = imageLoader,
        modifier = modifier
            .fillMaxWidth()
            .height(600.dp),
        contentScale = contentScale,
        contentDescription = stringResource(R.string.cat_image_description),
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                Box(
                    modifier = Modifier.size(150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(progressBarSize.dp))
                }
            }
            is AsyncImagePainter.State.Error,
            is AsyncImagePainter.State.Empty -> {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
            else -> {
                SubcomposeAsyncImageContent(
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}