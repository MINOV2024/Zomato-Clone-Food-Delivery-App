package com.sanjeet.mealsearchapp.doamin.repository

import com.sanjeet.mealsearchapp.data.model.MealsDTO

interface GetMealDetailsRepository {

    suspend fun getMealDetails(id:String):MealsDTO
}