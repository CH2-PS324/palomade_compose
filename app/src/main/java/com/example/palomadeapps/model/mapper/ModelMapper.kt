package com.example.palomadeapps.model.mapper

import com.example.palomadeapps.model.Prediction
import com.example.palomadeapps.response.Prediction.PredictResponse

object ModelMapper {
    fun toPrediction(dto: PredictResponse): Prediction {
        return Prediction(percentage = dto.status?.data?.precentase, classType = dto.status?.data?.classType)
    }
}