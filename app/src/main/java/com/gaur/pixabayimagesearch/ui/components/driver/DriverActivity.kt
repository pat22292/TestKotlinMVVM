package com.gaur.pixabayimagesearch.ui.components.driver

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.gaur.pixabayimagesearch.MainActivity
import com.gaur.pixabayimagesearch.ui.components.MainViewModel
import com.gaur.pixabayimagesearch.ui.components.driver.ui.theme.PixabayImageSearchTheme
import com.gaur.pixabayimagesearch.ui.components.single_product.SingleProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Math.abs
import java.text.NumberFormat
import java.util.*
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask
import kotlin.math.roundToInt
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.toSize
import coil.size.Scale
import com.gaur.pixabayimagesearch.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@AndroidEntryPoint
class DriverActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)


        setContent {
            val sharedPreferences: SharedPreferences = getSharedPreferences("SharedRef", Context.MODE_PRIVATE)
            val saved: Int = sharedPreferences.getInt("TEST", 3)
            val run: Boolean? = sharedPreferences.getBoolean("FIRST_RUN", true)
            val editor :SharedPreferences.Editor = sharedPreferences.edit()

            window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.insetsController?.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS)
            } else {
                @Suppress("DEPRECATION")
                window.decorView.systemUiVisibility = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                } else {
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }

            }
//            val systemUiController: SystemUiController = rememberSystemUiController()
//
//            systemUiController.isStatusBarVisible = false // Status bar
            WindowCompat.setDecorFitsSystemWindows(window, false)

            val mainViewModel by viewModels<SingleProductViewModel>()



            val context = LocalContext.current




            if (run == true)
            {
                mainViewModel.getVariationList(saved)
                // Do your unique code magic here
                val editor :SharedPreferences.Editor = sharedPreferences.edit()
                editor.apply{
                    putBoolean("FIRST_RUN", false)
                }.apply()

            }else{
                //this will repeat everytime
            }
//            singleProductViewModel.getAllMovies()

            PixabayImageSearchTheme {
//                var direction = remember {mutableStateOf("")}
//                var currentOffset by remember {mutableStateOf(0f)}
//                var offset by remember { mutableStateOf(0f) }

                val galleries = mutableListOf(mainViewModel.list.value.img_id)
                val depotResult = mainViewModel.list.value

                if (depotResult.variation.isNotEmpty()) {
                    for (image in mainViewModel.list.value.variation) {
                        galleries.add(image.variation_image)

                    }
                    Log.d("gal", "$galleries")
                }

                    val state = rememberPagerState()
                    Column {
                        SliderView(state, mainViewModel)
//                        Spacer(modifier = Modifier.padding(4.dp))
//                        DotsIndicator(
//                            totalDots = galleries.count(),
//                            selectedIndex = state.currentPage
//                        )
//                        LazyColumn() {
//                            items(mainViewModel.list.value.totalImages) { item ->
//                                MovieCard(movie = item)
//                            }
//                        }
                    }
                    LaunchedEffect(key1 = state.currentPage) {
                        delay(10000)
                        var newPosition = state.currentPage + 1
                        if (newPosition > mainViewModel.list.value.variation.count() - 1) newPosition = 0
                        // scrolling to the new position.
                        state.animateScrollToPage(newPosition)
                    }


//                Text("$saved")
                // A surface container using the 'background' color from the theme
//                GestureTest()
//           Greeting()
//                TestScreen()
//                if (result.isLoading) {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                    ) {
//                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//                    }
//                }
            }
        }
    }

}

@Composable
fun GestureTest() {
    Box(modifier = Modifier.fillMaxSize()) {
        var offsetX by remember { mutableStateOf(0f) }
//        var offsetY by remember { mutableStateOf(0f) }
        var direction = ""
        Box(
            Modifier
//                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
//                .size(100.dp, 100.dp)
                .fillMaxSize()
                .background(Color.Blue)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consumeAllChanges()

                        val (x, y) = dragAmount
                        when {
                            x > 0 -> { /* right */
                            }
                            x < 0 -> { /* left */
                            }
                        }
                        when {
                            y > 0 -> { /* down */
                            }
                            y < 0 -> { /* up */
                            }
                        }

                        offsetX += dragAmount.x
//                        offsetY += dragAmount.y
                    }
                }
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "$offsetX")
        }
    }
}

@Composable
fun TestScreen() {
    val context = LocalContext.current
    BackHandler {

        val sharedPreferences: SharedPreferences = context.getSharedPreferences("SharedRef", Context.MODE_PRIVATE)

        val editor :SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply{
            putBoolean("FIRST_RUN", true)
        }.apply()

        context.startActivity(
                            Intent(
                                context,
                                MainActivity::class.java
                            )
                        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun Greeting(viewModel: MainViewModel = hiltViewModel(), singleProductModel: SingleProductViewModel = hiltViewModel()) {

    var offset by remember { mutableStateOf(0) }

    val context = LocalContext.current

    val sharedPreferences: SharedPreferences = context.getSharedPreferences("SharedRef", Context.MODE_PRIVATE)
    val saved: Int = sharedPreferences.getInt("TEST", 3)

    val run: Boolean? = sharedPreferences.getBoolean("FIRST_RUN", true)

    if (run == true)
    {
        singleProductModel.getVariationList(saved)
        // Do your unique code magic here
        val editor :SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply{
            putBoolean("FIRST_RUN", false)
        }.apply()

    }else{
        //this will repeat everytime
    }


//    singleProductModel.getVariationList(saved)

    var veriationIndex by remember { mutableStateOf(0) }
    var visible by remember { mutableStateOf(true) }

    var countInt by remember { mutableStateOf(0) }

    val galleries = mutableListOf(singleProductModel.list.value.img_id)




    timer(initialDelay = 1500L, period = 1500L ) {

        if(countInt < 10 || countInt == 0) {
            countInt += 1
        }
        else
        {
            countInt = 0
        }
        if(countInt == 10 && veriationIndex <= singleProductModel.list.value.totalImages - 1) {
            visible = false
            veriationIndex++
            Timer().schedule(timerTask {
                visible = true
            }, 150)
        }
        if (countInt == 10 && veriationIndex > singleProductModel.list.value.totalImages - 1) {
            visible = false
            veriationIndex = 0
            Timer().schedule(timerTask {
                visible = true
            }, 150)
        }


    }

    val query: MutableState<String> = remember { mutableStateOf("") }
    val result = viewModel.list.value
    val depotResult = singleProductModel.list.value
    Text("←")

    Surface(modifier = Modifier
        .fillMaxSize()
    ) {

        Column(modifier = Modifier.padding(0.dp)) {



            if (depotResult.variation.isNotEmpty()) {
                for (image in singleProductModel.list.value.variation) {
                    galleries.add(image.variation_image)

                }
                Log.d("gal", "$galleries")
            }


            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                item {

//                    Card(
//                        modifier = Modifier.width(50.dp),
//                        shape = RoundedCornerShape(12.dp),
//                        elevation = 5.dp
//                    ) {
//                        IconButton(onClick = {
//                            context.startActivity(
//                            Intent(
//                                context,
//                                MainActivity::class.java
//                            ) )}) {
//                            Icon(
//                                imageVector = Icons.Outlined.KeyboardArrowLeft,
//                                contentDescription = ""
//                            )
//                        }
//
//                    }
                    Box(contentAlignment = Alignment.TopStart) {

                        Card(
                            elevation = 0.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(360.dp)
                                .padding(0.dp)
                                .clickable(
                                    onClick = {

                                        if (offset == 1 && veriationIndex < singleProductModel.list.value.totalImages -1) {
                                            veriationIndex += 1

                                        }

                                        if (offset == 0 && veriationIndex != 0) {
                                            veriationIndex -= 1

                                        }
                                        countInt = 0
                                    }
                                )
                        ) {



                            androidx.compose.animation.AnimatedVisibility(
                                visible = visible,
                                enter = fadeIn(animationSpec = tween(durationMillis = 100)),
                                exit = fadeOut(animationSpec = tween(durationMillis = 200))
                            ) {
                                Image(
                                    modifier = Modifier
                                        .scrollable(
                                            orientation = Orientation.Horizontal,
                                            state = rememberScrollableState {

                                                if(it < 0 ) {
                                                    offset = 1
                                                }
                                                else{
                                                    offset = 0
                                                }


//                                                if (it < 0) {veriationIndex++}
                                                it

                                            }
                                        ),
//                                        .fillMaxSize(),
                                    painter = rememberImagePainter(data = "https://res.cloudinary.com/dhdn7ukv9/image/upload/${galleries[veriationIndex]}" ),
                                    contentScale = ContentScale.FillWidth,
                                    contentDescription = null
                                )
                            }

                        }

                      IconButton(onClick = {
                            context.startActivity(
                                Intent(
                                    context,
                                    MainActivity::class.java
                                ) )},
                          modifier = Modifier
                              .alpha(0.6f)
                              .padding(vertical = 20.dp)
                        ) {
                            Icon(

                                imageVector = Icons.Outlined.KeyboardArrowLeft,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier
                                    .background(
                                        color = Color.Gray,
                                        shape = RoundedCornerShape(20.dp)
                                    )
                                    .padding(2.dp)

                            )
                        }

                    }

//                    Box {
//                        IconButton(onClick = {
//                            context.startActivity(
//                                Intent(
//                                    context,
//                                    MainActivity::class.java
//                                ) )}
//                        ) {
//                            Icon(
//                                imageVector = Icons.Outlined.KeyboardArrowLeft,
//                                contentDescription = ""
//                            )
//                        }
//
//                    }
//                    IconButton(onClick = {
//                        context.startActivity(
//                            Intent(
//                                context,
//                                MainActivity::class.java
//                            ) )}
//
//                    ) {
//                        Icon(
//                            imageVector = Icons.Outlined.KeyboardArrowLeft,
//                            contentDescription = ""
//                        )
//                    }

                }


//
//                for((index, variation) in singleProductModel.list.value.variation.withIndex()) {
//
//                    item {
//                        Button(
//                            onClick =
//                            {
//
//                                visible = false
//
//                                Timer().schedule(timerTask {
//                                    veriationIndex =  index + 1
//                                    visible = true
//
//
//                                }, 150)
//
//
//                            },
//                            colors = ButtonDefaults.buttonColors(Color.Transparent),
//                            modifier = Modifier.padding(horizontal = 1.dp, vertical = 0.dp),
//                            elevation = null
//                        ) {
//                            Card(
//                                elevation = 0.dp,
//                                modifier = Modifier
//                                    .width(40.dp)
//                                    .height(40.dp)
//                                    .padding(horizontal = 5.dp)
//
//
//                            ) {
//
//                                Image(
//                                    painter = rememberImagePainter(data = "https://res.cloudinary.com/dhdn7ukv9/image/upload/${variation.variation_image}" ),
//                                    contentScale = ContentScale.FillWidth,
//                                    contentDescription = null,
//
//                                    )
//
//
//                            }
//
//
//
//                            Text(text = "${variation.variation_name}", color = Color.Black, fontSize = 12.sp)
//
//                        }
//                    }
//
//                }

//                item {
//                    Button(onClick = {
////                        singleProductModel.getVariationList(saved)
//                        context.startActivity(
//                            Intent(
//                                context,
//                                MainActivity::class.java
//                            )
//                        )
//                    }) {
//                        Text(text = "Main $saved")
//
//                    }
//
//
//                }
                item {
                    LazyRow(content = {

                        for((index, variation) in singleProductModel.list.value.variation.withIndex()) {

                            item {
                                Button(
                                    onClick =
                                    {

                                        visible = false

                                        Timer().schedule(timerTask {
                                            veriationIndex =  index + 1
                                            visible = true


                                        }, 150)


                                    },
                                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                                    modifier = Modifier.padding(horizontal = 1.dp, vertical = 0.dp),
                                    elevation = null
                                ) {
                                    Card(
                                        elevation = 0.dp,
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                            .padding(horizontal = 1.dp)


                                    ) {

                                        Image(

                                            painter = rememberImagePainter(data = "https://res.cloudinary.com/dhdn7ukv9/image/upload/${variation.variation_image}" ),
                                            contentScale = ContentScale.FillWidth,
                                            contentDescription = null,

                                            )


                                    }



//                Text(text = "${variation.variation_name}", color = Color.Black, fontSize = 12.sp)

                                }
                            }

                        }
                    })
                    Column(modifier = Modifier.padding(8.dp)) {

                        Text(text = "${singleProductModel.list.value.product_name} - ${offset}",
                            fontSize = 25.sp, fontWeight = FontWeight.SemiBold)

                        if(singleProductModel.list.value.price != 0) {
                            Text(text = "₱${singleProductModel.list.value.price}", fontSize = 18.sp, color = Color.Red, fontWeight = FontWeight.SemiBold)
                        }
                        else {

                            val min = NumberFormat.getNumberInstance(Locale.US)
                                .format(singleProductModel.list.value.min)

                            val max = NumberFormat.getNumberInstance(Locale.US)
                                .format(singleProductModel.list.value.max)

                            Text(text = "₱${min}-₱${max}", fontSize = 18.sp, color = Color.Red, fontWeight = FontWeight.SemiBold)
                        }
                        Text(text = "${singleProductModel.list.value.description}",
                            fontSize = 15.sp, textAlign = TextAlign.Start)

                    }
                }
            }

//                val mainHandler = Handler(Looper.getMainLooper())

//                mainHandler.post(object : Runnable {
//                    override fun run() {
//                        countInt++
//                        mainHandler.postDelayed(this, 1000)
//                    }
//                })
//                Text(text = "$countInt")


            }

        }


    }



//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    PixabayImageSearchTheme {
//        Greeting("Android")
//    }
//}

@Composable
fun ImageSlider(viewModel: MainViewModel = hiltViewModel(), singleProductModel: SingleProductViewModel = hiltViewModel()) {

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SliderView(state: PagerState, viewModel: SingleProductViewModel = hiltViewModel(), singleProductModel: SingleProductViewModel = hiltViewModel()) {
    val galleries = mutableListOf(singleProductModel.list.value.img_id)
    val depotResult = singleProductModel.list.value
    val context = LocalContext.current
    if (depotResult.variation.isNotEmpty()) {
        for (image in singleProductModel.list.value.variation) {
            galleries.add(image.variation_image)

        }
        Log.d("gal", "$galleries")
    }

    val imageUrl =
        remember { mutableStateOf("") }
    Box(contentAlignment = Alignment.TopStart) {


    HorizontalPager(
        state = state,
        count = galleries.count(), modifier = Modifier
            .height(360.dp)
            .fillMaxSize()
    ) { page ->
        imageUrl.value = "https://res.cloudinary.com/dhdn7ukv9/image/upload/${galleries[page]}"

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {




                val painter = rememberImagePainter(data = imageUrl.value, builder = {
//                    placeholder(R.drawable.placeholder)
                })
                Image(
                    painter = painter, contentDescription = "", Modifier
//                        .padding(8.dp).clip(RoundedCornerShape(10.dp))
                        .padding(0.dp)
                        .fillMaxSize()
                        , contentScale = ContentScale.FillWidth
                )


//                Text(
//                    text = singleProductModel.list.value.variation[page].variation_name,
//                    Modifier
//                        .fillMaxWidth()
//                        .height(60.dp)
//                        .padding(8.dp)
//                        .background(Color.LightGray.copy(alpha = 0.60F))
//                        .padding(8.dp),
//                    textAlign = TextAlign.Start,
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Medium
//                )
            }



        }

        IconButton(onClick = {
            context.startActivity(
                Intent(
                    context,
                    MainActivity::class.java
                ) )},
            modifier = Modifier
                .alpha(0.6f)
                .padding(vertical = 20.dp)
        ) {
            Icon(

                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(2.dp)

            )
        }

    }
    Column(modifier = Modifier.padding(8.dp)) {

        Text(text = "${singleProductModel.list.value.product_name}",
            fontSize = 25.sp, fontWeight = FontWeight.SemiBold)

        if(singleProductModel.list.value.price != 0) {
            Text(text = "₱${singleProductModel.list.value.price}", fontSize = 18.sp, color = Color.Red, fontWeight = FontWeight.SemiBold)
        }
        else {

            val min = NumberFormat.getNumberInstance(Locale.US)
                .format(singleProductModel.list.value.min)

            val max = NumberFormat.getNumberInstance(Locale.US)
                .format(singleProductModel.list.value.max)

            Text(text = "₱${min}-₱${max}", fontSize = 18.sp, color = Color.Red, fontWeight = FontWeight.SemiBold)
        }
        Text(text = "${singleProductModel.list.value.description}",
            fontSize = 15.sp, textAlign = TextAlign.Start)

    }
}

//
//@Composable
//fun MovieCard(movie: Movies) {
//
//    Card(modifier = Modifier
//        .fillMaxWidth()
//        .height(100.dp)) {
//        Text(text = movie.name, Modifier.fillMaxWidth())
//
//    }
//
//}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.DarkGray)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.LightGray)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

