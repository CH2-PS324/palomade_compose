package com.example.palomadeapps.ui.screen.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.palomadeapps.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@Composable
fun CameraScreen(
    navigate: NavHostController
) {
    var currentImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val context = LocalContext.current
    val bitmap =  remember {
        mutableStateOf<Bitmap?>(null)
    }

    val Gallerylauncher = rememberLauncherForActivityResult(contract =

    ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (currentImageUri != Uri.EMPTY) currentImageUri = Uri.EMPTY
        currentImageUri = uri
    }

    val file = context.createImageFile()
    var uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider", file
    )

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()){
            if (currentImageUri != null) currentImageUri = null
            currentImageUri = uri
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

    Column(
        Modifier
            .fillMaxWidth(1f)
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Row (
        ){
            ElevatedButton(
                modifier = Modifier,
                onClick = {
                    Gallerylauncher.launch("image/*")
                }) {
                Image(
                    modifier = Modifier
                        .padding()
                        .width(25.dp)
                        .height(25.dp),
                    painter = painterResource(id = R.drawable.ic_gallery),
                    contentDescription = null
                )
            }

            ElevatedButton(
                modifier = Modifier,
                onClick = {
                    val permissionCheckResult =
                        ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                        cameraLauncher.launch(uri)
                    } else {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                }
            ) {
                Image(
                    modifier = Modifier
                        .padding()
                        .width(25.dp)
                        .height(25.dp),
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = null
                )
            }
        }
    }
    Column {
        Spacer(modifier = Modifier.height(150.dp))

        if (currentImageUri?.path?.isNotEmpty() == false){
            currentImageUri?.let {
                if (Build.VERSION.SDK_INT < 28) {
                    bitmap.value = MediaStore.Images
                        .Media.getBitmap(context.contentResolver, it)

                } else {
                    val source = ImageDecoder
                        .createSource(context.contentResolver, it)
                    bitmap.value = ImageDecoder.decodeBitmap(source)
                }

                bitmap.value?.let { btm ->
                    Image(
                        bitmap = btm.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(250.dp)
                    )
                }
            }
        }

        if (currentImageUri?.path?.isNotEmpty() == true) {
            Image(
                modifier = Modifier
                    .padding(top = 0.dp, start = 10.dp, end = 10.dp, bottom = 20.dp)
                    .fillMaxWidth(1f)
                    .size(250.dp),
                painter = rememberImagePainter(currentImageUri),
                contentDescription = null
            )
        } else{
            Column (
                Modifier
                    .fillMaxWidth(1f)
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Image(
                    modifier = Modifier
                        .padding()
                        .width(150.dp)
                        .height(150.dp),
                    painter = painterResource(id = R.drawable.ic_baner_gallery),
                    contentDescription = null
                )
            }
        }
    }
}

fun Context.createImageeFile(): File {
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
//        CameraScreen()
//    }
//}
