package com.d34th.nullpointer.whatsappcompose.domain.phone

import com.d34th.nullpointer.whatsappcompose.core.utils.PhoneNumberVisualTransformation

interface PhoneRepository {
    fun validatePhoneNumber(phoneNumber: String, countryNameCode: String): Boolean
    fun getPhoneNumberTransformation(countryNameCode: String): PhoneNumberVisualTransformation
    fun getExamplePhoneNumber(countryNameCode: String): String
}