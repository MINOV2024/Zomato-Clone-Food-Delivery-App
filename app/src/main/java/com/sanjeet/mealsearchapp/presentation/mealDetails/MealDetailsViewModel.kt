package com.sanjeet.mealsearchapp.presentation.mealDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjeet.mealsearchapp.common.Resource
import com.sanjeet.mealsearchapp.doamin.usecase.GetMealDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(private val getMealDetailUseCase: GetMealDetailUseCase):ViewModel() {


    private val _mealDetails = MutableStateFlow<MealDeatilsState>(MealDeatilsState())
    val mealDetails :StateFlow<MealDeatilsState> = _mealDetails


    fun getMealDetails(id:String){
        getMealDetailUseCase(id).onEach {
            when(it){
                is Resource.Loading->{
                    _mealDetails.value = MealDeatilsState(isLoading = true)
                }
                is Resource.Error->{
                    _mealDetails.value = MealDeatilsState(error = it.message?:"")
                }
                is Resource.Success->{
                    _mealDetails.value = MealDeatilsState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}