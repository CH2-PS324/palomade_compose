package com.example.palomadeapps.ui.screen.camera

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Objects


//@Composable
//fun CameraScreen2(
//    navigate: NavHostController
//){
//    val context = LocalContext.current
//    val file = context.createImageFile()
//    val uri = FileProvider.getUriForFile(
//        Objects.requireNonNull(context),
//        context.packageName + ".provider", file
//    )
//    var selectedImageUri by remember {
//        mutableStateOf<Uri>(Uri.EMPTY)
//    }
//
//    var capturedImageUri by remember {
//        mutableStateOf<Uri>(Uri.EMPTY)
//    }
//
//    val pickerLauncher =
//        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()){
//            selectedImageUri = uri
//        }
//
//    val cameraLauncher =
//        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()){
//            capturedImageUri = uri
//        }
//
//    val permissionLauncher = rememberLauncherForActivityResult(
//        ActivityResultContracts.RequestPermission()
//    ){
//        if (it)
//        {
//            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
//            cameraLauncher.launch(uri)
//        }
//        else
//        {
//            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth(1f
//            )
//    ){
//        Column(
//            Modifier
//                .fillMaxSize()
//                .padding(bottom = 16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Bottom
//        ) {
//            Row {
//                ElevatedButton(
//                    modifier = Modifier
//                        .width(190.dp),
//                    onClick = {
//                        val permissionCheckResult =
//                            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
//
//                        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED)
//                        {
//                            cameraLauncher.launch(uri)
//                        }
//                        else
//                        {
//                            permissionLauncher.launch(Manifest.permission.CAMERA)
//                        }
//                    }) {
//                    Text(text = "take from the camera")
//                }
//                ElevatedButton(
//                    modifier = Modifier
//                        .width(190.dp),
//                    onClick = {
//                        pickerLauncher.launch(PickVisualMediaRequest())
//                    }
//                ) {
//                    Text(text = "select from Gallery")
//                }
//            }
//        }
//    }
//
//    if (capturedImageUri.path?.isNotEmpty() == true)
//    {
//        Image(
//            modifier = Modifier
//                .padding(top = 0.dp, start = 10.dp, end = 10.dp)
//                .fillMaxWidth(1f)
//                .fillMaxHeight(1f),
//            painter = rememberImagePainter(capturedImageUri),
//            contentDescription = null
//        )
//    }
//    else
//    {
//        Text(text = "gagal")
//    }
//
//    if (selectedImageUri.path?.isNotEmpty() == true){
//        Image(
//            modifier = Modifier
//                .padding(top = 0.dp, start = 10.dp, end = 10.dp)
//                .fillMaxWidth(1f)
//                .fillMaxHeight(1f),
//            painter = rememberImagePainter(selectedImageUri),
//            contentDescription = "From Gallery"
//        )
//    }
//}
@Composable
fun CameraScreen2(
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

    Column() {

        ElevatedButton(onClick = {
            Gallerylauncher.launch("image/*")
        }) {
            Text(text = "Pick image")
        }

        ElevatedButton(
            modifier = Modifier
                .width(190.dp),
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
            Text(text = "take from the camera")
        }

        Spacer(modifier = Modifier.height(5.dp))

        if (currentImageUri?.path?.isNotEmpty() == false)
        {
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
                        modifier = Modifier.size(400.dp)
                    )
                }
            }
        }

        if (currentImageUri?.path?.isNotEmpty() == true)
        {
            Image(
                modifier = Modifier
                    .padding(top = 0.dp, start = 10.dp, end = 10.dp)
                    .fillMaxWidth(1f)
                    .fillMaxHeight(1f),
                painter = rememberImagePainter(currentImageUri),
                contentDescription = null
            )
        }
    }
}
private const val FILENAME_FORMAT = "yyyyMMdd_HHmmss"
private val timeStamp: String = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(Date())

fun uriToFile(imageUri: Uri, context: Context): File {
    val file = createCustomTempFile(context)
    val inputStream = context.contentResolver.openInputStream(imageUri) as InputStream
    val outputStream = FileOutputStream(file)
    val buffer = ByteArray(1024)
    var length: Int
    while (inputStream.read(buffer).also { length = it } > 0) outputStream.write(buffer, 0, length)
    outputStream.close()
    inputStream.close()
    return file
}

fun createCustomTempFile(context: Context): File {
    val filesDir = context.externalCacheDir
    return File.createTempFile(timeStamp, ".jpg", filesDir)
}

fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH:mm:ss", Locale.getDefault()).format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
    return image
}