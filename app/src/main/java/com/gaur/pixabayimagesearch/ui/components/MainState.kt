package com.gaur.pixabayimagesearch.ui.components

import com.gaur.pixabayimagesearch.network.model.Hit


data class MainState(
    val isLoading:Boolean=false,
    val data:List<Hit> = emptyList(),
    val error:String="",
    val total: Int = 0,


)
