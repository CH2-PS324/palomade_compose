package com.example.palomadeapps.ui.screen.welcome

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.palomadeapps.R
import com.example.palomadeapps.ui.components.TxtItem
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier
) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight(1f)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            val snackbarCoroutineSCope = rememberCoroutineScope()

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 200.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .padding(15.dp)
                        .size(200.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "ImageWelcome"
                )
            }

            TxtItem(
                desc = stringResource(id = R.string.titleWelcome),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 100.dp)
            ){
                Button(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(40.dp)
                        .padding(start = 40.dp, end = 40.dp),
                    onClick = {
                        snackbarCoroutineSCope.launch{
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff008857)
                    )
                ) {
                    Text(
                        text = "Getting Started",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                    )
                }
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
                        containerColor = Color(0xffffffff)
                    )
                ) {
                    Text(
                        text = "Already have an account?",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color(0xff008857)
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    WelcomeScreen()
}