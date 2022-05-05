package com.gaur.pixabayimagesearch.ui.components.single_product

import com.gaur.pixabayimagesearch.network.ApiService
import com.gaur.pixabayimagesearch.network.model.SingleProduct
import com.gaur.pixabayimagesearch.util.Resource
import java.lang.Exception
import javax.inject.Inject

class SingleProductRepository @Inject constructor(private val depotApiService:ApiService) {
    suspend fun getSingleProduct(id: Int):Resource<SingleProduct>{
        return  try{

            val result = depotApiService.getSingleProducts(id)
            Resource.Success(data = result)
        }catch (e:Exception){
            Resource.Error(message = e.message.toString())
        }
    }

}