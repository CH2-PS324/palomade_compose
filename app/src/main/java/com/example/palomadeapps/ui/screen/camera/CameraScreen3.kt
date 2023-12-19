package com.example.palomadeapps.ui.screen.camera

import android.Manifest
import android.app.Activity
import android.content.ContentValues
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.palomadeapps.R
import com.example.palomadeapps.data.di.Injection
import com.example.palomadeapps.ui.theme.PalomadeAppsTheme
import java.io.File

private const val TAG = "ScanScreen"

@Composable
fun CameraScreen3(

) {

    val context = LocalContext.current

    var image by remember {
        mutableStateOf<Bitmap?>(null)
    }

    var currentImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var type by remember {
        mutableStateOf("")
    }

    val viewModel: CameraViewModel = viewModel(
        factory = viewModelFactory {
            addInitializer(CameraViewModel::class) {
                CameraViewModel(Injection.providePrediction(context))
            }
        }
    )

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(2f)
        ) {

            ImagePreviewSection(
                image,
                uiState.prediction?.percentage?.toString() ?: "0",
                uiState.prediction?.classType ?: ""
            )

            Log.i(TAG, "CameraScreen3: isLoading ${uiState.isLoading}, isLoading ${uiState.prediction?.percentage}, ${uiState.errorMessage}")
        }
        Column(
        ) {
            ActionButtonsSection(
                imageCaptured = { bitmap, uri ->
                    image = bitmap
                    currentImageUri = uri
                },
                selectedIndex = {
                    type = if(it == 0) {
                        "Bongkahan"
                    } else {
                        "Brondolan"
                    }
                },
                viewModel = viewModel
            )
        }

    }

}



@Composable
fun ImagePreviewSection(
    image: Bitmap?,
    percentage: String?,
    classType: String?
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
//            .border(width = 1.dp, color = Color.Black)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (image != null) {
            Image(
                bitmap = image.asImageBitmap(),
                contentDescription = "Image to scanned",
                modifier = Modifier.size(width = 240.dp, height = 320.dp)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_baner_galer),
                contentDescription = "Image to scanned",
                modifier = Modifier.size(width = 240.dp, height = 320.dp)
            )
        }

        //Image scan result
        Text(
            text = "Percentage: $percentage%",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Category: $classType",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun ActionButtonsSection(
    imageCaptured: (Bitmap?, Uri?) -> Unit = { bitmap: Bitmap?, uri: Uri? -> },
    selectedIndex: (Int) -> Unit = {},
    viewModel: CameraViewModel
) {
    LocalContext.current as Activity

    var expanded by remember {
        mutableStateOf(value = false)
    }

    var selectedImageSourceIndex by remember {
        mutableIntStateOf(value = 0)
    }

    var selectedImageType by remember {
        mutableStateOf(value = "")
    }

    Log.i(TAG, "ActionButtonsSection: $selectedImageSourceIndex")

    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .border(width = 1.dp, color = Color.Black)

    ) {
        ElevatedButton(
            onClick = {
                expanded = !expanded
                selectedImageType = "Bongkahan"
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff008857)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Bongkahan")
        }

        ElevatedButton(
            onClick = {
                expanded = !expanded
                selectedImageType = "Brondolan"
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff008857)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Brondolan")
        }
    }
    ScanTypeMenu(
        expanded = expanded,
        onDismiss = {
            expanded = false
        },
        selectedIndex = {
            selectedImageSourceIndex = it
            selectedIndex(selectedImageSourceIndex)
        },
        imageCaptured = { bitmap, uri ->
            imageCaptured(bitmap, uri)
        },
        viewModel = viewModel
    )
}

@Composable
fun ScanTypeMenu(
    expanded: Boolean,
    onDismiss: () -> Unit = {},
    selectedIndex: (Int) -> Unit = {},
    imageCaptured: (Bitmap?, Uri?) -> Unit = { bitmap: Bitmap?, uri: Uri? -> },
    viewModel: CameraViewModel
) {

    val context = LocalContext.current
    var index by remember {
        mutableIntStateOf(value = 0)
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(value = null)
    }

    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }

    LaunchedEffect(key1 = bitmap, block = {
        bitmap?.let { bitmap ->
            viewModel.prediction(bitmap, if (index == 0) "bongkahan" else "brondolan")
        }
    })



    val runtimePermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                Log.i(TAG, "Permission Granted")
            } else {
                Log.i(TAG, "Permission Denied")
            }
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract =  ActivityResultContracts.TakePicture(),
        onResult = { isSuccess ->
            if (isSuccess) {
                bitmap = imageUri?.getBitmapFromUri(context)
                imageCaptured(bitmap, imageUri)

                Log.i(TAG, "from camera ${imageUri}")

            } else {
                Log.i(TAG, "Image not captured")
            }
        }
    )

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            imageUri = it
            bitmap = imageUri?.getBitmapFromUri(context)
            imageCaptured(bitmap, imageUri)
            Log.i(TAG, "from gallery ${bitmap}")
        }
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismiss() },
        offset = DpOffset(x = 120.dp, y = 0.dp)
    ) {
        DropdownMenuItem(
            text = {
                Text(text = "Gallery")
            },
            onClick = {
                index = 0
                selectedIndex(index)
                onDismiss()
                when {
                    ContextCompat.checkSelfPermission(context as Activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
                        galleryLauncher.launch("image/*")
                    }

                    ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.CAMERA) -> {
                        Log.i(TAG, "show some rational")
                    }

                    else -> {
                        runtimePermissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                }
                Toast.makeText(context, "Launch the folder", Toast.LENGTH_SHORT).show()
            }
        )

        DropdownMenuItem(
            text = {
                Text(text = "Camera")
            },
            onClick = {
                index = 1
                selectedIndex(index)
                onDismiss()
                when {
                    ContextCompat.checkSelfPermission(context as Activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
                        val values = ContentValues()
                        values.put(MediaStore.Images.Media.TITLE, "New PictureGroup")
                        values.put(MediaStore.Images.Media.DESCRIPTION, "From the camera")
                        imageUri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
                        cameraLauncher.launch(imageUri)
                    }

                    ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.CAMERA) -> {
                        Log.i(TAG, "show some rational")
                    }

                    else -> {
                        runtimePermissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                }
                Toast.makeText(context, "Launch the camera", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

private fun Uri.getBitmapFromUri(context: Context): Bitmap {
    return if (Build.VERSION.SDK_INT < 28) {
        MediaStore.Images.Media.getBitmap(context.contentResolver, this)
    } else {
        val source = ImageDecoder.createSource(context.contentResolver, this)
        ImageDecoder.decodeBitmap(source)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScanScreenPreview() {
//    ImagePreviewSection()
    PalomadeAppsTheme {
        CameraScreen3()
    }
}