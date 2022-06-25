package com.gaur.pixabayimagesearch.ui.components

import com.gaur.pixabayimagesearch.network.ApiService
import com.gaur.pixabayimagesearch.network.model.PixabayResponse
import com.gaur.pixabayimagesearch.network.model.Product
import com.gaur.pixabayimagesearch.network.model.SingleProduct
import com.gaur.pixabayimagesearch.util.Constant
import com.gaur.pixabayimagesearch.util.Resource
import java.lang.Exception
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService:ApiService) {


    suspend fun getQueryItems(q:String):Resource<PixabayResponse>{
      return  try{

            val result = apiService.getQueryImages(query = q, apiKey = Constant.KEY, imageType = "photo")
            Resource.Success(data = result)
        }catch (e:Exception){
            Resource.Error(message = e.message.toString())
        }
    }

    suspend fun getProducts():Resource<Product>{
        return  try{

            val result = apiService.getProducts()
            Resource.Success(data = result)
        }catch (e:Exception){
            Resource.Error(message = e.message.toString())
        }
    }

    

}