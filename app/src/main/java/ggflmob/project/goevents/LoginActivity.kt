package ggflmob.project.goevents

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModel
import ggflmob.project.goevents.Exceptions.Resource

import ggflmob.project.goevents.R
import ggflmob.project.goevents.Viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val register = findViewById<Button>(R.id.register)
        val loading = findViewById<ProgressBar>(R.id.loading)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.content.observe(this, Observer {
            when(it){
                is Resource.Complete -> {
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    intent.putExtra("UserLoggedIn", it.data)
                    startActivity(intent)
                }
            }
        })

        login.setOnClickListener {
            loginViewModel.login(username.text.toString(), password.text.toString())
        }

        register.setOnClickListener {
            loginViewModel.register(username.text.toString(), password.text.toString())
        }
    }


}
