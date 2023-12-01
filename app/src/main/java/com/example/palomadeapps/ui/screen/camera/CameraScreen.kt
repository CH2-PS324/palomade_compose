package com.example.palomadeapps.ui.screen.camera

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.palomadeapps.R
import com.example.palomadeapps.ui.screen.auth.register.RegisterScreen
import com.example.palomadeapps.ui.theme.PalomadeAppsTheme

@Composable
fun CameraScreen (
    modifier: Modifier = Modifier
){
    Box (

    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
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
                        .height(76.dp)
                        .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
                    onClick = { buttonColor.value = Color.Gray },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff008857)
                    )
                ) {
                    Text(
                        text = "Bongkahan",
                        modifier
                    )
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 10.dp),
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
                        .height(76.dp)
                        .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
                    onClick = { buttonColor.value = Color.Gray },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff008857)
                    )
                ) {
                    Text(
                        text = "Brondolan"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CameraScreenPreview() {
    PalomadeAppsTheme {
        CameraScreen()
    }
}