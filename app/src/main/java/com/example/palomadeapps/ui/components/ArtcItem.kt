package com.example.palomadeapps.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.palomadeapps.R
import com.example.palomadeapps.ui.theme.Shapes

@Composable
fun RewardItem(
    image: Int,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(1f)
            .padding(bottom = 13.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f)
                .padding(start = 26.dp, end = 26.dp)
                .clip(Shapes.medium)
        ){
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(110.dp)
                    .width(110.dp)
                    .clip(RoundedCornerShape(25.dp))
            )
            Column (
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(start = 8.dp)
            ){
                Text(
                    text = title.substring(0, 30) + "...",
                    maxLines = 1,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                    ),
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = description.substring(0, 82) + "..." + "  Read More",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun RewardItemPreview() {
//    PalomadeAppsTheme {
//        RewardItem(R.drawable.articel3, "Artikel", "Artikel")
//    }
//}