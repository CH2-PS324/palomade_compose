package com.example.palomadeapps.ui.screen.track

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.palomadeapps.R
import com.example.palomadeapps.ui.components.Shipping

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TrackScreen(
    navigate: NavHostController,
//    navigate: () -> Unit
    listState: LazyListState = rememberLazyListState()
) {
    var selectedFilter by remember { mutableStateOf<Filter?>(null) }

    // Daftar filter yang tersedia
    val filters = listOf(
        Filter.all,
        Filter.progress,
        Filter.shipping,
        Filter.end
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header dengan ikon filter
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp),
                text = stringResource(R.string.track_shipping),
                fontSize = 24.sp,
                style = TextStyle(
                    fontSize = 19.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000)
                )
            )

            ElevatedButton(
                modifier = Modifier
                    .padding(end = 0.dp),
                onClick = { /*todo*/ },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff008857)
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .padding(end = 10.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = "Email Icon"
                        )
                    }
                }
                Text(text = stringResource(R.string.add_shipping))
            }
        }

        Divider(
            thickness = 1.dp, color = Color.Black
        )

        // Baris chip untuk menampilkan filter
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 8.dp)
                .selectableGroup()
        ) {
            this.items(filters) { filter ->
                FilterChip(
                    filter = filter,
                    selectedFilter = selectedFilter,
                    onFilterSelected = { selectedFilter = it }
                )
            }
        }

        Text(
            text = stringResource(R.string.proses),
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 20.dp, bottom = 8.dp)
        )

        // Tampilkan daftar item atau konten aplikasi di sini
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 10.dp)
        ) {
            items(10) {
                Shipping(navigate = navigate)
            }
        }
    }
}

@Composable
fun FilterChip(
    filter: Filter,
    selectedFilter: Filter?,
    onFilterSelected: (Filter) -> Unit
) {
    val isSelected = filter == selectedFilter

    Row(
        modifier = Modifier
            .padding(end = 8.dp)
            .selectable(
                selected = isSelected,
                onClick = { onFilterSelected(filter) }
            )
            .background(
                color = if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                },
                shape = CircleShape
            )
            .padding(8.dp)
    ) {

        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(id = filter.stringRes),
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.primary
        )
    }
}

// Enum untuk filter
enum class Filter(val stringRes: Int) {
    all(R.string.all),
    progress(R.string.progress),
    shipping(R.string.shipping),
    end(R.string.end)
}

//@Preview(showBackground = true)
//@Composable
//fun TrackScreenPreview() {
//    TrackScreen({})
//}