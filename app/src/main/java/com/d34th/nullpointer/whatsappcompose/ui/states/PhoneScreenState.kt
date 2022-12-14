package com.d34th.nullpointer.whatsappcompose.ui.states

import android.content.Context
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager

class PhoneScreenState(
    context: Context,
    scaffoldState: ScaffoldState,
    private val focusManager: FocusManager
) : SimpleScreenState(context, scaffoldState) {

    fun hiddenKeyboard() {
        focusManager.clearFocus()
    }
}

@Composable
fun rememberPhoneScreenState(
    context: Context = LocalContext.current,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    focusManager: FocusManager = LocalFocusManager.current,
) = remember(scaffoldState) {
    PhoneScreenState(context, scaffoldState, focusManager)
}