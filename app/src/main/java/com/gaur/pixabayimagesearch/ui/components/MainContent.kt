package com.gaur.pixabayimagesearch.ui.components

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.gaur.pixabayimagesearch.network.model.Hit
import com.gaur.pixabayimagesearch.network.model.Variation
import com.gaur.pixabayimagesearch.ui.components.single_product.SingleProductViewModel



@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun MainContent(viewModel: MainViewModel = hiltViewModel(), singleProductModel: SingleProductViewModel = hiltViewModel()) {



    val query: MutableState<String> = remember { mutableStateOf("") }
    val result = viewModel.list.value
    val depotResult = singleProductModel.list.value
    Surface(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.padding(8.dp)) {

//            AnimatableSample()
//            OutlinedTextField(value = query.value, onValueChange = {
//                query.value = it
//                viewModel.getImageList(query.value)
//
//            }, enabled = true,
//                singleLine = true,
//                leadingIcon = {
//                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
//                },
//                label = { Text(text = "Search here...") },
//                modifier=Modifier.fillMaxWidth()
//
//            )
//            Button(
//
//                onClick = { singleProductModel.getVariationList(4) }
//            ) {
//                Text(text = "Palkups")
//            }
//            Text(text = "${depotResult.product_name}")
//            AnimatableSample()
//            Text(text = "${depotResult.variation.count()}")
//            if (result.isLoading) {
//                Log.d("TAG", "MainContent: in the loading")
//                Box(modifier = Modifier
//                    .fillMaxSize()) {
//                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//                }
//            }

//            if (depotResult.store_branch.isNotEmpty()) {
//                Log.d("Depot", "${depotResult.variation}")
//            }
//            if (result.error.isNotBlank()) {
//                Log.d("TAG", "MainContent: ${result.error}")
//                Box(modifier = Modifier
//                    .fillMaxSize()) {
//                    Text(
//                        modifier = Modifier.align(Alignment.Center),
//                        text = viewModel.list.value.error
//                    )
//                }
//            }
            var veriationIndex by remember { mutableStateOf(0) }

//            Text(text = "Founded Image/s: ${viewModel.list.value.total.toString()} items")
            if (depotResult.variation.isNotEmpty()) {

                Card(
                    modifier = Modifier
//                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(250.dp)
                ) {
                    Image(
                        painter = rememberImagePainter(data = "https://res.cloudinary.com/dhdn7ukv9/image/upload/${singleProductModel.list.value.variation[veriationIndex].variation_image}" ),
                        contentScale = ContentScale.Fit,
                        contentDescription = null,
//            modifier = Modifier
//                .width(150.dp)
//                .height(150.dp)
//                .fillMaxWidth()
//                .fillMaxHeight()
                    )
                }
               Row(modifier = Modifier.padding(8.dp)) {
                   Button(
                       onClick =
                       {
                           veriationIndex = 0
                       },
                       colors = ButtonDefaults.buttonColors(Color.Blue),
                       modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp)
                   ) {
                       Text(text = "Blue", color = Color.White)

                   }
                   Button(
                       onClick =
                       {
                           veriationIndex = 1
                       },
                       colors = ButtonDefaults.buttonColors(Color.Black)
                   ) {
                       Text(text = "Black", color = Color.White)

                   }
                   Button(
                       onClick =
                       {
                           veriationIndex = 2
                       },
                       colors = ButtonDefaults.buttonColors(Color.Red)
                   ) {
                       Text(text = "Pink", color = Color.White)

                   }
               }
                LazyVerticalGrid(cells = GridCells.Fixed(2)) {
//                    Log.d("TAG", "MainContent: Your Token")


//                    viewModel.list.value.total?.let {
//                        items(it) {

//                        }
//                    }

//                   Log.d("SHT", viewModel.list.value.total.toString())
//                    singleProductModel.list.value.variation?.let {
//                        items(it) {
//                            MainContentItem(it)
//                        }
//
//                    }

                }

            }


        }
    }


}



@Composable
fun MainContentItem(variation: Variation) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = "https://res.cloudinary.com/dhdn7ukv9/image/upload/${variation.variation_image}" ),
            contentScale = ContentScale.Fit,
            contentDescription = null,
//            modifier = Modifier
//                .width(150.dp)
//                .height(150.dp)
//                .fillMaxWidth()
//                .fillMaxHeight()
        )
    }


}

@Composable
private fun AnimatableSample() {
    var isAnimated by remember { mutableStateOf(false) }

    val color = remember { Animatable(Color.DarkGray) }

    // animate to green/red based on `button click`
    LaunchedEffect(isAnimated) {
        color.animateTo(if (isAnimated) Color.Green else Color.Red, animationSpec = tween(2000))
    }

    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .background(color.value)

    )
    Button(
        onClick = { isAnimated = !isAnimated },
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Text(text = "Animate Color")
    }
}

@Composable
private fun testFade() {
    var visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically {
            // Slide in from 40 dp from the top.
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Text("Hello",
            Modifier
                .fillMaxWidth()
                .height(200.dp))
    }
}


