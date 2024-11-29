package com.example.receitasaplicativo

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class Tela2 : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var recipeAdapter: RecipeAdapter
    private val recipeList = mutableListOf<Recipe>()
    private val filteredList = mutableListOf<Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela2)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val addRecipeButton = findViewById<Button>(R.id.addRecipeButton)
        val searchEditText = findViewById<EditText>(R.id.searchBar) // Barra de pesquisa

        // Configurar RecyclerView
        recipeAdapter = RecipeAdapter(filteredList) // Mostrar a lista filtrada
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recipeAdapter

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance()

        // Carregar receitas do Firestore
        loadRecipesFromFirestore()

        // Clique no botão "Adicionar Receita"
        addRecipeButton.setOnClickListener {
            val intent = Intent(this, AdicionarReceitaActivity::class.java)
            startActivity(intent)
        }

        // Configurar a barra de pesquisa
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterRecipes(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun loadRecipesFromFirestore() {
        db.collection("receitas")
            .get()
            .addOnSuccessListener { result ->
                recipeList.clear()
                for (document in result) {
                    val recipe = Recipe(
                        name = document.getString("name") ?: "",
                        descricao_rec = document.getString("descricao_rec") ?: "",
                        passo_a_passo = document.getString("passo_a_passo") ?: "",
                        image_url = document.getString("image_url") ?: ""
                    )
                    recipeList.add(recipe)
                }
                // Atualizar a lista filtrada inicialmente com todas as receitas
                filteredList.clear()
                filteredList.addAll(recipeList)
                recipeAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
    }

    private fun filterRecipes(query: String) {
        filteredList.clear()
        if (query.isEmpty()) {
            filteredList.addAll(recipeList) // Mostrar todas as receitas se a busca estiver vazia
        } else {
            val lowerCaseQuery = query.lowercase()
            recipeList.forEach { recipe ->
                if (recipe.name.lowercase().contains(lowerCaseQuery) ||
                    recipe.descricao_rec.lowercase().contains(lowerCaseQuery) ||
                    recipe.passo_a_passo.lowercase().contains(lowerCaseQuery)) {
                    filteredList.add(recipe)
                }
            }
        }
        recipeAdapter.notifyDataSetChanged() // Atualizar o RecyclerView
    }
}
