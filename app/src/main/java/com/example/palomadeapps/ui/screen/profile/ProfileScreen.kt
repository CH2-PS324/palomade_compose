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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            val rainbowColorsBrush = remember {
                Brush.sweepGradient(
                    listOf(
                        Color(0xFF9575CD),
                        Color(0xFFBA68C8)
                    )
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Center
            ){
                val borderWidth = 6.dp
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Photo Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .border(
                            BorderStroke(borderWidth, rainbowColorsBrush),
                            CircleShape
                        )
                )
            }


            Text(
                text = stringResource(R.string.name),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
                ),
            )
            Text(
                text = stringResource(R.string.role),
                style = TextStyle(
                    fontSize = 16.sp,
                )
            )
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