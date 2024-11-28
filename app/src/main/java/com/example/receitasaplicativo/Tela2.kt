package com.example.receitasaplicativo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Tela2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela2)

        val recipeList = listOf(Recipe("Poke", R.drawable.poke, "Poke caseiro feito em casa"),Recipe("Massa com Camarão", R.drawable.camarao, "Massa Caseira com Molho de Camarão") ,Recipe("Hamburguer", R.drawable.hamburguer, "Hamburguer de Carne Vegano"),Recipe("Musse", R.drawable.musse, "Musse de maracujá - Rápidoc e Fácil"),Recipe("Bolo de Cenoura", R.drawable.bolocenoura, "Bolo de Cenoura sem Glúten"), Recipe("Galinhada", R.drawable.galinhada, "Galinhada com sobrecoxa"),Recipe("Feijão Carioca", R.drawable.feijao, "Feijão Carioca com ervas"))

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = RecipeAdapter(recipeList)

    }
}