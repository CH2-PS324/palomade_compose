package com.example.palomadeapps.ui.screen.auth.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen (
    modifier: Modifier = Modifier
){
    val scrollStateHorizontal = rememberScrollState()
    val scrollStateVertical = rememberScrollState()

    val context = LocalContext.current

    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var showPassword by remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }

    var isFocused by remember { mutableStateOf(false) }

    val wasFocused = remember { isFocused }

    val register = "Register"
    val registerText = buildAnnotatedString {
        append("Don't Have an Account  ")
        withStyle(style = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)) {
            pushStringAnnotation(tag = register, annotation = register )
            append(register)
        }
    }

    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
    ){
        Column ( modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                colorResource(id = R.color.white)
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            ){
            TxtItem(
                desc = stringResource(id = R.string.login),
                fontWeight = FontWeight.SemiBold,
                fontSize = 45.sp,
            )
            TxtItem(
                desc = stringResource(id = R.string.titleLogin),
                fontSize = 12.sp,
            )

            OutlinedTextField(
                value = email,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon"
                    )
                },

                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                onValueChange = { newInput ->
                    email = newInput
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

            OutlinedTextField(
                value = password,
                onValueChange = { newPassword ->
                    password = newPassword
                },
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
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon"
                    )
                },

                label = {
                    Text(
                        text = "Password"
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
//                placeholder = {
//                    Text(
//                        text = "Masukan Email Anda",
//                        fontSize = 12.sp,
//                    )
//                },
                shape = RoundedCornerShape(15.dp),
                maxLines = 1,
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 40.dp)
            ){
                Button(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(40.dp)
                        .padding(start = 40.dp, end = 40.dp),
                    onClick = { /*TODO*/ }

                ) {
                    Text(
                        text = "Login"
                    )
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                ClickableText(
                    text = registerText ,
                    onClick = {
                        Toast.makeText(context, "Menuju Register", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    PalomadeAppsTheme {
        LoginScreen()
    }
}