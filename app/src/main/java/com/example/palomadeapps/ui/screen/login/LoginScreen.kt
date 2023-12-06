package com.example.palomadeapps.ui.screen.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.palomadeapps.R
import com.example.palomadeapps.ui.components.TxtItem
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.palomadeapps.ViewModelFactory
import com.example.palomadeapps.data.di.Injection
import com.example.palomadeapps.data.pref.UserModel
import com.example.palomadeapps.ui.common.UiState
import com.example.palomadeapps.ui.navigation.Screen
import com.example.palomadeapps.ui.screen.register.RegisterViewModel
import com.example.palomadeapps.ui.theme.PalomadeAppsTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen (
    context: Context = LocalContext.current,
    viewModel: LoginViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),

    navigate: NavHostController
){
    val scrollStateHorizontal = rememberScrollState()
    val scrollStateVertical = rememberScrollState()

    val context = LocalContext.current

    var email by rememberSaveable {
        mutableStateOf("")
    }
    var showDialog by remember { mutableStateOf(false) }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var showPassword by remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }

    var isFocused by remember { mutableStateOf(false) }

    var showLoading by remember { mutableStateOf(false) }
    val uploadState by viewModel.upload.observeAsState()

    val wasFocused = remember { isFocused }

    when (val uiState = uploadState) {
        is UiState.Loading -> {
            showLoading = true
        }
        is UiState.Success -> {
            showDialog = true
        }
            is UiState.Error -> {
                showLoading = false
                Toast.makeText(context, "Password atau Email salah", Toast.LENGTH_SHORT).show()
            }
        else -> {}
    }

    LaunchedEffect(true) {
        if (wasFocused) {
            focusRequester.requestFocus()
        }
    }

    val register = "Register"

    val registerText = buildAnnotatedString {
        append("Don't Have an Account?  ")
        withStyle(style = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)) {
            pushStringAnnotation(tag = register, annotation = register )
            append(register)
        }
    }

    Box (
        modifier = Modifier
            .fillMaxHeight(1f)
    ){
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
        Column ( modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            ){

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
                desc = stringResource(id = R.string.login),
                fontWeight = FontWeight.SemiBold,
                fontSize = 40.sp,
            )
            TxtItem(
                desc = stringResource(id = R.string.titleLogin),
                fontSize = 12.sp,
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Center
            ){
                OutlinedTextField(
                    value = viewModel.email,
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
                    shape = RoundedCornerShape(size = 12.dp),
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(60.dp)
                        .width(320.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged {
                            isFocused = it.isFocused
                        },
                )
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(),
                horizontalArrangement = Arrangement.Center
            ){
                OutlinedTextField(
                    value = password,
                    onValueChange = { newPassword ->
                        password = newPassword
                    },
                    modifier = Modifier
                        .padding(top = 13.dp)
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
                    label = {
                        Text(
                            text = "Password"
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
//                placeholder = {
//                    Text(
//                        text = "Masukan Email Anda",
//                        fontSize = 12.sp,
//                    )
//                },
                    shape = RoundedCornerShape(12.dp),
                    maxLines = 1,
                )
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 22.dp)
            ){
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
                        (navigate.navigate(Screen.Register.route))
                        Toast.makeText(context, "Menuju Register", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    PalomadeAppsTheme {
//        LoginScreen()
//    }
//}