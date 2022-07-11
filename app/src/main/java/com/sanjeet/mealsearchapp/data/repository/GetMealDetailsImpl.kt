package com.sanjeet.mealsearchapp.data.repository

import com.sanjeet.mealsearchapp.data.model.MealsDTO
import com.sanjeet.mealsearchapp.data.remote.MealSearchApi
import com.sanjeet.mealsearchapp.doamin.repository.GetMealDetailsRepository
import com.sanjeet.mealsearchapp.doamin.repository.MealSearchRepository

class GetMealDetailsImpl(private val mealSearchApi: MealSearchApi): GetMealDetailsRepository{


    override suspend fun getMealDetails(id: String): MealsDTO {
        return mealSearchApi.getMealDetailById(id)
    }
}