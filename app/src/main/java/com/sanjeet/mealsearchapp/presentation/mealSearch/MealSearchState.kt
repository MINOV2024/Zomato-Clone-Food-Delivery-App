package com.sanjeet.mealsearchapp.presentation.mealSearch

import com.sanjeet.mealsearchapp.doamin.model.Meal

data class MealSearchState(
    val data:List<Meal>? = null,
    val error:String = "",
    val isLoading:Boolean = false
) {
}