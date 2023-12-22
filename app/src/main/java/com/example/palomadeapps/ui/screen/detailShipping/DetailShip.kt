package com.example.palomadeapps.ui.screen.detailShipping

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.palomadeapps.R
import com.example.palomadeapps.ui.screen.FAQ.BottomText

@Composable
fun DetailShipScreen(
    navigate: NavHostController
) {
    Column {
        DetailShipContent(onBackClick = { navigate.popBackStack() })
    }
}

@Composable
fun DetailShipContent(
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f)
            .background(color = Color(0xFFF2F2F2)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Box(
            modifier = Modifier
//                .border(width = 1.dp, color = Color(0xFFD5D5D5))
                .fillMaxWidth()
                .height(53.dp)
                .background(color = Color(0xFFFFFFFF))
                .padding(start = 20.dp, top = 16.dp, end = 20.dp, bottom = 16.dp)
        ){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "icon back",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterStart)
                    .clickable {
                        onBackClick()
                    }
            )
            Text(
                text = stringResource(id = R.string.title_detail_ship),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 23.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column {
                Text(
                    text = "Jakarta - Yogyakarta",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                    ) {
                        Text(
                            text = "Kode Pengiriman",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                        )
                    }
                    Column (
                        modifier = Modifier
                            .width(98.dp),
                    ){
                        Row (
                            modifier = Modifier
                                .width(590.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ){
                            Text(
                                text = "CH2-PS324",
                                modifier = Modifier
                                    .padding(end = 0.dp),
                                fontSize = 15.sp
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_copy),
                                contentDescription = "icon copy",
                                modifier = Modifier
                                    .size(17.dp)

                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Text(
                        text = "Berat Angkutan",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                    Text(
                        text = "50 TON",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Text(
                        text = "Plat Nomor",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                    Text(
                        text = "PS 3242 CH",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color(0xFFD5D5D5),
                                shape = RoundedCornerShape(size = 8.dp)
                            )
                            .fillMaxWidth(),
                    ) {
                        Column(
                            modifier = Modifier
                                .background(
                                    color = Color(0xFFFFFFFF),
                                    shape = RoundedCornerShape(size = 8.dp)
                                )
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Status Perjalanan",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                                    .padding(bottom = 15.dp),
                                fontSize = 15.sp,
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 15.dp),
                            ){
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = null,
                                )
                                Column {
                                    Text(
                                        text = "Jakarta",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(start = 8.dp),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Driver Siap Berangkat",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(start = 8.dp),
                                        fontSize = 14.sp,
                                    )
                                    Text(
                                        text = "23:00, 22 November 2023",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(start = 8.dp),
                                        fontSize = 10.sp,
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 15.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = null,
                                )
                                Column {
                                    Text(
                                        text = "Jakarta",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(start = 8.dp),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Driver Siap Berangkat",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(start = 8.dp),
                                        fontSize = 14.sp,
                                    )
                                    Text(
                                        text = "23:00, 22 November 2023",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(start = 8.dp),
                                        fontSize = 10.sp,
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                            ){
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = null,
                                )
                                Column {
                                    Text(
                                        text = "Jakarta",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(start = 8.dp),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Driver Siap Berangkat",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(start = 8.dp),
                                        fontSize = 14.sp,
                                    )
                                    Text(
                                        text = "23:00, 22 November 2023",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(start = 8.dp),
                                        fontSize = 10.sp,
                                    )
                                }
                            }
                        }
                    }
                }

                Row (
                    modifier = Modifier
                        .padding(top = 15.dp)
                ){
                    ElevatedButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xff008857)
                        ),
                        shape = RoundedCornerShape(5.dp),
                        onClick = { /*TODO*/ }
                    ) {
                        Text(
                            text = stringResource(id = R.string.btn_detail_ship),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .padding(top = 6.dp, bottom = 6.dp),
                            fontSize = 15.sp,
                            color = Color(0xFFFFFFFF)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ShippingCardPreview() {
    DetailShipContent({})
}