package com.gaur.pixabayimagesearch.network

import com.gaur.pixabayimagesearch.network.model.PixabayResponse
import com.gaur.pixabayimagesearch.network.model.Product
import com.gaur.pixabayimagesearch.network.model.SingleProduct

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path


interface ApiService {

    @GET("api/")
    suspend fun getQueryImages(
        @Query("q") query:String,
        @Query("key") apiKey:String,
        @Query("image_type") imageType:String
    ):PixabayResponse


    @GET("product/{id}")
    suspend fun getSingleProducts(
        @Path(value = "id") product_id: Int
    ):SingleProduct

    @GET("view-products")
    suspend fun getProducts():Product
}