package com.example.palomadeapps.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.palomadeapps.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Shipping(
    navigate: NavHostController,
) {
    Card(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
        onClick = {
            navigate.navigate("detailship")
        },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 10.dp, bottom = 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.shipping),
                contentDescription = null,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFF000000),
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .size(70.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 10.dp)
                    .width(248.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.label_shipping),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFF686868),
                            shape = RoundedCornerShape(size = 4.dp)
                        )
                        .width(75.dp)
                        .height(21.dp)
                        .background(
                            color = Color(0xFFEBEBEB),
                            shape = RoundedCornerShape(size = 4.dp)
                        )
                        .padding(start = 4.dp, bottom = 2.dp)
                )
                Text(
                    text = "Jakarta - Yogyakarta",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "22 November 2023",
                    fontSize = 11.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_end), contentDescription = "gambar arrow",
                modifier = Modifier
                    .padding(top = 5.dp)
            )
        }
        Divider(
            thickness = 1.dp, color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 8.dp, bottom = 8.dp)
        ){
            Text(
                text = "Estimasi tiba: 24 November 2023",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

//@Preview
//@Composable
//fun ShippingCardPreview() {
//    Shipping()
//}