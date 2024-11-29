package com.example.receitasaplicativo

data class Recipe(
    val name: String = "",          // Nome da receita
    val descricao_rec: String = "", // Breve descrição da receita
    val passo_a_passo: String = "", // Passo a passo
    val image_url: String = ""      // URL da imagem
)