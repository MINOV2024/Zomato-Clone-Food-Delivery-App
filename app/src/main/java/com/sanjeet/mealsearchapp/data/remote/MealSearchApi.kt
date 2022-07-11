package com.sanjeet.mealsearchapp.data.remote

import com.sanjeet.mealsearchapp.data.model.MealDTO
import com.sanjeet.mealsearchapp.data.model.MealsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface MealSearchApi {


    @GET("api/json/v1/1/search.php")
    suspend fun getMealList(@Query("s") s:String):MealsDTO

    @GET("api/json/v1/1/lookup.php")
    suspend fun getMealDetailById(@Query("i") i:String):MealsDTO
}