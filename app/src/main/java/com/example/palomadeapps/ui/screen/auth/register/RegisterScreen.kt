package com.example.palomadeapps.ui.screen.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.palomadeapps.R
import com.example.palomadeapps.ui.components.TxtItem
import com.example.palomadeapps.ui.theme.PalomadeAppsTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var name by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    val focusRequester = remember { FocusRequester() }

    var isFocused by remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    colorResource(id = R.color.white)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TxtItem(
                desc = stringResource(id = R.string.regis),
                fontWeight = FontWeight.SemiBold,
                fontSize = 45.sp,
            )
            TxtItem(
                desc = stringResource(id = R.string.titleRegis),
                fontSize = 14.sp,
            )
            OutlinedTextField(
                value = name,
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Email,
//                        contentDescription = "Email Icon"
//                    )
//                },

                label = { Text(text = "Full Name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                onValueChange = { newInput ->
                    name = newInput
                },
                shape = RoundedCornerShape(size = 15.dp),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .height(60.dp)
                    .width(320.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        isFocused = it.isFocused
                    },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    PalomadeAppsTheme {
        RegisterScreen()
    }
}