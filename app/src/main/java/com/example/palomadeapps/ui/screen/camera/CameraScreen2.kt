package com.example.palomadeapps.ui.screen.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.palomadeapps.R
import com.example.palomadeapps.ui.theme.PalomadeAppsTheme
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects
import androidx.activity.result.PickVisualMediaRequest

@Composable
fun CameraScreen2(
    navigate: NavHostController
){
    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider", file
    )
    var selectedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

     val pickerLauncher =
         rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()){
            selectedImageUri = uri
    }


    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()){
            capturedImageUri = uri
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){
        if (it)
        {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        }
        else
        {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(1f
            )
    ){
        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row {
                ElevatedButton(
                    modifier = Modifier
                        .width(190.dp),
                    onClick = {
                        val permissionCheckResult =
                            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

                        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED)
                        {
                            cameraLauncher.launch(uri)
                        }
                        else
                        {
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                    }) {
                    Text(text = "take from the camera")
                }
                ElevatedButton(
                    modifier = Modifier
                        .width(190.dp),
                    onClick = {
                        pickerLauncher.launch(())
                    }
                ) {
                    Text(text = "select from Gallery")
                }
            }
        }
    }

    if (capturedImageUri.path?.isNotEmpty() == true)
    {
        Image(
            modifier = Modifier
                .padding(top = 0.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth(1f)
                .fillMaxHeight(1f),
            painter = rememberImagePainter(capturedImageUri),
            contentDescription = null
        )
    }
    else
    {
//        Image(
//            modifier = Modifier
//                .padding(16.dp, 8.dp),
//            painter = painterResource(id = R.drawable.ic_gallery),
//            contentDescription = null
//        )
    }
}

fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
    return image
}


//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    PalomadeAppsTheme {
//        CameraScreen2({})
//    }
//}