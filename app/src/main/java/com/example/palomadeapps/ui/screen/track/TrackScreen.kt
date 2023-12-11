package com.example.palomadeapps.ui.screen.track

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.palomadeapps.R

//data class

@Composable
fun TrackScreen(

){
    TrackContent()
}

@Composable
fun TrackContent(

){
    Column () {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 0.dp, top = 20.dp)
                .height(61.dp)
                .fillMaxWidth(1f),
        ){
            Text(
                modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp),
                text = "Track Perjalanan",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000)
                )
            )

            ElevatedButton(
                modifier = Modifier
                    .padding(end = 10.dp),
                onClick = { /*todo*/ },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff008857)
                )
            ) {
                Row (
                    horizontalArrangement = Arrangement.Start
                ){
                    Column (
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .padding(end = 10.dp)
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = "Email Icon"
                        )
                    }
                }
                Text(text = "Pengiriman Baru")
            }
        }

        Divider(
            thickness = 1.dp, color = Color.Black
        )
        Row (
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(start = 20.dp, end = 25.dp, top = 10.dp)
        ){
            ElevatedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(120.dp)
                    .height(46.dp),
                elevation = null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff008857)
                )
            ) {
                Text("text",
                    modifier = Modifier,
                    style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF)
                ))
            }
            Text("text",
                modifier = Modifier,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF0000000),

                ))
//                Text(
//                    text = "$dayOfMonth-$month-$year"
//                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    TrackContent()
}