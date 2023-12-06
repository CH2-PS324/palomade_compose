package com.example.palomadeapps.data.pref

data class UserModel(
    val id : String,
    val name : String,
    val token : String,
    val isLogin : Boolean = false
)