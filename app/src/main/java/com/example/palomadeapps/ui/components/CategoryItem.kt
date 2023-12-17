package com.example.palomadeapps.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.palomadeapps.ui.theme.PalomadeAppsTheme

@Composable
fun CategoryItem(
    category: String,
    onExecuteSearch: (String) -> Unit,
){
    Surface(
        modifier = Modifier.padding(end = 8.dp),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(modifier = Modifier
            .clickable(
                onClick = {
                    onExecuteSearch(category)
                }
            )
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun JetHeroesAppPreview() {
//    PalomadeAppsTheme {
//       CategoryItem()
//    }
//}