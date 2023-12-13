package com.example.palomadeapps.ui.screen.profile

import android.content.Intent
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import android.provider.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.palomadeapps.MainActivity
import com.example.palomadeapps.R
import com.example.palomadeapps.ViewModelFactory
import com.example.palomadeapps.data.di.Injection

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    activity: ComponentActivity,

    modifier: Modifier = Modifier
){
    var showDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

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
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,

            ){
                val buttonColor = remember {
                    mutableStateOf(Color.Blue)
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .width(320.dp)
                        .height(56.dp)
                        .padding(start = 30.dp, top = 8.dp, end = 30.dp, bottom = 8.dp),

                    onClick = {
                        val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                        context.startActivity(intent)
                              },

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
                                    .padding(start = 0.dp)

                            ){
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_settings),
                                    contentDescription = "Email Icon"
                                )
                            }
                        }
                        Row (
                            modifier = Modifier
                                .padding(start = 3.dp)
                        ){
                            Text(
                                text = stringResource(R.string.btn_settings)
                            )
                        }
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 0.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,

                ){
                ElevatedButton(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .width(320.dp)
                        .height(56.dp)
                        .padding(start = 30.dp, top = 8.dp, end = 30.dp, bottom = 8.dp),
                    onClick = { showDialog = !showDialog },
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
                                .padding(start = 0.dp)

                        ){
                            Icon(
                                painter = painterResource(id = R.drawable.ic_logout),
                                contentDescription = "Email Icon"
                            )
                        }
                    }
                    Row (
                        modifier = Modifier
                            .padding(start = 3.dp)
                    ){
                        Text(
                            text = stringResource(R.string.btn_logout)
                        )
                    }
                }
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            // Handle dialog dismissal if needed
                            showDialog = false
                        },
                        title = {
                            Text("Login Berhasil")
                        },
                        text = {
                            Text("Yuk lanjutin ke halaman selanjutnya")
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    showDialog = false
                                    val intent = Intent(context, MainActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                    viewModel.logout()
                                    context.startActivity(intent)
                                    (context as? ComponentActivity)?.finish()
                                },
                                colors = ButtonDefaults.elevatedButtonColors(
                                    containerColor = colorResource(id = R.color.Red)
                                )
                            ) {
                                Text("Yes", color = Color.White)
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = {
                                    showDialog = false
                                },
                                colors = ButtonDefaults.elevatedButtonColors(
                                    containerColor = Color.Gray
                                )
                            ) {
                                Text("No")
                            }
                        }
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ProfileScreenPreview() {
//    PalomadeAppsTheme {
//        ProfileScreen()
//    }
//}