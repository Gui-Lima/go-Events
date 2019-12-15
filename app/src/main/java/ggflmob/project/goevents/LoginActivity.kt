package ggflmob.project.goevents

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import ggflmob.project.goevents.Exceptions.Resource
import ggflmob.project.goevents.Models.User
import ggflmob.project.goevents.Viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var username : EditText
    private lateinit var password : EditText
    private lateinit var login : Button
    private lateinit var register : Button
    private lateinit var loading : ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        login = findViewById(R.id.login)
        register = findViewById(R.id.register)
        loading = findViewById(R.id.loading)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.content.observe(this, Observer {
            when(it){
                is Resource.Complete -> {
                    loading.visibility = View.INVISIBLE
                    var preferences = getSharedPreferences("ggflmob.project.goevents", Context.MODE_PRIVATE)
                    preferences.edit().putString("session", User.toJson(it.data)).apply()
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    startActivity(intent)
                }
                is Resource.Error -> {
                    loading.visibility = View.INVISIBLE
                    Toast.makeText(this@LoginActivity, "Ops, something went wrong",Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    loading.visibility = View.VISIBLE
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
