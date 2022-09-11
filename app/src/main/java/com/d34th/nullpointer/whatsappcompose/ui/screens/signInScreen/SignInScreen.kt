package com.d34th.nullpointer.whatsappcompose.ui.screens.signInScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.d34th.nullpointer.whatsappcompose.ui.screens.share.CountryCodePickerCompose
import com.d34th.nullpointer.whatsappcompose.ui.screens.share.SimpleToolbar

@Composable
fun SignInScreen() {
    Scaffold(
        topBar = { SimpleToolbar(title = "Verifica tu numero de telefono") }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextDisclaimer(modifier = Modifier.padding(vertical = 20.dp))
            TextNumberInput(Modifier.padding(horizontal = 10.dp, vertical = 20.dp))
        }
    }
}

@Composable
fun TextNumberInput(
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        CountryCodePickerCompose(
            changeCountryCode = {}
        )
        Spacer(modifier = Modifier.size(10.dp))
        OutlinedTextField(value = "", onValueChange = {})
    }
}


@Composable
private fun TextDisclaimer(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Se enviara un SMS al numero proporcionado para verificar su numero de telefono",
            modifier = Modifier.width(250.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.size(30.dp))
        Text(
            text = "Introduce tu  numero de telefono",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption,
            fontSize = 18.sp
        )
    }
}