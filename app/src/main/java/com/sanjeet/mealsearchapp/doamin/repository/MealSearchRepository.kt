package com.sanjeet.mealsearchapp.doamin.repository

import com.sanjeet.mealsearchapp.data.model.MealsDTO

interface MealSearchRepository {

    suspend fun getMealList(s:String):MealsDTO
}