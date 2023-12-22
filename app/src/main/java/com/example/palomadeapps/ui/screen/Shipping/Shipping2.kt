package com.example.palomadeapps.ui.screen.Shipping

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.palomadeapps.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun Shipping2(
    navigate: NavHostController
){
    Column {
        Shipping2Content(onBackClick = { navigate.popBackStack() })
    }
    Row (
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(top = 553.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

        ){
        ElevatedButton(
            {
                navigate.navigate("Track")
            },
            Modifier
                .fillMaxWidth(1f)
                .width(360.dp)
                .height(56.dp)
                .padding(start = 30.dp, top = 8.dp, end = 30.dp, bottom = 8.dp),

            shape = RoundedCornerShape(10.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff008857)
            ),
        ) {
            Row (
                horizontalArrangement = Arrangement.Start
            ){
                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    text = stringResource(R.string.btn_next)
                )
            }

        }
    }
}

@Composable
fun Shipping2Content(
    onBackClick: () -> Unit
){
    val singapore = LatLng(51.52061810406676, -0.12635325270312533)

    val singaporee = LatLng(6.1944, 106.8229)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }

    val locationState = rememberMarkerState(
        position = singapore,
    )

    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    val mapUiSettings by remember {
        mutableStateOf(MapUiSettings(compassEnabled = false))
    }
    val focusRequester = remember { FocusRequester() }

    val mapProperties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }
    var isFocused by remember { mutableStateOf(false) }

    var showInfoWindow by remember {
        mutableStateOf(true)
    }

    Column (
        modifier = Modifier
            .fillMaxHeight(1f)
            .background(color = colorResource(id = R.color.primary))
    ){
        Box(
            modifier = Modifier
//                .border(width = 1.dp, color = Color(0xFFD5D5D5))
                .fillMaxWidth()
                .height(53.dp)
                .background(color = Color(0xFFFFFFFF))
                .padding(start = 20.dp, top = 16.dp, end = 20.dp, bottom = 6.dp)
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
                text = stringResource(id = R.string.title_new_shipping),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterStart)
                    .padding(start = 30.dp)
            )
        }
        Card(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                .fillMaxWidth(),
//        backgroundColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Titik Tujuan",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Color(0xFF000000),
                    fontSize = 17.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                GoogleMap(
                    modifier = Modifier
                        .height(160.dp),
                    cameraPositionState = cameraPositionState
                ) {
                    Marker(
                        state = MarkerState(position = singapore),
                        title = "London",
                        snippet = "Marker in Big Ben"
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Titik Tujuan",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Color(0xFF000000),
                    fontSize = 17.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Center
                ){
                    OutlinedTextField(
                        value = email,
                        label = { Text(text = "Masukan Nama Lokasi") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                        shape = RoundedCornerShape(size = 10.dp),
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .height(60.dp)
                            .width(380.dp)
                            .focusRequester(focusRequester)
                            .onFocusChanged {
                                isFocused = it.isFocused
                            },
                        onValueChange = { newEmail ->
                            email = newEmail
                        },
                    )
                }
            }
        }

        Card(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                .fillMaxWidth(),
//        backgroundColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Titik Tujuan",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Color(0xFF000000),
                    fontSize = 17.sp,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Center
                ){
                    OutlinedTextField(
                        value = email,
                        label = { Text(text = "Masukan Berat Angkutan") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                        shape = RoundedCornerShape(size = 10.dp),
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .height(60.dp)
                            .width(380.dp)
                            .focusRequester(focusRequester)
                            .onFocusChanged {
                                isFocused = it.isFocused
                            },
                        onValueChange = { newEmail ->
                            email = newEmail
                        },
                    )
                }
            }
        }
    }
}