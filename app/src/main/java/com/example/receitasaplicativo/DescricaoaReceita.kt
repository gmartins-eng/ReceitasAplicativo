package com.example.receitasaplicativo

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DescricaoaReceita : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descricao_receita)

        val recipeName = intent.getStringExtra("RECIPE_NAME")
        val recipeDescription = intent.getStringExtra("RECIPE_DESCRIPTION")
        val recipeSteps = intent.getStringExtra("RECIPE_STEPS")
        val recipeImageUrl = intent.getStringExtra("RECIPE_IMAGE_URL")

        findViewById<TextView>(R.id.titulodareceita).text = recipeName
        findViewById<TextView>(R.id.descreceita).text = recipeDescription
        findViewById<TextView>(R.id.passosreceita).text = recipeSteps

        val recipeImage = findViewById<ImageView>(R.id.imagemreceita)
        Glide.with(this)
            .load(recipeImageUrl)
            .placeholder(R.drawable.default_image)
            .error(R.drawable.default_image)
            .into(recipeImage)
    }
}
