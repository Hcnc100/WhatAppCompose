package com.d34th.nullpointer.whatsappcompose.ui.screens.share

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import com.d34th.nullpointer.whatsappcompose.core.states.PropertySavableString


@Composable
fun EditableTextSavable(
    modifier: Modifier = Modifier,
    modifierText: Modifier = Modifier,
    hintOwner: String = "",
    isEnabled: Boolean = true,
    singleLine: Boolean = false,
    valueProperty: PropertySavableString,
    shape: Shape = MaterialTheme.shapes.small,
    textStyle: TextStyle = LocalTextStyle.current,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Surface {
        Column(modifier = modifier.fillMaxWidth()) {
            OutlinedTextField(
                shape = shape,
                enabled = isEnabled,
                textStyle = textStyle,
                singleLine = singleLine,
                value = valueProperty.value,
                isError = valueProperty.hasError,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                modifier = modifierText.fillMaxWidth(),
                onValueChange = valueProperty::changeValue,
                visualTransformation = visualTransformation,
                label = { Text(stringResource(id = valueProperty.label)) },
                placeholder = {
                    if (hintOwner.isNotEmpty()) {
                        Text(hintOwner)
                    } else {
                        Text(stringResource(id = valueProperty.hint))
                    }
                }
            )
            Row {
                Text(
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.weight(.9f),
                    style = MaterialTheme.typography.caption,
                    text = if (valueProperty.hasError) stringResource(id = valueProperty.errorValue) else ""
                )
                Text(
                    text = valueProperty.countLength,
                    style = MaterialTheme.typography.caption,
                    color = if (valueProperty.hasError) MaterialTheme.colors.error else Color.Unspecified
                )
            }
        }
    }
}