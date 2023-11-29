package com.example.palomadeapps.ui.components

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

@SuppressLint("ModifierParameter")
@Composable
fun TxtItem (
    desc: String,
    fontWeight: FontWeight? = null,
    fontSize : TextUnit = TextUnit.Unspecified,
    modifier: Modifier = Modifier
){
    Text(
        fontSize = fontSize,
        fontWeight = fontWeight,
        text = desc,
        modifier = modifier
    )
}