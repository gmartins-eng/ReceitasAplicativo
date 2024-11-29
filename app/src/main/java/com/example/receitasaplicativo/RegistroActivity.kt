package com.example.receitasaplicativo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegistroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // Inicializando o FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Obtendo referências dos componentes da tela
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)

        // Configurando o clique do botão de registrar
        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Chama o método para registrar o usuário com email e senha
            registerWithEmail(email, password)
        }
    }

    private fun registerWithEmail(email: String, password: String) {
        // Verificando se os campos não estão vazios
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Criando usuário no Firebase
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Se o registro for bem-sucedido
                    Toast.makeText(this, "Usuário registrado com sucesso!", Toast.LENGTH_SHORT).show()

                    // Redireciona para a tela de login após o registro
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Finaliza a tela de registro para que o usuário não volte para ela
                } else {
                    // Se houver algum erro
                    Toast.makeText(this, "Erro ao registrar: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}
