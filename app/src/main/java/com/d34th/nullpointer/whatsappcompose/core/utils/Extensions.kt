package com.d34th.nullpointer.whatsappcompose.core.utils

import android.content.Context
import androidx.annotation.PluralsRes

fun Context.getPlural(@PluralsRes stringQuality: Int, quality: Int): String {
    return resources.getQuantityString(stringQuality, quality, quality)
}