package com.example.palomadeapps.ui.screen.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.palomadeapps.R
import com.example.palomadeapps.ViewModelFactory
import com.example.palomadeapps.data.di.Injection
import com.example.palomadeapps.ui.components.RewardItem
import kotlinx.coroutines.delay

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
) {
    val groupedHeroes by viewModel.groupedHeroes.collectAsState()
    val sessionData by viewModel.getSession().observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .background(color = colorResource(id = R.color.primary)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .width(1000.dp)
                .padding(bottom = 20.dp, top = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = Modifier
                    .height(85 .dp)
                    .width(350.dp)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFD5D5D5),
                        shape = RoundedCornerShape(size = 8.dp)
                    ),
            ) {
                Row(
                    Modifier
                        .padding(start = 20.dp, top = 20.dp)
                        .height(21.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
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
                Row(
                    Modifier
                        .padding(start = 20.dp)
                        .height(26.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    sessionData?.let {
                        Text(
                            it.name,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            color = Color(0xFF000000),
                            fontSize = 17.sp,
                        )
                    }
                }
            }
        }

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
                    .height(113.dp)
                    .width(350.dp)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFD5D5D5),
                        shape = RoundedCornerShape(size = 8.dp)
                    ),
                //                colors =
            ) {
                Row(
                    modifier = Modifier.fillMaxHeight(1f)
                ) {
                    Column(
                        modifier = Modifier

                            .fillMaxHeight(1f)

                    ) {
                        Row(
                            Modifier
                                .padding(start = 20.dp, top = 25.dp)
                                .height(21.dp),
                            horizontalArrangement = Arrangement.Start
                        ) {
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
                        Row(
                            Modifier
                                .padding(start = 20.dp, top = 10.dp)
                                .height(21.dp),
                            horizontalArrangement = Arrangement.Start
                        ) {
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .fillMaxHeight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 10.dp, start = 28.dp),

                            text = "32",
                            style = TextStyle(
                                fontSize = 58.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000)
                            )
                        )
                    }
                }
            }
        }


        Column {
            var isLoading by remember {
                mutableStateOf(true)
            }
            LaunchedEffect(key1 = true) {
                delay(10000)
                isLoading = false
            }
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(bottom = 10.dp)
            ) {
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