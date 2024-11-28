package com.example.receitasaplicativo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Item_receita : AppCompatActivity() {

    private lateinit var bt_verreceita : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_receita)

        bt_verreceita = findViewById(R.id.bt_verreceita)

        bt_verreceita.setOnClickListener{
                var intent = Intent(applicationContext, DescricaoaReceita::class.java)
                startActivity(intent)
            }
        }

    }
