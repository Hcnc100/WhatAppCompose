package com.d34th.nullpointer.whatsappcompose.ui.screens.phoneInputScreen.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.d34th.nullpointer.whatsappcompose.R
import com.d34th.nullpointer.whatsappcompose.core.states.PropertySavableString
import com.d34th.nullpointer.whatsappcompose.core.states.SavableComposeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhoneInputViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val MAX_LENGTH_PHONE = 30
        private const val KEY_CODE_COUNTRY = "KEY_CODE_COUNTRY"
    }

    var countryCode by SavableComposeState(savedStateHandle, KEY_CODE_COUNTRY, "")
        private set

    val numberPhoneProperty = PropertySavableString(
        state = savedStateHandle,
        label = R.string.label_number_phone,
        hint = R.string.hint_number_phone,
        maxLength = MAX_LENGTH_PHONE,
        emptyError = R.string.error_empty_phone_number,
        lengthError = R.string.error_length_phone_number
    )

    fun changeCountryCode(mewCountryCode: String) {
        countryCode = mewCountryCode
    }

    fun validatePhoneNumber(): String? {
        numberPhoneProperty.reValueString()
        return when {
            numberPhoneProperty.hasError -> null
            else -> countryCode + numberPhoneProperty.value
        }
    }
}