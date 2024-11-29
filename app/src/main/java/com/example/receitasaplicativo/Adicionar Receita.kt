package com.example.receitasaplicativo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class AdicionarReceitaActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_receita)

        // Inicializar o Firestore
        db = FirebaseFirestore.getInstance()

        // Referências para os campos de entrada
        val recipeNameInput = findViewById<EditText>(R.id.recipe_name_input)
        val recipeDescriptionInput = findViewById<EditText>(R.id.recipe_description_input)
        val recipeStepsInput = findViewById<EditText>(R.id.recipe_steps_input)
        val recipeImageUrlInput = findViewById<EditText>(R.id.recipe_image_url_input)
        val addRecipeButton = findViewById<Button>(R.id.add_recipe_button)

        // Configurar clique no botão "Adicionar Receita"
        addRecipeButton.setOnClickListener {
            val name = recipeNameInput.text.toString().trim()
            val description = recipeDescriptionInput.text.toString().trim()
            val steps = recipeStepsInput.text.toString().trim()
            val imageUrl = recipeImageUrlInput.text.toString().trim()

            if (name.isEmpty() || description.isEmpty() || steps.isEmpty() || imageUrl.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else {
                addRecipeToFirestore(name, description, steps, imageUrl)
            }
        }
    }

    private fun addRecipeToFirestore(name: String, description: String, steps: String, imageUrl: String) {
        val recipe = hashMapOf(
            "name" to name,
            "descricao_rec" to description,
            "passo_a_passo" to steps,
            "image_url" to imageUrl
        )

        db.collection("receitas")
            .add(recipe)
            .addOnSuccessListener {
                Toast.makeText(this, "Receita adicionada com sucesso!", Toast.LENGTH_SHORT).show()
                finish() // Fecha a tela após adicionar a receita
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao adicionar receita: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
