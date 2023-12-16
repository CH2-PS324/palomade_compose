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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.palomadeapps.R
import com.example.palomadeapps.ViewModelFactory
import com.example.palomadeapps.data.di.Injection
import com.example.palomadeapps.model.Predict
import com.example.palomadeapps.ui.common.UiState
import com.example.palomadeapps.ui.screen.login.LoginViewModel
import com.example.palomadeapps.ui.uriToFile
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Objects

@Composable
fun CameraScreen2(
    navigate: NavHostController,
    context: Context = LocalContext.current,
    viewModel: CameraViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    )
) {

    var currentImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val context = LocalContext.current
    val bitmap =  remember {
        mutableStateOf<Bitmap?>(null)
    }

    val uploadState by viewModel.upload.observeAsState()

    val Gallerylauncher = rememberLauncherForActivityResult(contract =

    ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (currentImageUri != Uri.EMPTY) currentImageUri = Uri.EMPTY
        currentImageUri = uri
    }
    var showLoading by remember { mutableStateOf(false) }

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
        ElevatedButton(
            modifier = Modifier
                .width(130.dp)
                .height(44.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff008857)
            ),
            onClick = {

                      Toast.makeText(context, "Prediction", Toast.LENGTH_SHORT).show()
//                  viewModel.prediction(imageFile, "bongkahan")
            },
            shape = RoundedCornerShape(10.dp),

            ) {
//            Image(
//                modifier = Modifier
//                    .padding()
//                    .width(25.dp)
//                    .height(25.dp),
//                painter = painterResource(id = R.drawable.ic_scan),
//                contentDescription = null
//            )
            Text(text = "Prediction")
        }

        // dua button gallery and camera
        Row (
            modifier = Modifier
                .padding(top = 10.dp),
        ){
            ElevatedButton(
                modifier = Modifier
                    .width(130.dp)
                    .height(44.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff008857)
                ),
                onClick = {
                Gallerylauncher.launch("image/*")
                },
                shape = RoundedCornerShape(10.dp),
            ) {
                Image(
                    modifier = Modifier
                        .padding()
                        .width(25.dp)
                        .height(25.dp),
                    painter = painterResource(id = R.drawable.ic_gallery),
                    contentDescription = null,
                )
                Text(text = "Gallery")
            }
            Spacer(modifier = Modifier.width(10.dp))
            ElevatedButton(
                modifier = Modifier
                    .width(135.dp)
                    .height(44.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff008857)
                ),
                onClick = {
                    val permissionCheckResult =
                        ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                        cameraLauncher.launch(uri)
                    } else {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                },
                shape = RoundedCornerShape(10.dp),

            ) {
                Image(
                    modifier = Modifier
                        .padding()
                        .size(35.dp),
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = null
                )
                Text(text = "Camera")
            }
        }
    }

    Column {
        Spacer(modifier = Modifier.height(120.dp))

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
                        .size(150.dp),
                    painter = painterResource(id = R.drawable.ic_baner_galer),
                    contentDescription = null
                )
            }
        }
    }
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