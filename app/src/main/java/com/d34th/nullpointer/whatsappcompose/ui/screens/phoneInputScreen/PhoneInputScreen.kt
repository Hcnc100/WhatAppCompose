package com.d34th.nullpointer.whatsappcompose.ui.screens.phoneInputScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.d34th.nullpointer.whatsappcompose.R
import com.d34th.nullpointer.whatsappcompose.core.states.PropertySavableString
import com.d34th.nullpointer.whatsappcompose.core.utils.mobileNumberFilter
import com.d34th.nullpointer.whatsappcompose.ui.screens.phoneInputScreen.viewModel.PhoneInputViewModel
import com.d34th.nullpointer.whatsappcompose.ui.screens.share.CountryCodePickerCompose
import com.d34th.nullpointer.whatsappcompose.ui.screens.share.EditableTextSavable
import com.d34th.nullpointer.whatsappcompose.ui.screens.share.SimpleToolbar
import timber.log.Timber

@Composable
fun PhoneInputScreen(
    phoneInputViewModel: PhoneInputViewModel = hiltViewModel()
) {
    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        topBar = { SimpleToolbar(title = stringResource(R.string.title_sign_in_phone)) },
        floatingActionButton = {
            ButtonNextSignIn(onClickNext = {
                phoneInputViewModel.validatePhoneNumber()?.let {
                    Timber.d("Full number: ${it}")
                }
            })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextDisclaimer(modifier = Modifier.padding(vertical = 20.dp))
            TextNumberInput(
                changeCountryCode = phoneInputViewModel::changeCountryCode,
                phoneNumberProperty = phoneInputViewModel.numberPhoneProperty,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
            )
        }
    }
}

@Composable
private fun ButtonNextSignIn(
    onClickNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClickNext,
        modifier = modifier,
    ) {
        Text(text = stringResource(R.string.text_button_next))
    }
}

@Composable
private fun TextNumberInput(
    modifier: Modifier = Modifier,
    phoneNumberProperty: PropertySavableString,
    changeCountryCode: (newCodeCountry: String) -> Unit
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        CountryCodePickerCompose(changeCountryCode = changeCountryCode)
        Spacer(modifier = Modifier.size(10.dp))
        EditableTextSavable(
            singleLine = true,
            shape = RoundedCornerShape(15.dp),
            valueProperty = phoneNumberProperty,
            visualTransformation = { mobileNumberFilter(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
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
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(250.dp),
            style = MaterialTheme.typography.caption,
            text = stringResource(R.string.text_disclaimer_send_sms)
        )
        Spacer(modifier = Modifier.size(30.dp))
        Text(
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption,
            text = stringResource(R.string.text_input_phone_number)
        )
    }
}
