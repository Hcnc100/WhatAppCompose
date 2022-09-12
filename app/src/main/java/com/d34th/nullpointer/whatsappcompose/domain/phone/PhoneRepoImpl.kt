package com.d34th.nullpointer.whatsappcompose.domain.phone

import android.content.Context
import com.d34th.nullpointer.whatsappcompose.core.utils.PhoneNumberVisualTransformation
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import io.michaelrocks.libphonenumber.android.Phonenumber
import timber.log.Timber

class PhoneRepoImpl(context: Context) : PhoneRepository {

    private val phoneNumberUtils = PhoneNumberUtil.createInstance(context)

    override fun validatePhoneNumber(phoneNumber: String, countryNameCode: String): Boolean {
        return try {
            val swissNumberProto: Phonenumber.PhoneNumber =
                phoneNumberUtils.parse(phoneNumber, countryNameCode)
            phoneNumberUtils.isValidNumber(swissNumberProto);
        } catch (e: Exception) {
            Timber.d("Exception validate number: $phoneNumber")
            false
        }
    }

    override fun getExamplePhoneNumber(countryNameCode: String): String {
        val phoneExample = phoneNumberUtils.getExampleNumber(countryNameCode)
        return phoneNumberUtils.format(
            phoneExample,
            PhoneNumberUtil.PhoneNumberFormat.NATIONAL
        )
    }

    override fun getPhoneNumberTransformation(countryNameCode: String): PhoneNumberVisualTransformation =
        PhoneNumberVisualTransformation(phoneNumberUtils.getAsYouTypeFormatter(countryNameCode))
}