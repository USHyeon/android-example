package com.test.umstagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var auth: FirebaseAuth? = null

    var googleSignInClient : GoogleSignInClient? = null
    val GOOGLE_LOGIN_CODE = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //1.먼저 authentication library 를 불러움
        auth = FirebaseAuth.getInstance()

        email_login_button.setOnClickListener {
            signinAndSignup()
        }
        //5.구글 로그인 옵션
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) //api key
            .requestEmail()
            .build()
        //6.옵션 값을 google signin client 에 추가해 줌.
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        google_signin_button.setOnClickListener {
            //First Step
            googleLogin()

        }
    }
    //7.구글 로그인
    fun googleLogin() {
        var signInIntent = googleSignInClient?.signInIntent
        startActivityForResult(signInIntent, GOOGLE_LOGIN_CODE)
    }
    //8.구글 로그인 값을 받아와서 처리
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_LOGIN_CODE) {
            var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            result?.let { result ->
                var account =  result.signInAccount
                //Second step
                account?.let { account ->
                    firebaseAuthWithGoogle(account)
                }
                true
            }
        }
    }
    //9.firebase 에 넘길수 있게 만들어줌
    fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        var credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    //아이디와 패스워드가 맞을 때.
                    moveMainPage(task.result?.user)
                } else {
                    //틀렸을 때.
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    //2.회원가입 코드를 넣어주도록 한다
    fun signinAndSignup() {
        auth?.createUserWithEmailAndPassword(
            email_edittext.text.toString(),
            password_edittext.text.toString()
        )?.addOnCompleteListener { task ->
            //아이디가 만들어졌을 때
            if(task.isSuccessful) {
                //Creating a user account
                moveMainPage(task.result?.user)
            } else if(!(task.exception?.message.isNullOrEmpty())) {
                //실패했을 때 메시지
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
            } else {
                //Login if you have account
                signinEmail()
            }
        }
    }

    //3.로그인 하는 부분
    fun signinEmail() {
        auth?.createUserWithEmailAndPassword(
            email_edittext.text.toString(),
            password_edittext.text.toString()
        )?.addOnCompleteListener { task ->
            if(task.isSuccessful) {
                //아이디와 패스워드가 맞을 때.
                moveMainPage(task.result?.user)
            } else {
                //틀렸을 때.
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    //4.로그인 성공시 다음페이지로.
    fun moveMainPage(user: FirebaseUser?) {
        if(user != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
