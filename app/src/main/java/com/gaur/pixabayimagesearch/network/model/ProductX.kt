package com.gaur.pixabayimagesearch.network.model

data class ProductX(
    val created_at: String,
    val description: String,
    val id: Int,
    val img_id: String,
    val max: Int,
    val min: Int,
    val package_height: String,
    val package_length: String,
    val package_weight: String,
    val package_width: String,
    val price: Int,
    val product_name: String,
    val store_name: String,
    val updated_at: String,
    val variation_name: String,
    val variation_option_name: String
)