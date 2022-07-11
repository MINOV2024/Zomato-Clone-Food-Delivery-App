package com.sanjeet.mealsearchapp.data.repository

import com.sanjeet.mealsearchapp.data.model.MealsDTO
import com.sanjeet.mealsearchapp.data.remote.MealSearchApi
import com.sanjeet.mealsearchapp.doamin.repository.MealSearchRepository

class GetMealListImpl(private val mealSearchApi: MealSearchApi):MealSearchRepository {


    override suspend fun getMealList(s: String): MealsDTO {
        return mealSearchApi.getMealList(s)
    }
}