package com.example.palomadeapps.model

data class UserModel(
    val id : String,
    val name : String,
    val token : String,
    val role : String,
    val isLogin : Boolean = false
)