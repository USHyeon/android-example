package com.spacetalk.qr_codeexam.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.spacetalk.qr_codeexam.R
import com.spacetalk.webviewexam.commons.keyboardHide
import com.spacetalk.webviewexam.commons.toEditable
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var integrator: IntentIntegrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //자바 스크립트 사용할 수 있게
        val webSetting = web_view.settings
        webSetting.javaScriptEnabled = true

        web_view.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                Toast.makeText(baseContext, "로딩 끝", Toast.LENGTH_SHORT).show()
                super.onPageFinished(view, url)
            }
        }

        et_search.setOnEditorActionListener { v, actionId, event ->
            if(event?.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_SEARCH) {
                //btn_search 의 onClick 를 실행
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
            if(!strAddress.startsWith("https://")
                    && !strAddress.startsWith("http://")) {
                strAddress = "https://$strAddress"
            }
            web_view.loadUrl(strAddress)
        }

        //바코드 스캐너 세팅
        initIntegrator()
        btn_barcode.setOnClickListener {
            //바코드 스캐너 시작
            integrator.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        result.contents?.let {
            //QR 코드를 읽어서 EditText 에 입력해줍니다
            et_search.text = (result.contents).toEditable()
            //btn_search 의 onClick 를 호출
            btn_search.callOnClick()

            Toast.makeText(this, "Scanned: ${result.contents}", Toast.LENGTH_SHORT).show()
            true
        }

        Toast.makeText(this, "바코드 requestCode:$requestCode, resultCode$resultCode, data$data", Toast.LENGTH_SHORT).show()

    }

    private fun initIntegrator() {
        integrator = IntentIntegrator(this)
        integrator.apply {
            //바코드 안의 텍스트
            setPrompt("바코드를 사각형 안에 비춰주세요")
            //바코드 인식시 소리 여부
            setBeepEnabled(false)

            setBarcodeImageEnabled(true)
            captureActivity = CaptureActivity::class.java
        }
    }
}