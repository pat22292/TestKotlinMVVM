package com.gaur.pixabayimagesearch.ui.components.single_product


import com.gaur.pixabayimagesearch.network.model.StoreBranch
import com.gaur.pixabayimagesearch.network.model.Variation


data class SingleProductState (

    val isLoading:Boolean=false,
    val storeName: String="",
    val description: String="",
    val product_name:String="",
    val price:Int=0,
    val min:Int=0,
    val max:Int=0,
    val store_branch:List<StoreBranch> = emptyList(),
    val variation:List<Variation> = emptyList(),
    val error:String="",
    val total: Int = 0,
    val img_id: String="",
    val totalImages: Int = 0

)