package com.example.easychef.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.easychef.R

/**
 *  Reusable logo composable
 *  @param modifier Optional arguments to mutate logo composable
 */

@Composable
fun AppLogo(modifier: Modifier) {
    Image(
        // TODO Replace placeholder with the actual logo once it is finished
        painter = painterResource(id = R.drawable.placeholder_image),
        contentDescription = "EasyChef App Logo",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
    )

}