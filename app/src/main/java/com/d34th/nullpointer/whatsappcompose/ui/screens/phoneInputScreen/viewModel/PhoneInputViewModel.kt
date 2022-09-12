package com.d34th.nullpointer.whatsappcompose.ui.screens.phoneInputScreen.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.d34th.nullpointer.whatsappcompose.R
import com.d34th.nullpointer.whatsappcompose.core.states.PropertySavableString
import com.d34th.nullpointer.whatsappcompose.core.states.SavableComposeState
import com.d34th.nullpointer.whatsappcompose.domain.phone.PhoneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhoneInputViewModel @Inject constructor(
    private val phoneRepository: PhoneRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val MAX_LENGTH_PHONE = 30
        private const val KEY_CODE_COUNTRY = "KEY_CODE_COUNTRY"
        private const val KEY_NAME_COUNTRY = "KEY_NAME_COUNTRY"
    }

    var countryCode by SavableComposeState(savedStateHandle, KEY_CODE_COUNTRY, "")
        private set

    var countryNameCode by SavableComposeState(savedStateHandle, KEY_NAME_COUNTRY, "")
        private set

    var phoneNumberTransforms by mutableStateOf(VisualTransformation.None)
        private set

    var examplePhoneNumber by mutableStateOf("")
        private set

    val numberPhoneProperty = PropertySavableString(
        state = savedStateHandle,
        label = R.string.label_number_phone,
        hint = R.string.hint_number_phone,
        maxLength = MAX_LENGTH_PHONE,
        emptyError = R.string.error_empty_phone_number,
        lengthError = R.string.error_length_phone_number
    )

    fun changeCountryCode(mewCountryCode: String, newCountryNameCode: String) {
        countryCode = mewCountryCode
        countryNameCode = newCountryNameCode
        phoneNumberTransforms = phoneRepository.getPhoneNumberTransformation(countryNameCode)
        examplePhoneNumber = phoneRepository.getExamplePhoneNumber(countryNameCode)
    }

    fun validatePhoneNumber(): String? {
        numberPhoneProperty.reValueString()
        val isNumberValid = phoneRepository.validatePhoneNumber(
            phoneNumber = numberPhoneProperty.value,
            countryNameCode = countryNameCode
        )
        return when {
            numberPhoneProperty.hasError -> null
            !isNumberValid -> {
                numberPhoneProperty.setCustomError(R.string.error_invalid_phone_number)
                null
            }
            else -> countryCode + numberPhoneProperty.value
        }
    }
}