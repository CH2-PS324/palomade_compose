package com.example.palomadeapps.ui.screen.register

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.res.colorResource
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.palomadeapps.MainActivity
import com.example.palomadeapps.R
import com.example.palomadeapps.ViewModelFactory
import com.example.palomadeapps.data.di.Injection
import com.example.palomadeapps.ui.common.UiState
import com.example.palomadeapps.ui.components.TxtItem
import com.example.palomadeapps.ui.navigation.Screen
import com.example.palomadeapps.ui.theme.PalomadeAppsTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,

    context: Context = LocalContext.current,
    viewModel: RegisterViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),

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

    var showDialog by remember { mutableStateOf(false) }

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
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    val uploadState by viewModel.upload.observeAsState()

    val wasFocused = remember { isFocused }
    when (val uiState = uploadState) {
        is UiState.Loading -> {

        }
        is UiState.Success -> {

            showDialog = true

        }
        is UiState.Error -> {

            showDialog = false

        }

        else -> {}
    }


    LaunchedEffect(true) {
        if (wasFocused) {
            focusRequester.requestFocus()
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
                value = viewModel.name,
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
                    viewModel.name = newInput
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
                    viewModel.email = newInput
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
                value = viewModel.password,
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
                    viewModel.password = newInput
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
                value = viewModel.role,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon"
                    )
                },

                label = { Text(text = "Role") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                onValueChange = { newInput ->
                    viewModel.role = newInput
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

            ElevatedButton(
                onClick = {
                    if (viewModel.password.length < 8) {
                        Toast.makeText(context,"Password kurang dari 8", Toast.LENGTH_SHORT).show()
                        return@ElevatedButton
                    }
                    // Set showDialog to true when the button is clicked
                    viewModel.uploadData(
                        viewModel.name,
                        viewModel.email,
                        viewModel.password,
                        viewModel.role,
                    )

                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = colorResource(id = R.color.Yellow)
                ),
                enabled = !isLoading,
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White
                    )
                } else {
                    Text("REGISTER")
                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = {
                        // Handle dialog dismissal if needed
                        showDialog = false
                    },
                    title = {
                        Text("Register Succesfull")
                    },
                    text = {
                        Text("Are you sure you want to proceed?")
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                showDialog = false
                                val intent = Intent(context, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                context.startActivity(intent)
                                (context as? ComponentActivity)?.finish()
                            },
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = colorResource(id = R.color.Yellow)
                            )
                        ) {
                            Text("Yes")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                showDialog = false
                            },
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = Color.Black
                            )
                        ) {
                            Text("No")
                        }
                    }
                )
            }

//            Button(
//                modifier = Modifier
//                    .fillMaxWidth(1f)
//                    .height(40.dp)
//                    .padding(start = 40.dp, end = 40.dp, top = 0.dp),
//                onClick = {
//                          showDialog = false
//                            val intent = Intent(context, MainActivity::class.java)
//                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                            context.startActivity(intent)
//                            (context as? ComponentActivity)?.finish()
//                          },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xff008857)
//                )
//
//            ) {
//                Text(
//                    text = "Register"
//                )
//            }
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