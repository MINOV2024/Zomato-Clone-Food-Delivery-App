package com.sanjeet.mealsearchapp.hilt

import com.sanjeet.mealsearchapp.common.Constants
import com.sanjeet.mealsearchapp.data.remote.MealSearchApi
import com.sanjeet.mealsearchapp.data.repository.GetMealDetailsImpl
import com.sanjeet.mealsearchapp.data.repository.GetMealListImpl
import com.sanjeet.mealsearchapp.doamin.repository.GetMealDetailsRepository
import com.sanjeet.mealsearchapp.doamin.repository.MealSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Provides
    @Singleton
    fun provideMealSearchApi(): MealSearchApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MealSearchApi::class.java)
    }

    @Provides
    fun provideMealSearchRepository(mealSearchApi: MealSearchApi):MealSearchRepository{
        return GetMealListImpl(mealSearchApi)
    }

    @Provides
    fun provideMealDetailsRepository(mealSearchApi: MealSearchApi):GetMealDetailsRepository{
        return GetMealDetailsImpl(mealSearchApi)
    }

}