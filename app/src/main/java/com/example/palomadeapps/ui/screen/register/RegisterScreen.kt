package com.example.palomadeapps.ui.screen.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.palomadeapps.R
import com.example.palomadeapps.ui.components.TxtItem
import com.example.palomadeapps.ui.navigation.Screen
import com.example.palomadeapps.ui.theme.PalomadeAppsTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navigate: NavHostController
) {
    val context = LocalContext.current

    var name by rememberSaveable {
        mutableStateOf("")
    }

    var newEmail by rememberSaveable {
        mutableStateOf("")
    }

    var newPassword by rememberSaveable {
        mutableStateOf("")
    }

    var showPassword by remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }

    var isFocused by remember { mutableStateOf(false) }

    val loginn = "Login"

    val registerText = buildAnnotatedString {
        append("Already have an account?  ")
        withStyle(style = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)) {
            pushStringAnnotation(tag = loginn, annotation = loginn )
            append(loginn)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight(1f)
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ellipse2),
            contentDescription = "ellipse",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 20.dp)
        )
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ellipse1),
            contentDescription = "ellipse",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 20.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Image(
                    modifier = Modifier
                        .padding(15.dp)
                        .size(200.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "LogoApp"
                )
            }
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
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Email Icon"
                    )
                },

                label = { Text(text = "Full Name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                onValueChange = { newInput ->
                    name = newInput
                },
                shape = RoundedCornerShape(size = 15.dp),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(60.dp)
                    .width(320.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        isFocused = it.isFocused
                    },
            )

            OutlinedTextField(
                value = newEmail,
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
                    newEmail = newInput
                },
                shape = RoundedCornerShape(size = 15.dp),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .height(60.dp)
                    .width(320.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        isFocused = it.isFocused
                    },
            )
            OutlinedTextField(
                value = newPassword,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Email Icon"
                    )
                },

                label = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                onValueChange = { newInput ->
                    newPassword = newInput
                },
                shape = RoundedCornerShape(size = 15.dp),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .height(60.dp)
                    .width(320.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        isFocused = it.isFocused
                    },
                visualTransformation = if (showPassword) {

                    VisualTransformation.None

                } else {

                    PasswordVisualTransformation()

                },
                trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            Icon(
                                painterResource(id = R.drawable.ic_visibility),
                                contentDescription = "hide_password"
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { showPassword = true }) {
                            Icon(
                                painterResource(id = R.drawable.ic_visibility_off),
                                contentDescription = "hide_password"
                            )
                        }
                    }
                },
            )
            OutlinedTextField(
                value = newEmail,
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
                    newEmail = newInput
                },
                shape = RoundedCornerShape(size = 15.dp),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .height(60.dp)
                    .width(320.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        isFocused = it.isFocused
                    },
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 20.dp)
            ){

            }
            Button(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(40.dp)
                    .padding(start = 40.dp, end = 40.dp),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff008857)
                )

            ) {
                Text(
                    text = "Login"
                )
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
                        navigate.navigate(Screen.Login.route)
                        Toast.makeText(context, "Menuju Login", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun RegisterScreenPreview() {
//    PalomadeAppsTheme {
//        RegisterScreen()
//    }
//}