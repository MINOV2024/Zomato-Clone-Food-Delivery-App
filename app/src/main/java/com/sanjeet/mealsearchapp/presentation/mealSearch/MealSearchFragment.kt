package com.sanjeet.mealsearchapp.presentation.mealSearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sanjeet.mealsearchapp.R
import com.sanjeet.mealsearchapp.databinding.FragmentMealSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealSearchFragment : Fragment() {


    private val mealSearchAdapter = MealSearchAdapter()
    private var _binding:FragmentMealSearchBinding?=null
    val binding:FragmentMealSearchBinding
    get() = _binding!!

    private val mealSearchViewModel:MealSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealSearchBinding.inflate(inflater,container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchMeal.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    mealSearchViewModel.searchMealList(it)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

        binding.rvMeal.apply {
            adapter =mealSearchAdapter
        }

        lifecycle.coroutineScope.launchWhenCreated {
            mealSearchViewModel.mealSearchState.collect{
                if (it.isLoading){
                    binding.progressBar.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()){
                    binding.progressBar.visibility = View.GONE
                }
                it.data?.let {
                    binding.progressBar.visibility = View.GONE
                    mealSearchAdapter.setContentList(it.toMutableList())
                }
            }
        }

        mealSearchAdapter.itemClickListener {
            findNavController().navigate(MealSearchFragmentDirections.actionMealSearchFragmentToMealDetailsFragment2(mealId = it.mealId))
        }

    }
}