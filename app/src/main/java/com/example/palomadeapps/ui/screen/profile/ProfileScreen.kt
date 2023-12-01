package com.example.palomadeapps.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.palomadeapps.R
import com.example.palomadeapps.ui.screen.auth.register.RegisterScreen
import com.example.palomadeapps.ui.theme.PalomadeAppsTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
){
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
//            val rainbowColorsBrush = remember {
//                Brush.sweepGradient(
//                    listOf(
//                        Color(0xFF9575CD),
//                        Color(0xFFBA68C8)
//                    )
//                )
//            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 42.dp),
                horizontalArrangement = Arrangement.Center
            ){
                    Image(
                        painter = painterResource(R.drawable.potoku),
                        contentDescription = "Photo Profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                    )
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = stringResource(R.string.name),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center,
                    ),
                )
            }
            Text(
                text = stringResource(R.string.role),
                style = TextStyle(
                    fontSize = 16.sp,
                )
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,

            ){
                val buttonColor = remember {
                    mutableStateOf(Color.Blue)
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 40.dp, end = 40.dp)
                        .width(320.dp)
                        .height(56.dp)

                        .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
                    onClick = { buttonColor.value = Color.Gray },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff008857)
                    )
                ) {
                    Column (
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_settings),
                            contentDescription = "Email Icon"
                        )
                    }
                    Text(
                        text = "Settings"
                    )
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 0.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,

                ){
                val buttonColor = remember {
                    mutableStateOf(Color.Blue)
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 40.dp, end = 40.dp)
                        .width(320.dp)
                        .height(56.dp)

                        .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
                    onClick = { buttonColor.value = Color.Gray },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff008857)
                    )
                ) {
                    Column (
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_logout),
                            contentDescription = "Email Icon"
                        )
                    }
                    Text(
                        text = "Logout"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    PalomadeAppsTheme {
        ProfileScreen()
    }
}