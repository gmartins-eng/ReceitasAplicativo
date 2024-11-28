package com.example.receitasaplicativo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var user : EditText
    lateinit var pass : EditText
    lateinit var loginButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user = findViewById(R.id.userid)
        pass = findViewById(R.id.pass)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener{
            if (user.text.toString().equals("teste") &&
                pass.text.toString().equals("1234")){
                Toast.makeText(this,"Foi!",Toast.LENGTH_LONG).show()
                var intent = Intent(applicationContext, Tela2::class.java)
                startActivity(intent)
            }
        }

    }
}