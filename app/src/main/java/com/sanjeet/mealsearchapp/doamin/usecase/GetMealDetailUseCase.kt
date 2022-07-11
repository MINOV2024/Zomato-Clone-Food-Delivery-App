package com.sanjeet.mealsearchapp.doamin.usecase

import android.telephony.mbms.MbmsErrors
import com.sanjeet.mealsearchapp.common.Resource
import com.sanjeet.mealsearchapp.data.model.toDomainMeal
import com.sanjeet.mealsearchapp.data.model.toDomainMealDetails
import com.sanjeet.mealsearchapp.doamin.model.Meal
import com.sanjeet.mealsearchapp.doamin.model.MealDetails
import com.sanjeet.mealsearchapp.doamin.repository.GetMealDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealDetailUseCase @Inject constructor(private val repository: GetMealDetailsRepository) {


    operator fun invoke(id:String):Flow<Resource<MealDetails>> = flow {
        try {
            emit(Resource.Loading())

            val response = repository.getMealDetails(id).meals[0].toDomainMealDetails()
            emit(Resource.Success(data = response))

        }catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage?:"Unknown Error"))
        }catch (e: IOException){
            emit(Resource.Error(message = e.localizedMessage?:"Check your internet connection"))
        }catch (e:Exception){
            emit(Resource.Error(message = e.localizedMessage?:"UnExpected Error"))
        }
    }
}