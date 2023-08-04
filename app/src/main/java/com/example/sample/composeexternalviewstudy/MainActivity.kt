package com.example.sample.composeexternalviewstudy

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.example.sample.composeexternalviewstudy.databinding.SomelayoutBinding
import com.example.sample.composeexternalviewstudy.ui.theme.ComposeExternalViewStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExternalViewStudyTheme {
                XMLLayoutCompose()
            }
        }
    }
}



@Composable
fun XMLLayoutCompose() {
    AndroidViewBinding(SomelayoutBinding::inflate) {
        // Access the views defined in the XML via the binding object
        // ...
        textView.text = "테스트"

        button.setOnClickListener {
            textView.text = "hello world"
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WebViewListCompose() {
    val urls = listOf(
        "https://developer.android.com/jetpack/compose",
        "https://google.github.io/accompanist/",
        "https://android-developers.googleblog.com/",
        "https://io.google/",
        // ...
    )
    LazyVerticalGrid(columns = GridCells.Adaptive(512.dp)) {
        items(items = urls) { url ->
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        settings.javaScriptEnabled = true
                        webViewClient = object : WebViewClient() {
                            // Optional overrides for WebViewClient
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                update = { webView -> webView.loadUrl(url) },
                onReset = { webView ->
                    webView.stopLoading()
                    webView.loadUrl("about:blank")
                    webView.clearHistory()
                }
            )
        }
    }
}