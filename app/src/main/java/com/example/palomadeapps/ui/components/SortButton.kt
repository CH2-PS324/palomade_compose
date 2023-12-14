package com.example.palomadeapps.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.palomadeapps.R
import com.example.palomadeapps.ui.theme.PalomadeAppsTheme

@Composable
fun SortButton(
modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = { /*TODO*/ }) {
        Text(text = "sort")
    }
}
//@Composable
//@Preview(showBackground = true)
//fun SortBtn() {
//    PalomadeAppsTheme {
//        SortButton()
//    }
//}