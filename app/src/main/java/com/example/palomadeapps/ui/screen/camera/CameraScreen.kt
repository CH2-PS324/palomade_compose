package com.example.palomadeapps.ui.screen.camera

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.palomadeapps.CameraPreview
import com.example.palomadeapps.MainActivity
import com.example.palomadeapps.R
import com.example.palomadeapps.ui.theme.PalomadeAppsTheme
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.util.concurrent.Executors

@OptIn(ExperimentalGetImage::class) @Composable
fun CameraScreen(
    navigate: NavHostController

) {
    val context = LocalContext.current
    fun hasRequiredPermissions(): Boolean{
        return MainActivity.CAMERAX_PERMISSIONS.all{
            ContextCompat.checkSelfPermission(
                context,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    val previewView: PreviewView = remember { PreviewView(context) }
    val cameraController = remember { LifecycleCameraController(context) }
    val lifecycleOwner = LocalLifecycleOwner.current
    cameraController.bindToLifecycle(lifecycleOwner)
    cameraController.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    previewView.controller = cameraController

    val executor = remember { Executors.newSingleThreadExecutor() }
    val textRecognizer = remember { TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS) }

    var text by rememberSaveable {
        mutableStateOf("")
    }
    var isLoading by remember { mutableStateOf(false) }

//    val scope = rememberCoroutineScope()
//                val scaffoldState = rememberBottomSheetScaffoldState()
//                val controller = remember {
//                    LifecycleCameraController(applicationContext).apply {
//                        setEnabledUseCases(
//                            CameraController.IMAGE_CAPTURE or
//                                    CameraController.VIDEO_CAPTURE
//                        )
//                    }
//                }
//    val viewModel = viewModel
//                val bitmaps by viewModel.bitmaps.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize())

        // Show loading indicator when isLoading is true
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )
        } else {
            Box(
                modifier = Modifier
                .fillMaxSize()
            ){
                CameraPreview(
                    controller = cameraController,
                    modifier = Modifier
                        .fillMaxSize()
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    onClick = {
                        isLoading = true
                        cameraController.setImageAnalysisAnalyzer(executor) { imageProxy ->
                            imageProxy.image?.let { image ->
                                val img = InputImage.fromMediaImage(
                                    image,
                                    imageProxy.imageInfo.rotationDegrees
                                )

                                textRecognizer.process(img).addOnCompleteListener { task ->
                                    isLoading = false
                                    text = if (!task.isSuccessful) task.exception!!.localizedMessage?.toString()
                                        .toString()
                                    else task.result.text

                                    cameraController.clearImageAnalysisAnalyzer()
                                    imageProxy.close()
                                }
                            }
                        }
                    }
                ) {
                    Icon(

                        painter = painterResource(id = R.drawable.ic_camera),
                        contentDescription = "camera",
                        tint = MaterialTheme.colorScheme.background,
                        modifier = Modifier.size(54.dp)
                    )
                }
            }
        }
    }

    if (text.isNotEmpty()) {
        Dialog(onDismissRequest = { text = "" }) {
            Card(modifier = Modifier.fillMaxWidth(0.8f)) {
                Text(
                    text = text,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                Button(
                    onClick = { text = "" },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    Text(text = "Done")
                }
            }
        }
    }
}

//private fun takePhoto(
//    controller: LifecycleCameraController,
//    onPhotoTaken: (Bitmap) -> Unit
//) {
//    controller.takePicture(
//        ContextCompat.getMainExecutor(applicationContext),
//        object : ImageCapture.OnImageCapturedCallback() {
//            override fun onCaptureSuccess(image: ImageProxy) {
//                super.onCaptureSuccess(image)
//
//                val matrix = Matrix().apply {
//                    postRotate(image.imageInfo.rotationDegrees.toFloat())
//                }
//                val rotatedBitmap = Bitmap.createBitmap(
//                    image.toBitmap(),
//                    0,
//                    0,
//                    image.width,
//                    image.height,
//                    matrix,
//                    true
//                )
//
//                onPhotoTaken(rotatedBitmap)
//            }
//
//            override fun onError(exception: ImageCaptureException) {
//                super.onError(exception)
//                Log.e("Camera", "Couldn't take photo: ", exception)
//            }
//        }
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    PalomadeAppsTheme {
//        ScanScreen()
//    }
//}
