package com.example.newsapplication.ui.main

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.newsapplication.R
import kotlinx.android.synthetic.main.activity_webview.*

class Webview : AppCompatActivity() {
    lateinit var url:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        var intentdata=intent
        if (intentdata!=null){
            url=intentdata.getStringExtra("url")
        }

       webiew.loadUrl(url)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
