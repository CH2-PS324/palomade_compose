package com.example.palomadeapps.data.di

import android.content.Context
import com.example.palomadeapps.api.ApiConfig
import com.example.palomadeapps.data.PaloRepository
import com.example.palomadeapps.data.PredictionRepository
import com.example.palomadeapps.data.pref.UserPref
import com.example.palomadeapps.data.pref.dataStore


import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): PaloRepository {
        val pref = UserPref.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return PaloRepository.getInstance(pref, apiService)
    }

    fun providePrediction(context: Context): PredictionRepository {
        val pref = UserPref.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val predictionService = ApiConfig.getPredictionService(user.token)
        return PredictionRepository.getInstance(context, predictionService)
    }
}