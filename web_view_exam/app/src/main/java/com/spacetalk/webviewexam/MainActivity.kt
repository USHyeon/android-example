package com.spacetalk.webviewexam

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.spacetalk.webviewexam.commons.keyboardHide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webSettings = web_view.settings

        //자바 스크립트 사용을 할 수 있게
        webSettings.javaScriptEnabled = true

        web_view.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Toast.makeText(baseContext, "로딩 끝", Toast.LENGTH_SHORT).show()
            }
        }

        et_search.setOnEditorActionListener { v, actionId, event ->
            if (event?.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_SEARCH) {
                //버튼의 onClick 을 실행
                btn_search.callOnClick()
                //키보드 숨기기
                v.keyboardHide()

                true
            } else {
                false
            }
        }

        btn_search.setOnClickListener {
            var strAddress = et_search.text.toString()
            //앞부분에 http://을 추가해줌
            if (!strAddress.startsWith("http://")) {
                strAddress = "http://$strAddress"
            }
            //웹뷰 로드
            web_view.loadUrl(strAddress)
        }
    }

    override fun onBackPressed() {
        //뒤로가기 버튼을 눌렀을 때 웹뷰가 뒤로가기가 가능하다면 뒤로가기.
        if (web_view.canGoBack()) {
            web_view.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when ( item.itemId) {
            R.id.action_back -> {
                if(web_view.canGoBack()) {
                    web_view.goBack()
                }
            }
            R.id.action_forward -> {
                if(web_view.canGoForward()) {
                    web_view.goForward()
                }
            }
            R.id.action_refresh -> {
                web_view.reload()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}