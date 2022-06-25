package com.gaur.pixabayimagesearch.ui.components

import com.gaur.pixabayimagesearch.network.model.Hit
import com.gaur.pixabayimagesearch.network.model.ProductX
import com.gaur.pixabayimagesearch.network.model.StoreBranch
import com.gaur.pixabayimagesearch.network.model.Variation


data class MainState(
    val products:List<ProductX> = emptyList(),
    val isLoading:Boolean=false,
    val data:List<Hit> = emptyList(),
    val store_branch:List<StoreBranch> = emptyList(),
    val variation:List<Variation> = emptyList(),
    val error:String="",
    val total: Int = 0,

)
