package com.example.palomadeapps.ui.screen.FAQ

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.palomadeapps.R
import com.example.palomadeapps.ui.theme.IconColor
import com.example.palomadeapps.ui.theme.IconColor2
import com.example.palomadeapps.ui.theme.Shapes
import com.example.palomadeapps.ui.theme.poppinsFontFamily


@Composable
fun FAQScreen(
    navigate: NavHostController
) {
    Column {
        MainFAQToolbar()
        FAQSection()
    }

    BottomText(onBackClick = { navigate.popBackStack() })
}

@Composable
fun BottomText(
    onBackClick: () -> Unit
) {
    Box (
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.padding(10.dp)
    ){
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.back),
            modifier = Modifier
                .padding(start = 7.dp, top = 21.dp)
                .clickable { onBackClick() }
        )
    }
}


@Composable
fun FAQSection() {
    Column() {
        ExpandableFAQCard(
            "What is Malware ?", "Malware is intrusive software that is " +
                    "designed to damage and destroy computers and computer systems. " +
                    "Malware is a contraction for “malicious software" +
                    "."
        )
        ExpandableFAQCard(
            "How do I contact customer service ?", "You may write us at abcd@ab.com" +
                    " with your query/concern and we'll get back to you as soon as possible."
        )
        ExpandableFAQCard(
            "What will be the duration of the service ?",
            "The members can be admitted under the policy at well defined date for full " +
                    "Cover term (1 Year) from their scheme joining date."
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableFAQCard(title: String, description: String) {
    var expandedState by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 400,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = Shapes.small,
        onClick = {

        },
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 0.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    modifier = Modifier.weight(6f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines =if(!expandedState) 1 else 3,
                    overflow =if(!expandedState) TextOverflow.Ellipsis else TextOverflow.Visible,
                    fontFamily = poppinsFontFamily,
                )
                IconButton(
                    onClick = { expandedState = !expandedState },
                    modifier = Modifier
                        .weight(1f)
                        .alpha(.5f),
                ) {
                    if (expandedState)
                        Icon(
                            painterResource(id = R.drawable.ic_arrow_up), contentDescription = "",
                            modifier = Modifier.size(20.dp),
                            tint = IconColor2
                        )
                    else
                        Icon(
                            painterResource(id = R.drawable.ic_arrow_down), contentDescription = "",
                            modifier = Modifier.size(20.dp),
                            tint = IconColor2
                        )
                }
            }

            if (expandedState) {
                Text(
                    text = description,
                    modifier = Modifier.padding(bottom = 40.dp),
                    fontSize = 14.sp,
                    color = IconColor2,
                    lineHeight = 18.sp
                )
            }

        }

    }
}

@Composable
fun MainFAQToolbar() {
    Text(
        text = "Frequently   Asked   Questions",
        color = IconColor,
        fontFamily = poppinsFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .padding(horizontal = 10.dp, vertical = 30.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
@Preview
fun FAQScreenPreview() {
    FAQScreen(navigate = NavHostController(LocalContext.current))
}