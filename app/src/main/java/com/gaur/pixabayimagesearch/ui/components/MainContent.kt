package com.gaur.pixabayimagesearch.ui.components

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.widget.GridLayout
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.gaur.pixabayimagesearch.R
import com.gaur.pixabayimagesearch.network.model.ProductX
import com.gaur.pixabayimagesearch.ui.components.driver.DriverActivity
import com.gaur.pixabayimagesearch.ui.components.single_product.SingleProductViewModel
import java.text.NumberFormat
import java.util.*
import androidx.core.view.WindowCompat

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable

fun MainContent(viewModel: MainViewModel = hiltViewModel(), singleProductModel: SingleProductViewModel = hiltViewModel()) {


    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("SharedRef", Context.MODE_PRIVATE)

    val editor :SharedPreferences.Editor = sharedPreferences.edit()
    editor.apply{
        putBoolean("FIRST_RUN", true)
    }.apply()

    val query: MutableState<String> = remember { mutableStateOf("") }
    val result = viewModel.list.value
    val depotResult = singleProductModel.list.value


    Surface(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.padding(0.dp)) {
           Row(
               Modifier
                   .background(Color.Black)
                   .padding(14.dp)
                   .height(35.dp)

           ) {
               AppBar( leadingIcon = {
                   Icon(

                       Icons.Filled.Search,
                       null,
modifier = Modifier.height(22.dp)
    .width(18.dp)
                   )
               })
           }

                LazyVerticalGrid(cells = GridCells.Fixed(2)) {


                    viewModel.list.value.products?.let {
                        items(it) {
                            MainContentItem(it)
                        }

                    }

                }

            }


        }
    }


//}



@OptIn(ExperimentalFoundationApi::class)
@Composable

fun AppBar(leadingIcon: (@Composable () -> Unit)? = null, ) {

     val CELL_COUNT = 8

     val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(CELL_COUNT) }

    var text by remember { mutableStateOf("Search....") }
    LazyVerticalGrid(cells = GridCells.Fixed(10)) {
        item(span = span) {

           BasicTextField( modifier = Modifier
               .background(
                   MaterialTheme.colors.surface,
                   MaterialTheme.shapes.small,
               )



               .padding(vertical = 4.dp, horizontal = 5.dp),

               value = text,

               onValueChange = {text = it},
               singleLine = true,
               textStyle = LocalTextStyle.current.copy(
                   color = Color.Black,
                   fontSize = 14.sp,

               ),
               decorationBox = { innerTextField ->
                   Row(
                      modifier = Modifier
                          .padding(1.dp)

                   ) {
                       if (leadingIcon != null) leadingIcon()
                       Box(Modifier.weight(1f)) {

                           innerTextField()
                       }

                   }
               }
           )


        }

        val CELL_COUNT_1 = 2

        val span_1: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(CELL_COUNT_1) }
item(span = span_1 ) {

    Box(
        Modifier
            .width(36.dp)
            .height(36.dp),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = { },


            ) {

            Icon( imageVector = Icons.Outlined.ShoppingCart, contentDescription = "", tint = Color.White)
        }

    }



}
}



}



@Composable
fun HomeScreen() {
    Box(Modifier.verticalScroll(rememberScrollState())) {
//        Image(
//            modifier = Modifier
//                .fillMaxWidth()
//                .offset(0.dp, (-30).dp),
//            painter = painterResource(id = R.drawable.bg_main),
//            contentDescription = "Header Background",
//            contentScale = ContentScale.FillWidth
//        )
        Column {
            AppBar(  leadingIcon = {
                Icon(
                    Icons.Filled.Search,
                    null,

                )
            })


        }
    }
}

@Composable
fun MainContentItem(productX: ProductX, singleProductModel: SingleProductViewModel = hiltViewModel()) {
    val context = LocalContext.current



    val sharedPreferences: SharedPreferences = context.getSharedPreferences("SharedRef", Context.MODE_PRIVATE)
    val editor :SharedPreferences.Editor = sharedPreferences.edit()
Column() {
    Card(
        elevation = 0.dp,
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .height(170.dp)


    ) {


//        val context = LocalContext.current
//        val sharedPreferences: SharedPreferences = context.getSharedPreferences("SharedRef", Context.MODE_PRIVATE)

//        val editor :SharedPreferences.Editor = sharedPreferences.edit()
//        editor.apply{
//            putBoolean("FIRST_RUN", true)
//        }.apply()


        Image(
            painter = rememberImagePainter(data = "https://res.cloudinary.com/dhdn7ukv9/image/upload/${productX.img_id}" ),
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = Modifier.clickable(
                onClick = {
                    editor.apply{
                        putInt("TEST", productX.id)
                        putBoolean("FIRST_RUN", true)
                    }.apply()
//               singleProductModel.setSelectedID(productX.id)
//               singleProductModel.getVariationList(productX.id)
                    context.startActivity(
                        Intent(
                            context,
                            DriverActivity::class.java
                        )
                    )
                }
            )
        )

//            modifier = Modifier
//                .width(150.dp)
//                .height(150.dp)
//                .fillMaxWidth()
//                .fillMaxHeight()
//        )
    }
    Text(text = "${ productX.product_name}", fontSize = 12.sp, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.SemiBold,   modifier = Modifier
        .padding(horizontal =  8.dp))
    if(productX.price != 0) {
        Text(text = "₱${productX.price}", fontSize = 12.sp, color = Color.Red, fontWeight = FontWeight.SemiBold,   modifier = Modifier
            .padding(horizontal =  8.dp))
    }
    else {

        val min = NumberFormat.getNumberInstance(Locale.US)
            .format(productX.min)

        val max = NumberFormat.getNumberInstance(Locale.US)
            .format(productX.max)

        Text(text = "₱${min}-₱${max}", fontSize = 12.sp, color = Color.Red, fontWeight = FontWeight.SemiBold,   modifier = Modifier
            .padding(horizontal = 8.dp))
    }
}



}



