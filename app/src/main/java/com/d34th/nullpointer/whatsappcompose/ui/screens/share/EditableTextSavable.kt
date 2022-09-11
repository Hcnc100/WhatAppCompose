package com.d34th.nullpointer.whatsappcompose.ui.screens.share

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import com.d34th.nullpointer.whatsappcompose.core.states.PropertySavableString


@Composable
fun EditableTextSavable(
    modifier: Modifier = Modifier,
    modifierText: Modifier = Modifier,
    isEnabled: Boolean = true,
    singleLine: Boolean = false,
    valueProperty: PropertySavableString,
    shape: Shape = MaterialTheme.shapes.small,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Surface {
        Column(modifier = modifier.fillMaxWidth()) {
            OutlinedTextField(
                enabled = isEnabled,
                label = { Text(stringResource(id = valueProperty.label)) },
                placeholder = { Text(stringResource(id = valueProperty.hint)) },
                value = valueProperty.value,
                onValueChange = valueProperty::changeValue,
                isError = valueProperty.hasError,
                modifier = modifierText.fillMaxWidth(),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                shape = shape,
                visualTransformation = visualTransformation,
                singleLine = singleLine,
            )
            Row {
                Text(
                    text = if (valueProperty.hasError) stringResource(id = valueProperty.errorValue) else "",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.weight(.9f)
                )
                Text(
                    text = valueProperty.countLength,
                    color = if (valueProperty.hasError) MaterialTheme.colors.error else Color.Unspecified,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}