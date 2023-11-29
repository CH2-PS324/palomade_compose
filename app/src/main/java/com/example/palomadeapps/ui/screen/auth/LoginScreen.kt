package com.example.palomadeapps.ui.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.palomadeapps.R
import com.example.palomadeapps.ui.components.TxtItem
import com.example.palomadeapps.ui.theme.PalomadeAppsTheme

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

            OutlinedTextField(
                value = email,
                onValueChange = { newEmail ->
                    email = newEmail
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .background(
                        color = colorResource(id = R.color.teal_200),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .height(50.dp),
                placeholder = {
                    Text(
                        text = "Masukan Email Anda",
                        fontSize = 12.sp,
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon"
                    )
                },
                shape = RoundedCornerShape(20.dp),
                maxLines = 1,
            )
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