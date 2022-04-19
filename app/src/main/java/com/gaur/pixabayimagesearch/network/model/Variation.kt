package com.gaur.pixabayimagesearch.network.model

data class Variation(
    val variation_id: Int,
    val variation_image: String,
    val variation_name: String,
    val variation_option: Any,
    val variation_price: Int
)