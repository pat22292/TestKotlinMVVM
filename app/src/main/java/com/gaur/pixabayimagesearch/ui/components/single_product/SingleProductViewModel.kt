package com.gaur.pixabayimagesearch.ui.components.single_product

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaur.pixabayimagesearch.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleProductViewModel @Inject constructor(private val singleProductRepository: SingleProductRepository) : ViewModel() {
    val list: MutableState<SingleProductState> = mutableStateOf(SingleProductState())


    fun getVariationList()=viewModelScope.launch{

        Log.d("Depot", "Damn It!")
        list.value = SingleProductState(isLoading = true)
        try{
            val result = singleProductRepository.getSingleProduct()
            when(result){
                is Resource.Error->{
                    list.value = SingleProductState(error = "Something went wrong")
                }
                is Resource.Success->{

                    result.data?.let {
                        list.value = SingleProductState(store_branch = it.store_branches, variation = it.variations)
                        Log.d("Depot", "${it.store_name}")
                        Log.d("Depot", "${it.variations}")
                    }
                }
            }
        }catch (e:Exception){
            list.value = SingleProductState(error = "Something went wrong")
            Log.d("Depot", "Something went wrong")
        }





    }
}