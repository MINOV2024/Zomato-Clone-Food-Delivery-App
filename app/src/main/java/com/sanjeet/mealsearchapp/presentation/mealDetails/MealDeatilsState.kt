package com.sanjeet.mealsearchapp.presentation.mealDetails

import com.sanjeet.mealsearchapp.doamin.model.Meal
import com.sanjeet.mealsearchapp.doamin.model.MealDetails

data class MealDeatilsState(
    val data:MealDetails? = null,
    val error:String = "",
    val isLoading:Boolean = false
) {
}