package com.example.palomadeapps.ui.screen.welcome

import com.example.palomadeapps.R

class OnBoardingItems(
    val image: Int,
    val title: Int,
    val desc: Int
) {
    companion object{
        fun getData(): List<OnBoardingItems>{
            return listOf(
                OnBoardingItems(R.drawable.baner1, R.string.titleOnboard1, R.string.titleLogin),
                OnBoardingItems(R.drawable.baner2, R.string.titleLogin, R.string.titleLogin),
                OnBoardingItems(R.drawable.ic_cameraa, R.string.titleLogin, R.string.titleLogin)
            )
        }
    }
}