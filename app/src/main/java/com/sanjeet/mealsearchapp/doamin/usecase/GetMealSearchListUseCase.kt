package com.sanjeet.mealsearchapp.doamin.usecase

import com.sanjeet.mealsearchapp.common.Resource
import com.sanjeet.mealsearchapp.data.model.toDomainMeal
import com.sanjeet.mealsearchapp.doamin.model.Meal
import com.sanjeet.mealsearchapp.doamin.repository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealSearchListUseCase @Inject constructor(private val repository: MealSearchRepository) {

    operator fun invoke(s:String): Flow<Resource<List<Meal>>> = flow {
        try {
            emit(Resource.Loading())

            val response = repository.getMealList(s)

            val list = if (response.meals.isNullOrEmpty()) emptyList<Meal>() else response.meals.map { it.toDomainMeal()}

            emit(Resource.Success(data = list))

        }catch (e:HttpException){
            emit(Resource.Error(message = e.localizedMessage?:"Unknown Error"))
        }catch (e:IOException){
            emit(Resource.Error(message = e.localizedMessage?:"Check your internet connection"))
        }catch (e:Exception){
            emit(Resource.Error(message = e.localizedMessage?:"UnExpected Error"))
        }
    }
}