package com.d34th.nullpointer.whatsappcompose.ui.screens.initScreen

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.d34th.nullpointer.whatsappcompose.R

@Composable
fun InitScreen() {
    Scaffold(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LogoApp()
            TextAcceptAndLogin(modifier = Modifier.padding(20.dp))
        }
    }
}

@Composable
private fun TextAcceptAndLogin(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        TextDisclaimer()
        Spacer(modifier = Modifier.height(40.dp))
        ButtonSignIn {

        }
    }
}

@Composable
fun ButtonSignIn(
    modifier: Modifier = Modifier,
    actionSignIn: () -> Unit
) {
    ExtendedFloatingActionButton(
        modifier = modifier,
        text = { Text(text = "Inicia session") },
        onClick = actionSignIn
    )
}

@Composable
private fun TextDisclaimer(
    modifier: Modifier = Modifier
) {
    Text(
        text = "Al hacer click en inicar session aceptas los terminos y condiciiones",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.caption,
        modifier = modifier.width(130.dp),
    )
}

@Composable
private fun LogoApp(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp), contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            ImageRequest.Builder(context)
                .data(R.drawable.ic_launcher_background)
                .transformations(CircleCropTransformation())
                .build(),
            contentDescription = "Logo de la application",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(150.dp)
        )
    }
}