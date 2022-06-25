package com.gaur.pixabayimagesearch

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.gaur.pixabayimagesearch.ui.components.MainContent
import com.gaur.pixabayimagesearch.ui.theme.PixabayImageSearchTheme
import com.gaur.pixabayimagesearch.util.Constant
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            window.statusBarColor = ContextCompat.getColor(this, android.R.color.black)


            if (Build.VERSION.SDK_INT >= 21) {
                val window = this.window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = this.resources.getColor(R.color.colorPrimaryDark)
            }
            PixabayImageSearchTheme {
                MyApp {
//                    MyWebView()
                    MainContent()
//                    Button(onClick = { val navigate = Intent(this, DriverActivity::class.java)
//                    startActivity(navigate)}) {
//                        Text(text = "Next Page")
//                    }




                }
            }
        }
    }
}



@Composable
fun MyApp(content: @Composable ()->Unit) {
    content()
}

@Composable
fun MyWebView(){
    // Declare a string that contains a url
    val mUrl = "https://test-lumen-7.herokuapp.com/pos/testPayment"

    // Adding a WebView inside AndroidView
    // with layout as full screen
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true


            loadUrl(mUrl)
        }
    }, update = {
        it.loadUrl(mUrl)
    })
}
