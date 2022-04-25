package com.gaur.pixabayimagesearch.ui.components.single_product

import com.gaur.pixabayimagesearch.network.ApiService
import com.gaur.pixabayimagesearch.network.model.SingleProduct
import com.gaur.pixabayimagesearch.util.Resource
import java.lang.Exception
import javax.inject.Inject

class SingleProductRepository @Inject constructor(private val apiService:ApiService) {
    suspend fun getSingleProduct():Resource<SingleProduct>{
        return  try{

            val result = apiService.getSingleProducts()
            Resource.Success(data = result)
        }catch (e:Exception){
            Resource.Error(message = e.message.toString())
        }
    }

}