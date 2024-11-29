package com.example.receitasaplicativo

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecipeAdapter(private var listRecipe: List<Recipe>) : RecyclerView.Adapter<RecipeAdapter.RecipeItemHolder>() {

    class RecipeItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeName: TextView = itemView.findViewById(R.id.tvRecipeName)
        val recipeImage: ImageView = itemView.findViewById(R.id.imageView)
        val recipeDescricao: TextView = itemView.findViewById(R.id.descricao_receita)
        val verReceitaButton: Button = itemView.findViewById(R.id.verReceitaButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_receita, parent, false)
        return RecipeItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listRecipe.size
    }

    override fun onBindViewHolder(holder: RecipeItemHolder, position: Int) {
        val recipe = listRecipe[position]
        holder.recipeName.text = recipe.name
        holder.recipeDescricao.text = recipe.descricao_rec

        // Usar Glide para carregar a imagem do Firestore
        Glide.with(holder.itemView.context)
            .load(recipe.image_url)
            .placeholder(R.drawable.default_image) // Imagem de placeholder
            .error(R.drawable.default_image)      // Imagem em caso de erro
            .into(holder.recipeImage)

        holder.verReceitaButton.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DescricaoaReceita::class.java)
            intent.putExtra("RECIPE_NAME", recipe.name)
            intent.putExtra("RECIPE_DESCRIPTION", recipe.descricao_rec)
            intent.putExtra("RECIPE_STEPS", recipe.passo_a_passo)
            intent.putExtra("RECIPE_IMAGE_URL", recipe.image_url)
            context.startActivity(intent)
        }
    }

    fun updateRecipeList(newList: List<Recipe>) {
        listRecipe = newList
        notifyDataSetChanged()
    }
}
