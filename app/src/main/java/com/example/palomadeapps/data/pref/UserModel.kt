package com.example.palomadeapps.data.pref

data class UserModel(
    val username : String,
    val token : String,
    val name : String,
    val isLogin : Boolean = false
)