package com.example.user.webviewtext

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast
import com.example.user.webviewtext.R.id.text
import java.net.URL

class MainActivity : AppCompatActivity() {
var et1:EditText?=null
var wview:WebView?=null
    @SuppressLint("AddJavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wview=findViewById(R.id.wview)
        wview?.settings?.javaScriptEnabled=true
        wview?.settings?.builtInZoomControls=true
        wview?.addJavascriptInterface(this,"MyInterface")
        et1=findViewById(R.id.et1)
        wview?.webViewClient=object: WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?)
            {

                Toast.makeText(this@MainActivity,"page loading started",Toast.LENGTH_LONG).show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Toast.makeText(this@MainActivity,"page loading finished",Toast.LENGTH_LONG).show()
            }

        }
    }

    @JavascriptInterface
    fun display(uname:String,pwd:String)
    {
        Toast.makeText(this@MainActivity,uname+"\t"+pwd,Toast.LENGTH_LONG).show()

    }


    fun load(v: View) {
    when(v.id){
        R.id.search->wview?.loadUrl(et1?.text.toString())
        R.id.google->wview?.loadUrl("http://google.com")
        R.id.fb->wview?.loadUrl("http://facebook.com")
        R.id.youtube->wview?.loadUrl("http://youtube.com")
        R.id.html->wview?.loadUrl("file:///android_asset/login_new.html")

    }
    }
}
