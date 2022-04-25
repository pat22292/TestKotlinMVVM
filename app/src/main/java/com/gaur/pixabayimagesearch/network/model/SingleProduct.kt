package com.gaur.pixabayimagesearch.network.model

data class SingleProduct(
    val description: String,
    val id: Int,
    val img_gallery: List<String>,
    val img_id: String,
    val max: Int,
    val min: Int,
    val package_height: String,
    val package_length: String,
    val package_weight: String,
    val package_width: String,
    val price: Int,
    val product_name: String,
    val store_branches: List<StoreBranch>,
    val store_name: String,
    val variation_name: String,
    val variation_option_name: String,
    val variations: List<Variation>
)