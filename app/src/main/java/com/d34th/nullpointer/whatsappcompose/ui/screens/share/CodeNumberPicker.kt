package com.d34th.nullpointer.whatsappcompose.ui.screens.share


import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.d34th.nullpointer.whatsappcompose.R
import com.hbb20.CountryCodePicker

@SuppressLint("InflateParams")
@Composable
fun CountryCodePickerCompose(
    modifier: Modifier = Modifier,
    colorText: Color = MaterialTheme.colors.onBackground,
    colorBackground: Color = MaterialTheme.colors.background,
    changeCountryCode: (newCode: String, nameCountry: String) -> Unit,
) {
    AndroidView(
        modifier = modifier,
        factory = {
            LayoutInflater.from(it).inflate(
                R.layout.layout_phone_number,
                null,
                false
            ).apply {
                initCountryPicker(
                    colorText = colorText,
                    colorBackground = colorBackground,
                    changeCountryCode = changeCountryCode,
                    countryCodePicker = findViewById(R.id.codeCountry)
                )
            }
        },
    )
}


private fun initCountryPicker(
    colorText: Color,
    colorBackground: Color,
    countryCodePicker: CountryCodePicker,
    changeCountryCode: (newCode: String, nameCountry: String) -> Unit,
) = with(countryCodePicker) {
    // * set code number for defect
    changeCountryCode(selectedCountryCodeWithPlus, selectedCountryNameCode)

    contentColor = colorText.toArgb()
    setDialogTextColor(colorText.toArgb())
    setDialogBackgroundColor(colorBackground.toArgb())

    // * set listener for change country code
    setOnCountryChangeListener {
        changeCountryCode(selectedCountryCodeWithPlus, selectedCountryNameCode)
    }
}