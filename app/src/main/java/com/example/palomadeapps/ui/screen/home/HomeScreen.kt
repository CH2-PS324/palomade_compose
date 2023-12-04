package com.example.palomadeapps.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.palomadeapps.R
import com.example.palomadeapps.data.di.Injection
import com.example.palomadeapps.ui.components.Card1
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,

//    viewModel: HomeViewModel = viewModel(
////        factory = ViewModelFactory(Injection.provideRepository())
//    ),
//    navigateToDetail: (Long) -> Unit,
){
    HomeContent()
}

@Composable
fun HomeContent(){
    Column {
        Text(text = "ini halaman home")
        
//        Row(
//            Modifier
//                .fillMaxWidth()
//                .padding(top = 48.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Card1(R.drawable.ic_stok_tersedia, stringResource(id = R.string.tersedia))
//            Card1(R.drawable.ic_menipis, stringResource(id = R.string.menipis))
//            Card1(R.drawable.ic_stok_habis, stringResource(id = R.string.habis))
//        }
//
//        Row(
//            Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center
//        ){
//            CardItem2(R.drawable.ic_stok_masuk, stringResource(id = R.string.stok_masuk))
//            CardItem2(R.drawable.ic_stok_keluar, stringResource(id = R.string.stok_keluar))
//        }
//
//        TextItem(desc = stringResource(id = R.string.last_update))

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

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    HomeContent()
}