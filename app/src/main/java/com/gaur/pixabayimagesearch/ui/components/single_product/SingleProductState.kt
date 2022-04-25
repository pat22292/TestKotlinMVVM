package com.gaur.pixabayimagesearch.ui.components.single_product


import com.gaur.pixabayimagesearch.network.model.StoreBranch
import com.gaur.pixabayimagesearch.network.model.Variation


data class SingleProductState (
    val isLoading:Boolean=false,
    val store_branch:List<StoreBranch> = emptyList(),
    val variation:List<Variation> = emptyList(),
    val error:String="",
    val total: Int = 0,
)