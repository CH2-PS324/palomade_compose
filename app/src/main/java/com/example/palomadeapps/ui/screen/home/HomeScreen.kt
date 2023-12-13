package com.example.palomadeapps.ui.screen.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.palomadeapps.R
import com.example.palomadeapps.ViewModelFactory
import com.example.palomadeapps.data.di.Injection
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.palomadeapps.ui.components.RewardItem

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    context: Context = LocalContext.current,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    listState: LazyListState = rememberLazyListState(),
    navigateToDetail: (Long) -> Unit,
){
    val groupedHeroes by viewModel.groupedHeroes.collectAsState()
    val sessionData by viewModel.getSession().observeAsState()


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1000.dp)
                    .padding(bottom = 30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Card(
                    modifier = Modifier
                        .height(130.dp)
                        .width(350.dp)
                        .padding(top = 25.dp),
                    elevation = CardDefaults.cardElevation(10.dp),
                    //                colors =
                ) {
                    Row (
                        Modifier
                            .padding(start = 20.dp, top = 20.dp)
                            .height(21.dp),
                        horizontalArrangement = Arrangement.Start
                    ){
                        Text(
                            text = "Selamat Datang,",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000)
                            )
                        )
                    }
                    Row (
                        Modifier
                            .padding(start = 20.dp)
                            .height(26.dp),
                        horizontalArrangement = Arrangement.Start
                    ){
                        sessionData?.let {
                            Text(
                                it.name,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 17.sp,
                            )
                        }
                    }
                    Row (
                        Modifier
                            .padding(start = 20.dp)
                            .height(21.dp),
                        horizontalArrangement = Arrangement.Start
                    ){
                        sessionData?.let {
                            Text(text = it.role,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 17.sp,
                            )
                        }
                    }
                }

                Card(
                    modifier = Modifier
                        .height(130.dp)
                        .width(350.dp)
                        .padding(top = 25.dp),
                    elevation = CardDefaults.cardElevation(10.dp),
                    //                colors =
                ) {
                    Row (
                        modifier = Modifier.fillMaxHeight(1f)
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxHeight(1f)

                        ){
                            Row (
                                Modifier
                                    .padding(start = 20.dp, top = 25.dp)
                                    .height(21.dp),
                                horizontalArrangement = Arrangement.Start
                            ){
                                Text(
                                    text = "Jumlah Scan Hari ini",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF000000)
                                    )
                                )
                            }
                            Row (
                                Modifier
                                    .padding(start = 20.dp, top = 10.dp)
                                    .height(21.dp),
                                horizontalArrangement = Arrangement.Start
                            ){
                                Text(
                                    text = "Sisah Scan 68",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF000000)
                                    )
                                )
                            }
                        }
                        Column (
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .fillMaxHeight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(
                                modifier = Modifier.padding(top = 10.dp),

                                text = "30",
                                style = TextStyle(
                                    fontSize = 58.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF000000)
                                )
                            )
                        }
                    }
                }
            }


            Column {
                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(bottom = 10.dp)
                ){
                    groupedHeroes.forEach { (initial, heroess) ->
                        items(heroess, key = { it.id }) { hero ->
                            RewardItem(
                                image = hero.image,
                                title = hero.title,
                                description = hero.description,
                                modifier = Modifier
                                    .clickable {
                                        navigateToDetail(hero.id)
                                    }
                                    .fillMaxWidth()
                                    .animateItemPlacement(tween(durationMillis = 100))
                            )
                        }
                    }
                }
            }
        }

}

@OptIn(ExperimentalFoundationApi::class)


//@Composable
//fun HeroListItem(
//    name: String,
//    photoUrl: String,
//    modifier: Modifier = Modifier
//) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = modifier.clickable {}
//    ) {
//        A(model = photoUrl, contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .padding(8.dp)
//                .size(50.dp)
//                .clip(CircleShape)
//        )
//        Text(
//            text = name,
//            fontWeight = FontWeight.Medium,
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f)
//                .padding(start = 16.dp)
//        )
//    }

@Composable
fun ScrollToTopButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    FilledIconButton(onClick = onClick,
        modifier = modifier) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = stringResource(R.string.scroll_to_top),
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomePreview(){
//    HomeScreen()
//}